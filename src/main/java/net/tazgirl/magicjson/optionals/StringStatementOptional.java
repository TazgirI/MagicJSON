package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.PrivateCore;
import org.jetbrains.annotations.NotNull;

public class StringStatementOptional implements IStatementOptional<String>, CharSequence, Comparable<String>
{
    public Object value;
    public String defaultValue;

    public StringStatementOptional(Object value, @NotNull String defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override
    public String get()
    {
        Object tempValue = value;
        if(tempValue instanceof String string && PrivateCore.hasStatement(string))
        {
            tempValue = PrivateCore.runStatement(string);
        }

        if(tempValue instanceof String parsedValue)
        {
            return parsedValue;
        }
        else
        {
            return defaultValue;
        }
    }

    @Override
    public Object getRaw()
    {
        return value;
    }

    @Override
    public int length()
    {
        return get().length();
    }

    @Override
    public char charAt(int index)
    {
        return get().charAt(index);
    }

    @Override
    public @NotNull CharSequence subSequence(int start, int end)
    {
        return get().substring(start,end);
    }

    // Infinite loop potential, I think, if passed a StringStatementOptional as a String (Or I'm too tired and that's only if I used o.compareTo(get()))
    @Override
    public int compareTo(@NotNull String o)
    {
        return get().compareTo(o);
    }

    @Override
    public @NotNull String toString()
    {
        return get();
    }
}
