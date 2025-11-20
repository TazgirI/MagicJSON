package net.tazgirl.magicjson.old.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.Logging;

public class LongStatementObject extends NumberStatementObject<Long>
{
    @Override
    public void SetConstants()
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
                Logging.Warn(FailedConversionMessage(contentString),false);
                return false;
            }
            return true;
        }

        return false;
    }


}
