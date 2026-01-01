package net.tazgirl.magicjson.registration.processes;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.helpers.InputStreamToJson;
import net.tazgirl.magicjson.registration.RegistersForProcessing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class TokenSynonyms
{
    public static void RegisterSynonyms()
    {
        Map<ResourceLocation, Resource> resources = GetAllSynonymJsons();

        for(Map.Entry<ResourceLocation, Resource> entry: resources.entrySet())
        {
            ProcessSynonymJson(entry);
        }
    }

    public static Map<ResourceLocation, Resource> GetAllSynonymJsons()
    {
        ResourceManager resourceManager = Constants.server.getResourceManager();

        return resourceManager.listResources("magicjson", path -> path.getPath().endsWith("hook_synonyms.json"));
    }

    public static void ProcessSynonymJson(Map.Entry<ResourceLocation, Resource> entry)
    {
        try(InputStream inputStream = entry.getValue().open())
        {
            JsonObject jsonObject = InputStreamToJson.getJson(inputStream);

            if(jsonObject == null){return;}

            jsonObject.entrySet().forEach(elementEntry ->
            {
                if(elementEntry.getValue() instanceof JsonObject entryObject)
                {
                    String address;
                    String synonym;
                    try
                    {
                        address = entryObject.get(Constants.hookSynonymAddressElement).getAsString();
                        synonym = entryObject.get(Constants.hookSynonymSynonymElement).getAsString();
                    }
                    catch (IllegalStateException | NullPointerException e)
                    {
                        MJLogging.Warn("JsonObject \"" + elementEntry.getKey() + "\" did not have the expected contents of two JsonPrimitive Strings named \"" + Constants.hookSynonymAddressElement + "\" and \"" + Constants.hookSynonymSynonymElement + "\" respectively");
                        return;
                    }

                    String previousSynonym = RegistersForProcessing.tokenSynonyms.put(address, synonym);

                    if(previousSynonym != null)
                    {
                        MJLogging.Debug("Added the synonym \"" + synonym + "\" for the address \"" + address + "\", overriding the old synonym of \"" + previousSynonym + "\"");
                    }
                }
            });
        }
        catch (IOException e)
        {
            MJLogging.Error("Could not process .json: " + entry.getKey());
        }
    }
}
