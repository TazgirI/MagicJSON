package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class BooleanObject extends Base
{
    Boolean value;

    public BooleanObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        return value;
    }

    @Override
    public Boolean HandleValue(Object object)
    {
        if(object instanceof Boolean bool)
        {
            value = bool;
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
        return "Boolean";
    }

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[]{Boolean.class};
    }

}
