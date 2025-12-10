package net.tazgirl.magicjson.statements.objects;

import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;

public class Arg extends Base
{
    public Base argumentName;

    public Arg(StatementHolder holder)
    {
        super(holder);
    }

    public Arg(StatementHolder holder, Base argumentName)
    {
        super(holder);
        this.argumentName = argumentName;
    }

    @Override
    public Object Resolve()
    {
        if(argumentName.Resolve() instanceof String string)
        {
            return holder.args.get(string);
        }
        return null;
    }

    @Override
    public @NotNull Boolean HandleValue(Object object)
    {
        if(object instanceof Base base)
        {
            argumentName = base;
            return true;
        }
        DebugUnHandledType(object.getClass());
        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Arg";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + argumentName.toString() + " )";
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return null;
    }
}
