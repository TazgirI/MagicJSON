package net.tazgirl.magicjson.main.hook_object;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.main.addresses.FunctionAddress;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.FunctionManager;
import net.tazgirl.magicjson.main.statement_object.StatementManager;

public record HookParameter(String name, Object value)
{

    // If you want the intended value then use fetchValue, if you believe your hook may be used to handle a string literal of an Address then use fetchValueTrue

    // Fetch that checks if value is a Statement or Function and runs it if so
    public Object fetchValue(StatementManager manager)
    {
        if(value instanceof String string)
        {
            if (PrivateCore.hasStatement(string))
            {
                return MagicJson.RunStatement(StatementAddress.from(string), manager);
            }
            if (PrivateCore.hasFunction(string))
            {
                return MagicJson.RunFunction(PrivateCore.getFunction(string),new FunctionManager(FunctionAddress.from(string),manager.getArgs()));
            }
        }

        return value;
    }

    // Fetch that will return value even if it contains an Address string
    public Object fetchValueTrue()
    {
        return value;
    }
}
