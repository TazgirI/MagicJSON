package net.tazgirl.magicjson.main.statement_object.args.numbers;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.main.DefaultValues;
import net.tazgirl.magicjson.main.statement_object.args.ArgStatementObject;

public class ArgNumberStatementObject<T extends Number> extends ArgStatementObject
{

    protected Class<T> numberClass;
    protected String numberIdentifier;


    public ArgNumberStatementObject(Class<T> myNewClass, String myNewNumberType)
    {
        numberClass = myNewClass;
        numberIdentifier = myNewNumberType;
    }

    @Override
    public T Resolve()
    {
        Object keyValue = args.get(value);
        if(numberClass.isInstance(keyValue))
        {
            return (T) keyValue;
        }
        else if(keyValue == null)
        {
            Logging.Error("Could not find the key \"" + value + "\" in provided args: \"" + args.toString() + "\"", MagicJson.LOGGER);
        }
        else
        {
            Logging.Error("The key \"" + value + "\" did not return " + numberIdentifier + " from provided args: \"" + args.toString() + "\"", MagicJson.LOGGER);
        }

        return (T) DefaultValues.getDefault(numberClass);
    }
}
