package net.tazgirl.magicjson.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;

public class DoubleStatementObject extends NumberStatementObject<Double>
{
    @Override
    public void SetConstants()
    {
        identifier = "Double";
        numberType = Double.class;
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            contentString = contentString.replace("D", "");
            contentString = contentString.replace("d", "");

            try
            {
                value = Double.valueOf(contentString);
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
