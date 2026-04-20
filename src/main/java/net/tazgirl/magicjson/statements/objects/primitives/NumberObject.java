package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.helpers.NumberHandling;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitive_adjacent.ClassObject;
import org.jetbrains.annotations.NotNull;

public abstract class NumberObject<T extends Number> extends Base
{
    T value;
    Class<? extends Number> typeClass;

    public NumberObject(StatementHolder holder)
    {
        super(holder);
        typeClass = setType();
    }

    public NumberObject(StatementHolder holder, T value)
    {
        super(holder);
        typeClass = setType();
        this.value = value;
    }

    protected abstract Class<? extends Number> setType();

    @Override
    public T resolve()
    {
        return value;
    }

    @Override
    public @NotNull Boolean HandleObject(Object object)
    {
        if(object instanceof Number number)
        {
            value = (T) NumberHandling.getNumberAsType(typeClass, number);
            return true;
        }
        return false;
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
    public String toString()
    {
        return value.toString() + identifier.toLowerCase().charAt(0);
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {

    }
}
