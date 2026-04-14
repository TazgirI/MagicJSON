package net.tazgirl.magicjson.subscription.base;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.helpers.EventPriorityFrom;
import net.tazgirl.magicjson.helpers.InputStreamToJson;
import net.tazgirl.magicjson.subscription.EventSubscriptionHolder;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class SubscriptionFetcher
{
    public static <E extends Event, T extends EventSubscriptionHolder<E>> List<T> setupSubscribers(String eventKey, BiFunction<String, EventPriority, T> builder, IEventBus eventBus)
    {
        List<T> returnList = new ArrayList<>();

        Map<ResourceLocation, Resource> files = getFiles(eventKey);
        if(files == null)
        {
            return null;
        }

        for(Map.Entry<ResourceLocation, Resource> entry : files.entrySet())
        {
            List<T> holders = objectToEntry(entry, builder, eventBus);
            if(!holders.isEmpty())
            {
                returnList.addAll(holders);
            }
        }

        return returnList;
    }

    @NotNull
    static <E extends Event, T extends EventSubscriptionHolder<E>> List<T> objectToEntry(Map.Entry<ResourceLocation, Resource> entry, BiFunction<String, EventPriority, T> builder, IEventBus eventBus)
    {
        List<T> returnList = new ArrayList<>();

        try(InputStream inputStream = entry.getValue().open())
        {
            JsonObject jsonObject = InputStreamToJson.getJson(inputStream);

            if(jsonObject == null)
            {
                return returnList;
            }

            jsonObject.entrySet().forEach(elementEntry ->
            {
                if(elementEntry.getValue() instanceof JsonObject entryObject)
                {
                    String address = null;
                    EventPriority priority = EventPriority.NORMAL;
                    try
                    {
                        address = entryObject.get(Constants.statementSubscriberAddresssElement).getAsString();
                        priority = EventPriorityFrom.element(entryObject.get(Constants.statementSubscriberPriorityElement));
                    }
                    catch (IllegalStateException | NullPointerException e)
                    {
                        if(address == null)
                        {
                            MJLogging.debug("JsonObject \"" + elementEntry.getKey() + "\" did not have an element called \"" + Constants.statementSubscriberAddresssElement + "\", skipping this object");
                        }
                        else
                        {
                            MJLogging.info("JsonObject \"" + elementEntry.getKey() + "\" did not have an element called \"" + Constants.statementSubscriberPriorityElement + "\", defaulting to NORMAL(3)");
                        }
                    }

                    if(address != null)
                    {
                        T holder = builder.apply(address, priority);
                        holder.subscribe(eventBus);

                        returnList.add(holder);
                    }
                }
            });
        }
        catch (IOException e)
        {
            MJLogging.error("Could not process .json: " + entry.getKey());
        }

        return returnList;
    }

    static Map<ResourceLocation, Resource> getFiles(String filePath)
    {
        if(Constants.server == null)
        {
            return null;
        }

        ResourceManager resourceManager = Constants.server.getResourceManager();

        return resourceManager.listResources("magicjson/subscribe/" + filePath, path -> path.getPath().endsWith(".json"));
    }

}
