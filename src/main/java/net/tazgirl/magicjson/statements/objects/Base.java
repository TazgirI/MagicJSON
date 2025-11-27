package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.Logging;

public abstract class Base
{

    protected StatementHolder holder;
    protected static String identifier;

    public Base(StatementHolder holder)
    {
        identifier = setIdentifier();
        this.holder = holder;
    }

    public abstract Object Resolve();

    public abstract Boolean HandleValue(Object object);

    public abstract Boolean HandleUniqueArgument(Object object);

    public abstract Base ImplicitChild();


    public abstract String setIdentifier();

    @Override
    public abstract String toString();

    public abstract Class<?>[] SoftResolve();

    public void DebugUnHandledType(Class<?> failedType)
    {
        Logging.Debug("Failed to handle Object of type \"" + failedType.toString() + "\" in a(n) " + identifier + " within: " + holder.getAddress());
    }
}
