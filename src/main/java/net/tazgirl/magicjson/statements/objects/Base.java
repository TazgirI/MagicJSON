package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.MJLogging;
import org.jetbrains.annotations.NotNull;

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

    @NotNull
    public abstract Boolean HandleBase(Base base);

    @NotNull
    public Boolean HandleObject(Object object)
    {
        return false;
    }

    @NotNull
    public abstract Boolean HandleUniqueArgument(String string);


    public abstract Base ImplicitChild();

    @NotNull
    public abstract String setIdentifier();

    @Override
    public abstract String toString();

    public abstract void Replace(Base oldBase, Base newBase);

    public void DebugUnHandledType(Class<?> failedType)
    {
        MJLogging.Debug("Failed to handle Object of type \"" + failedType.toString() + "\" in a(n) " + identifier + " within: " + holder.getAddress());
    }

    public void DebugUnHandledType(Object object)
    {
        DebugUnHandledType(object.getClass());
    }
}
