package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.memory.Pointer;
import net.tazgirl.magicjson.optionals.tests.ResultTest;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CachedOptionalValue<T> extends OptionalValue<T>
{
    List<String> argValues;

    List<Pointer> editedValues = new ArrayList<>();

    Map<String, T> cachedResults = new HashMap<>();

    protected CachedOptionalValue(String stringValue, ResultTest<T> test)
    {
        super(stringValue, test);
        StatementHolder holder = MagicJson.getHolder(stringValue);
        editedValues = holder.getPointers();
    }

    protected CachedOptionalValue(T plainValue)
    {
        super(plainValue);
    }

    @Override
    public T get()
    {
        return getWithArgs(new HashMap<>());
    }

    @Override
    public T getWithArgs(Object... args)
    {
        if(this.isPlain())
        {
            return plainValue;
        }

        return getWithArgs(argMap(args));
    }

    @Override
    public T getWithArgs(Map<String, Object> argMap)
    {
        if(this.isPlain())
        {
            return plainValue;
        }

        StatementHolder holder = MagicJson.getHolder(stringValue);

        if(holder == null)
        {
            return null;
        }

        String key = editedValues.stream().collect(StringBuilder::new, (builder, pointer) -> builder.append(pointer.fetch(holder)), StringBuilder::append).toString();

        T cachedResult = cachedResults.get(key);

        if(cachedResult != null)
        {
            return cachedResult;
        }

        // Fully handles null and incorrect results

        T result = test.testAndCast(MagicJson.runStatement(stringValue, argMap));

        cachedResults.put(key, result);

        return result;
    }
}
