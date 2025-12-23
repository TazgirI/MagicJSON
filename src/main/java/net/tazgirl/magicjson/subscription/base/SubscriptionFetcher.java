package net.tazgirl.magicjson.subscription.base;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.helpers.InputStreamToJson;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SubscriptionFetcher
{
    public static StatementPriorityQueue fetchAddresses(String eventKey)
    {
        Map<ResourceLocation, Resource> files = GetFiles(eventKey);
        if(files == null){return null;}

        Map<ExecutableAddress, Integer> returnMap = new HashMap<>();

        for(Map.Entry<ResourceLocation, Resource> entry : files.entrySet())
        {
            Map<ExecutableAddress, Integer> entryResult = objectToEntry(entry);

            if(!entryResult.isEmpty())
            {
                returnMap.putAll(entryResult);
            }
        }

        return new StatementPriorityQueue(returnMap);
    }

    static Map<ExecutableAddress, Integer> objectToEntry(Map.Entry<ResourceLocation, Resource> entry)
    {
        Map<ExecutableAddress, Integer> returnMap = new HashMap<>();

        try(InputStream inputStream = entry.getValue().open())
        {
            JsonObject jsonObject = InputStreamToJson.getJson(inputStream);

            if(jsonObject == null){return returnMap;}

            jsonObject.entrySet().forEach(elementEntry ->
            {
                if(elementEntry.getValue() instanceof JsonObject entryObject)
                {
                    String address;
                    int priority;
                    try
                    {
                        address = entryObject.get(Constants.statementSubscriberAddresssElement).getAsString();
                        priority = entryObject.get(Constants.statementSubscriberPriorityElement).getAsInt();

                        returnMap.put(new ExecutableAddress(address), priority);
                    }
                    catch (IllegalStateException | NullPointerException e)
                    {
                        Logging.Warn("JsonObject \"" + elementEntry.getKey() + "\" did not have the expected contents of two JsonPrimitive Strings named \"" + Constants.hookSynonymAddressElement + "\" and \"" + Constants.hookSynonymSynonymElement + "\" respectively");
                    }

                }
            });
        }
        catch (IOException e)
        {
            Logging.Error("Could not process .json: " + entry.getKey());
        }

        return returnMap;
    }

    static Map<ResourceLocation, Resource> GetFiles(String filePath)
    {
        if(Constants.server == null){return null;}

        ResourceManager resourceManager = Constants.server.getResourceManager();

        return resourceManager.listResources("magicjson/subscribe/" + filePath, path -> path.getPath().endsWith(".json"));
    }

}
