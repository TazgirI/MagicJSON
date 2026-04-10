package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.optionals.tests.ResultTest;

import java.util.HashMap;
import java.util.Map;

public class OptionalValue<T>
{
    private String stringValue = null;
    private ResultTest<T> test;

    private T plainValue = null;


    private OptionalValue(String stringValue, ResultTest<T> test)
    {
        this.stringValue = stringValue;
        this.test = test;
    }

    private OptionalValue(T plainValue)
    {
        this.plainValue = plainValue;
    }

    public static <T> OptionalValue<T> from(String stringValue, ResultTest<T> test)
    {
        return new OptionalValue<>(stringValue, test);
    }

    public static <T> OptionalValue<T> from(T plainValue)
    {
        return new OptionalValue<>(plainValue);
    }

    public T get()
    {
        return getWithArgs();
    }

    public T getWithArgs(Object... args)
    {
        if(plainValue != null)
        {
            return plainValue;
        }

        Object result;

        if(args != null && args.length > 0)
        {
            result = MagicJson.runStatement(stringValue, argMap(args));
        }
        else
        {
            result = MagicJson.runStatement(stringValue);
        }

        if(test.test(result))
        {
            return (T) result;
        }

        return null;
    }

    public T getWithArgs(Map<String, Object> argMap)
    {
        if(plainValue != null)
        {
            return plainValue;
        }

        Object result = MagicJson.runStatement(stringValue, argMap);

        if(test.test(result))
        {
            return (T) result;
        }

        return null;
    }

    public Object getRaw()
    {
        return plainValue != null ? plainValue : stringValue;
    }

    public boolean isPlain()
    {
        return plainValue != null;
    }

    public boolean isStatement()
    {
        return test != null;
    }

    public String getAddress()
    {
        return stringValue;
    }

    public Map<String, Object> argMap(Object[] args)
    {
        Map<String, Object> argMap = new HashMap<>();
        for(int i = 0; i < args.length; i++)
        {
            argMap.put("arg" + i, args[i]);
        }

        return argMap;
    }

    @Override
    public String toString()
    {
        String valueString = plainValue != null ? "literalValue: " + plainValue : "statementAdress: " + stringValue;
        return "OptionalValue{ " + valueString + " }";
    }
}
