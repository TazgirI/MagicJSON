package net.tazgirl.magicjson.old.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.Logging;
import org.jetbrains.annotations.NotNull;

public class IntegerStatementObject extends NumberStatementObject<Integer>
{
    @Override
    public void SetConstants()
    {
        identifier = "Integer";
        numberType = Integer.class;
    }

    @Override
    public Boolean HandleValue(@NotNull Object content)
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
                Logging.Warn(FailedConversionMessage(contentString),false);
                return false;
            }
            return true;
        }

        return false;
    }

}
