package net.tazgirl.magicjson.statements.objects.StringMutators;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Concat extends Base
{

    List<Base> values = new ArrayList<>();

    public Concat(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (Base base: values)
        {
            stringBuilder.append(base.resolve());
        }

        return stringBuilder.toString();
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        values.add(base);
        return true;
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
        return "Concat";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + values + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        if (values.contains(oldBase))
        {
            values.set(values.indexOf(oldBase), newBase);
        }
    }
}
