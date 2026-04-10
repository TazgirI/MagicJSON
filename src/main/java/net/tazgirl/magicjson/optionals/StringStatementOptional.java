package net.tazgirl.magicjson.optionals;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.MagicJson;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class StringStatementOptional extends StatementOptional<String> implements IStatementOptional<String>, CharSequence, Comparable<String>
{
    public StringStatementOptional(OptionalValue<String> optionalValue, @NotNull String defaultValue)
    {
        super(optionalValue, defaultValue);
    }

    // This is so fucked up, but it is what it is, just leave in the documentation that String optionals need an _ if they are to be executed
    @Override
    public String get()
    {
        String raw = (String) optionalValue.getRaw();

        if(raw.charAt(0) == '_')
        {
            Object result = MagicJson.runStatement(((String) optionalValue.getRaw()).substring(1));
            if(result instanceof String)
            {
                return (String) result;
            }
        }

        return raw;
    }

    @Override
    public String getWithArgs(Map<String, Object> args)
    {
        String raw = (String) optionalValue.getRaw();

        if(raw.charAt(0) == '_')
        {
            Object result = MagicJson.runStatement(((String) optionalValue.getRaw()).substring(1), args);
            if(result instanceof String)
            {
                return (String) result;
            }
        }

        return raw;
    }

    @Override
    public String getWithArgs(Object[] args)
    {
        String raw = (String) optionalValue.getRaw();

        if(raw.charAt(0) == '_')
        {
            return getWithArgs(optionalValue.argMap(args));
        }

        return raw;
    }

    @Override
    public int length()
    {
        String result = optionalValue.get();
        return result == null ? -1 : result.length();
    }

    @Override
    public char charAt(int index)
    {

        return optionalValue.get().charAt(index);
    }

    @Override
    public @NotNull CharSequence subSequence(int start, int end)
    {
        return optionalValue.get().subSequence(start, end);
    }

    @Override
    public int compareTo(@NotNull String o)
    {
        return o.compareTo(optionalValue.get());
    }

    @Override
    public OptionalValue<String> getOptional()
    {
        return optionalValue;
    }
}
