package net.tazgirl.magicjson.helpers;

import com.google.gson.JsonElement;
import net.neoforged.bus.api.EventPriority;
import net.tazgirl.magicjson.MJLogging;
import org.jetbrains.annotations.NotNull;

public class EventPriorityFrom
{
    public static EventPriority integer(int i)
    {
        return switch(i)
        {
            case 1 -> EventPriority.HIGHEST;
            case 2 -> EventPriority.HIGH;
            case 3 -> EventPriority.NORMAL;
            case 4 -> EventPriority.LOW;
            case 5 -> EventPriority.LOWEST;
            default ->
            {
                MJLogging.debug("Tried to turn integer into EventPriority that was not 1-5 inclusive, integer: " + i);
                yield null;
            }
        };
    }

    public static EventPriority string(String s)
    {
        return switch(s)
        {
            case "HIGHEST" -> EventPriority.HIGHEST;
            case "HIGH" -> EventPriority.HIGH;
            case "NORMAL" -> EventPriority.NORMAL;
            case "LOW" -> EventPriority.LOW;
            case "LOWEST" -> EventPriority.LOWEST;
            default ->
            {
                MJLogging.debug("Tried to turn String into EventPriority that was not HIGH(EST), NORMAL or LOW(EST), string: " + s);
                yield null;
            }
        };
    }

    @NotNull
    public static EventPriority element(JsonElement element)
    {
        EventPriority returnValue = EventPriority.NORMAL;

        try
        {
            returnValue = integer(element.getAsInt());
            if(returnValue != null)
            {
                return returnValue;
            }
        }
        catch(Exception ignored)
        {

        }

        try
        {
            returnValue = string(element.getAsString());
            if(returnValue != null)
            {
                return returnValue;
            }
        }
        catch(Exception ignored)
        {

        }

        MJLogging.debug("Failed to turn provided JsonElement into an EventPriority, reverting to NORMAL");
        return returnValue;
    }
}
