package net.tazgirl.magicjson.optionals;

public interface IStatementOptional<T>
{
    T get();
    T getWithArg(Object object);
    Object getRaw();
}
