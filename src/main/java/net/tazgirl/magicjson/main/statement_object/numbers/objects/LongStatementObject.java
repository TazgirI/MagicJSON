package net.tazgirl.magicjson.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;

public class LongStatementObject extends NumberStatementObject<Long>
{

    public LongStatementObject()
    {
        identifier = "Long";
        numberType = Long.class;
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            contentString = contentString.replace("L", "");
            contentString = contentString.replace("l", "");

            try
            {
                value = Long.valueOf(contentString);
            }
            catch (NumberFormatException error)
            {
                Logging.Error("Attempted to convert the following String to a Long but failed: " + contentString, MagicJson.LOGGER);
                return false;
            }
            return true;
        }

        return false;
    }
}
