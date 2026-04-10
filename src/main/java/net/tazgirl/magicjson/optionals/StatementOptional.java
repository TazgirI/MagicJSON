package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.PrivateCore;
import org.jetbrains.annotations.NotNull;

public class StatementOptional<T>
{
    public OptionalValue<T> optionalValue;
    public T defaultValue;

    public StatementOptional(OptionalValue<T> optionalValue, @NotNull T defaultValue)
    {
        this.optionalValue = optionalValue;
        this.defaultValue = defaultValue;
    }

    public T get()
    {
        return optionalValue.get();
    }

    public Object getRaw()
    {
        return optionalValue.getRaw();
    }
}
