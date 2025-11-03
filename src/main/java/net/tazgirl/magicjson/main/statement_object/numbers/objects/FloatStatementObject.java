package net.tazgirl.magicjson.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;

public class FloatStatementObject extends NumberStatementObject<Float>
{
    public FloatStatementObject()
    {
        identifier = "Float";
        numberType = Float.class;
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            contentString = contentString.replace("F", "");
            contentString = contentString.replace("f", "");

            try
            {
                value = Float.valueOf(contentString);
            }
            catch (NumberFormatException error)
            {
                Logging.Error("Attempted to convert the following String to a Float but failed: " + contentString, MagicJson.LOGGER);
                return false;
            }
            return true;
        }

        return false;
    }

}
