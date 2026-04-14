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
    public Object resolve()
    {
        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
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
        return "Null";
    }

    @Override
    public String toString()
    {
        return identifier;
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {

    }

}
