package net.tazgirl.magicjson.optionals;

import org.jetbrains.annotations.NotNull;

public interface IStatementOptional<T>
{
    T get();
    Object getRaw();

}
