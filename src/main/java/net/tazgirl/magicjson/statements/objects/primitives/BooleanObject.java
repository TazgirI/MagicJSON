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
    public Object resolve()
    {
        return value;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        return false;
    }

    @Override
    public @NotNull Boolean HandleObject(Object object)
    {
        if(object instanceof Boolean bool)
        {
            value = bool;
            return true;
        }
        debugUnHandledType(object.getClass());
        return false;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public Base implicitChild()
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
    public void replace(Base oldBase, Base newBase)
    {

    }
}
