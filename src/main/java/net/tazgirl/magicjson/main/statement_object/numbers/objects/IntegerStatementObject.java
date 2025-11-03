package net.tazgirl.magicjson.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;

public class IntegerStatementObject extends NumberStatementObject<Integer>
{
    public IntegerStatementObject()
    {
        identifier = "Integer";
        numberType = Integer.class;
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            contentString = contentString.replace("I", "");
            contentString = contentString.replace("i", "");

            try
            {
                value = Integer.valueOf(contentString);
            }
            catch (NumberFormatException error)
            {
                Logging.Error("Attempted to convert the following String to a Integer but failed: " + contentString, MagicJson.LOGGER);
                return false;
            }
            return true;
        }

        return false;
    }

}
