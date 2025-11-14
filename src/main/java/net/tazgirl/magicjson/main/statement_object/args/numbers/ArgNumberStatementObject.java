package net.tazgirl.magicjson.main.statement_object.args.numbers;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.main.DefaultValues;
import net.tazgirl.magicjson.main.statement_object.args.ArgStatementObject;

import java.util.Map;

public class ArgNumberStatementObject<T extends Number> extends ArgStatementObject
{

    protected Class<T> numberClass;
    protected String numberIdentifier;


    public ArgNumberStatementObject(Class<T> myNewClass, String myNewNumberType)
    {
        numberClass = myNewClass;
        numberIdentifier = myNewNumberType;

        identifier = "ArgNumberStatementObject (this object is abstract, if you see this identifier then something has broken)";
    }

    @Override
    public T Resolve()
    {
        Map<String, Object> args = manager.getArgs();

        Object keyValue = args.get(value);
        if(numberClass.isInstance(keyValue))
        {
            return (T) keyValue;
        }
        else if(keyValue == null)
        {
            String errorMessage = "Could not find the key \"" + value + "\" in provided args: \"" + args.toString() + "\" \n" + LogLocation();

            Logging.Warn(errorMessage, false);
        }
        else
        {
            String errorMessage = "The key \"" + value + "\" did not return " + numberIdentifier + " from provided args: \"" + args.toString() + "\"\n" + LogLocation();
            Logging.Warn(errorMessage, false);
        }

        return (T) DefaultValues.getDefault(numberClass);
    }
}
