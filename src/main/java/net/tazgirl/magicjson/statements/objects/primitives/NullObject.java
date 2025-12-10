package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class NullObject extends Base
{
    public NullObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        return null;
    }

    @Override
    public @NotNull Boolean HandleValue(Object object)
    {
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
        return "Null";
    }

    @Override
    public String toString()
    {
        return identifier;
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[]{NullObject.class};
    }
}
