package net.tazgirl.magicjson.optionals;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.optionals.numbers.DoubleStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.FloatStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.IntegerStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.LongStatementOptional;

public class OptionalFromElement
{


    public static <T> Comparable<T> get(JsonElement element, Class<T> checkClass)
    {
        if(checkClass == Integer.class)
        {
            return (Comparable<T>) INT(element, 0);
        }
        if(checkClass == Float.class)
        {
            return (Comparable<T>) FLOAT(element, 0f);
        }
        if(checkClass == Double.class)
        {
            return (Comparable<T>) DOUBLE(element, 0.0);
        }
        if(checkClass == Long.class)
        {
            return (Comparable<T>) LONG(element, 0L);
        }
        if(checkClass == String.class || checkClass == CharSequence.class)
        {
            return (Comparable<T>) STRING(element, "");
        }


        return null;
    }


    public static IntegerStatementOptional INT(JsonElement element, Integer defaultValue)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new IntegerStatementOptional(element.getAsInt(), defaultValue);
        }
        catch (Exception ignored)
        {

        }

        return new IntegerStatementOptional(element.getAsString(), defaultValue);
    }

    public static FloatStatementOptional FLOAT(JsonElement element, Float defaultValue)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new FloatStatementOptional(element.getAsFloat(), defaultValue);
        }
        catch (Exception ignored)
        {

        }

        return new FloatStatementOptional(element.getAsString(), defaultValue);
    }

    public static DoubleStatementOptional DOUBLE(JsonElement element, Double defaultValue)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new DoubleStatementOptional(element.getAsDouble(), defaultValue);
        }
        catch (Exception ignored)
        {

        }

        return new DoubleStatementOptional(element.getAsString(), defaultValue);
    }

    public static LongStatementOptional LONG(JsonElement element, Long defaultValue)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new LongStatementOptional(element.getAsLong(), defaultValue);
        }
        catch (Exception ignored)
        {

        }

        return new LongStatementOptional(element.getAsString(), defaultValue);
    }

    public static StringStatementOptional STRING(JsonElement element, String defaultValue)
    {
        try
        {
            return new StringStatementOptional(element.getAsString(), defaultValue);
        }
        catch (Exception ignored)
        {

        }

        return null;
    }


}
