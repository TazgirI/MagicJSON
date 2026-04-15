package net.tazgirl.magicjson.statements.objects.evaluation;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Equals extends Base
{
    List<Base> bases = new ArrayList<>();

    boolean not = false;

    public Equals(StatementHolder holder)
    {
        super(holder);
    }

    // Defaults to true if it has less than 2 bases
    // Will exit early upon first != (or ==)
    @Override
    public Object resolve()
    {
        if(bases.size() < 2)
        {
            return true;
        }

        boolean returnValue = true;

        Object firstResult = bases.getFirst().resolve();

        for(int i = 1; i < bases.size(); i++)
        {
            Object currentResult = bases.get(i).resolve();
            boolean equals = firstResult.equals(currentResult);

            if(not ? equals : !equals)
            {
                returnValue = false;
                break;
            }
        }

        return returnValue;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        bases.add(base);
        return true;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        if(string.equals("not"))
        {
            not = true;
            return true;
        }

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
        return "Equals";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + bases.toString() + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        bases = replaceInList(oldBase, newBase, bases);
    }
}
