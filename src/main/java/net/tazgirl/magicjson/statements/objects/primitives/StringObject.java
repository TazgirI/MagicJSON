package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class StringObject extends Base
{
    String value;

    public StringObject(StatementHolder holder)
    {
        super(holder);
    }

    public StringObject(StatementHolder holder, String value)
    {
        super(holder);
        this.value = value;
    }

    @Override
    public Object Resolve()
    {
        return value;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        return false;
    }

    @Override
    public @NotNull Boolean HandleObject(Object object)
    {
        if(object instanceof String string)
        {
            value = string;
            return true;
        }
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
        return "String";
    }

    @Override
    public String toString()
    {
        return "\"" + value + "\"";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {

    }
}
