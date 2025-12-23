package net.tazgirl.magicjson.statements.objects.complex_numeric;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class Random extends Base
{

    Base end;
    Base start;

    Class<?> type = Integer.class;

    public Random(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        if(end == null && start == null)
        {
            return 0;
        }

        java.util.Random random = new java.util.Random();

        if(end.Resolve() instanceof Number endNum)
        {
            if(type == null){type=endNum.getClass();}

            Number beginNum = 0;

            if(start != null && start.Resolve() instanceof Number startNum)
            {
                beginNum = startNum;
            }

            if(type == Double.class)
            {
                return random.nextDouble(beginNum.doubleValue(), endNum.doubleValue());
            }
            else if(type == Float.class)
            {
                return random.nextFloat(beginNum.floatValue(), endNum.floatValue());
            }
            else if(type == Long.class)
            {
                return random.nextLong(beginNum.longValue(), endNum.longValue());
            }
            else
            {
                return random.nextInt(beginNum.intValue(), endNum.intValue());
            }
        }

        return 0;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(end == null)
        {
            end = base;
            return true;
        }
        if(start == null)
        {
            start = base;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        switch (string)
        {
            case ".int", ".integer" -> type = Integer.class;
            case ".dub", ".double" -> type = Double.class;
            case ".float" -> type = Float.class;
            case ".long" -> type = Long.class;
            default ->
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Random";
    }

    @Override
    public String toString()
    {
        return identifier + "." + type.toString().substring(0,type.toString().indexOf('.')) + "( " + end + " " + start + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(end == oldBase)
        {
            end = newBase;
        }
        if(start == oldBase)
        {
            start = newBase;
        }
    }
}
