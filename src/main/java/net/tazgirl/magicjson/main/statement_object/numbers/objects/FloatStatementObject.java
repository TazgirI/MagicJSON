package net.tazgirl.magicjson.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;

public class FloatStatementObject extends NumberStatementObject<Float>
{
    @Override
    public void SetConstants()
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
                Logging.Warn(FailedConversionMessage(contentString),false);
                return false;
            }
            return true;
        }

        return false;
    }

}
