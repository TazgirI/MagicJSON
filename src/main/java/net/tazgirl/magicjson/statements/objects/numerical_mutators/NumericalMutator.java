package net.tazgirl.magicjson.statements.objects.numerical_mutators;

import net.tazgirl.magicjson.helpers.NumberHandling;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class NumericalMutator extends Base
{
    Class<? extends Number> prefferedReturnType = null;

    List<Base> values = new ArrayList<>();

    BiFunction<Number, Number, Number> function;

    public NumericalMutator(StatementHolder holder)
    {
        super(holder);
        function = initMyFunction();
    }

    abstract BiFunction<Number, Number, Number> initMyFunction();

    @Override
    public Number Resolve()
    {
        List<Number> finalNumbers = new ArrayList<>();

        Number returnNum = 0;

        if(values.getFirst().Resolve() instanceof Number number)
        {
            returnNum = number;
            finalNumbers.add(number);
        }

        for(int i = 1; i < values.size(); i++)
        {
            Base base = values.get(i);

            if(base.Resolve() instanceof Number loopNum)
            {
                returnNum = function.apply(returnNum, loopNum);
                finalNumbers.add(loopNum);
            }
        }


        if(prefferedReturnType == null)
        {
            prefferedReturnType =  NumberHandling.findReturnType(finalNumbers);
        }

        return NumberHandling.getNumberAsType(prefferedReturnType, returnNum);
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        values.add(base);
        holder.AddRelationship(this, base);
        return true;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        if(NumberHandling.numberClassStrings.get(string) instanceof Class<? extends Number> numberClass)
        {
            prefferedReturnType = numberClass;
            return true;
        }
        return false;
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        int index = values.indexOf(oldBase);
        if(index != -1)
        {
            values.set(index, newBase);
        }
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return identifier + "( " + values.toString() + " )";
    }
}
