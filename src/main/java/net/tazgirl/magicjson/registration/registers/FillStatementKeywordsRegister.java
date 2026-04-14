package net.tazgirl.magicjson.registration.registers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.helpers.InputStreamToJson;
import net.tazgirl.magicjson.registration.registers.objectification.StatementKeywordsRegister;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@EventBusSubscriber
public class FillStatementKeywordsRegister
{
    @SubscribeEvent
    public static void fillStatementKeywords(StatementKeywordsRegister.FetchEvent event)
    {
        Map<ResourceLocation, Resource> files = getFiles();

        if(files == null)
        {
            return;
        }

        for(Resource resource : files.values())
        {
            JsonObject jsonObject;
            try(InputStream inputStream = resource.open())
            {
                jsonObject = InputStreamToJson.getJson(inputStream);
            }
            catch (IOException ignored)
            {
                MJLogging.debug("Failed to get StatementKeyword json as a JsonArray, skipping file. Source pack id: " + resource.sourcePackId());
                continue;
            }

            if(jsonObject == null)
            {
                continue;
            }

            for(Map.Entry<String, JsonElement> entry : jsonObject.asMap().entrySet())
            {
                JsonElement element = entry.getValue();
                if(element.isJsonPrimitive())
                {
                    event.put(entry.getKey(), element.getAsString());
                }
            }
        }
    }

    static Map<ResourceLocation, Resource> getFiles()
    {
        if(Constants.server == null){return null;}

        ResourceManager resourceManager = Constants.server.getResourceManager();

        return resourceManager.listResources("magicjson/register/statement_keyword", path -> path.getPath().endsWith(".json"));
    }


}
