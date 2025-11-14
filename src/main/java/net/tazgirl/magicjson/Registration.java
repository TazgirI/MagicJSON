package net.tazgirl.magicjson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.tazgirl.magicjson.main.TokenToStatement;
import net.tazgirl.magicjson.main.Tokenise;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.JsonToFunctionObject;
import oshi.jna.platform.mac.SystemB;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@EventBusSubscriber(modid = MagicJson.MODID)
public class Registration
{

    @SubscribeEvent
    public static void ServerStarting(ServerStartingEvent event)
    {
        ProcessStatements(event);
        ProcessFunctions(event);
    }

    public static void ProcessFunctions(ServerStartingEvent event)
    {
        for(Map.Entry<ResourceLocation, Resource> entry : GetAllFunctions(event).entrySet())
        {
            try(InputStream inputStream = entry.getValue().open())
            {

                if(JsonParser.parseReader(new InputStreamReader(inputStream)) instanceof JsonObject jsonObject)
                {
                    PrivateCore.addFunction(
                            ResourceLocationToAddress(entry.getKey().toString()),
                            JsonToFunctionObject.ConstructFunctionFromJson(jsonObject));
                }

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static void ProcessStatements(ServerStartingEvent event)
    {
        for(Map.Entry<ResourceLocation, Resource> entry : GetAllStatements(event).entrySet())
        {
            try(InputStream inputStream = entry.getValue().open())
            {
                PrivateCore.addStatement(ResourceLocationToAddress(entry.getKey().toString()),
                        TokenToStatement.objectFromTokens
                                (
                                        Tokenise.TokeniseStatement
                                                (
                                                        new String(inputStream.readAllBytes(), StandardCharsets.UTF_8)
                                                )
                                )
                );

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }



    public static Map<ResourceLocation, Resource> GetAllFunctions(ServerStartingEvent event)
    {
        ResourceManager resourceManager = event.getServer().getResourceManager();

        return resourceManager.listResources("magicjson/function", path -> path.getPath().endsWith(".json"));
    }

    public static Map<ResourceLocation, Resource> GetAllStatements(ServerStartingEvent event)
    {
        ResourceManager resourceManager = event.getServer().getResourceManager();

        return resourceManager.listResources("magicjson/statement", path -> path.getPath().endsWith(".txt"));
    }

    // Make sure to exit after first hit to prevent too much removal
    public static Map<String, String> resourceLocationReplacements = Map.of(
            "magicjson/statement/", "",
            "magicjson/function/", "",
            // Very important the list is handled in order as the ones below this line are only backups to catch shorthand
            ":statement/", ":",
            ":function/", ":"
    );

    public static String ResourceLocationToAddress(String location)
    {
        if(!location.contains(":")){return null;}

        for(String stringToReplace : resourceLocationReplacements.keySet())
        {
            if(location.contains(stringToReplace))
            {
                location = location.replace(stringToReplace,resourceLocationReplacements.get(stringToReplace));
                break;
            }
        }

        int indexSubtraction = location.contains(".") ? location.substring(location.lastIndexOf('.')).length() : 0;

        location = location.substring(0, location.length() - indexSubtraction);


        return location;
    }

}
