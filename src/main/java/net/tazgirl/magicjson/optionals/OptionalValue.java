package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.optionals.tests.ResultTest;

import java.util.HashMap;
import java.util.Map;

public class OptionalValue<T>
{
    protected String stringValue = null;
    protected ResultTest<T> test;

    protected T plainValue = null;


    protected OptionalValue(String stringValue, ResultTest<T> test)
    {
        this.stringValue = stringValue;
        this.test = test;
    }

    protected OptionalValue(T plainValue)
    {
        this.plainValue = plainValue;
    }

    // NOTE: These two are protected to make sure people use the generic Object instructor, the specific ones are internal use only
    // *****************************************
    protected static <T> OptionalValue<T> from(String stringValue, ResultTest<T> test)
    {
        return new OptionalValue<>(stringValue, test);
    }

    protected static <T> OptionalValue<T> from(T plainValue)
    {
        return new OptionalValue<>(plainValue);
    }
    // *****************************************


    public static <T> OptionalValue<T> from(Object value, ResultTest<T> test)
    {
        if(value instanceof String)
        {
            return new OptionalValue<>((String) value, test);
        }
         else if(test.test(value))
        {
            return new OptionalValue<>((T) value);
        }

        return null;
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
        for(Object object : args)
        {
            argMap.put(classIncrement(object, argMap), object);
        }

        return argMap;
    }

    private String classIncrement(Object object, Map<String, Object> map)
    {
        int i = 1;
        String base = object.getClass().getSimpleName().toLowerCase();

        while(map.containsKey(base + i))
        {
            i++;
        }

        return base + i;
    }

    @Override
    public String toString()
    {
        String valueString = plainValue != null ? "literalValue: " + plainValue : "statementAdress: " + stringValue;
        return "OptionalValue{ " + valueString + " }";
    }
}
