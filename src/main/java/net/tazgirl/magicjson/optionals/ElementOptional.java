package net.tazgirl.magicjson.optionals;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.PrivateCore;

public class ElementOptional
{

    public static StatementOptional<Integer> integer(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new StatementOptional<>(element.getAsInt(), 1);
        }
        catch (Exception ignored)
        {

        }

        return new StatementOptional<>(element.getAsString(), 1);
    }

    public static StatementOptional<String> string(JsonElement element)
    {

        try
        {
            return new StatementOptional<>(element.getAsString(), "");
        }
        catch (Exception ignored)
        {
            return null;
        }
    }


}
