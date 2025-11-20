package net.tazgirl.magicjson.old.main.statement_object.numbers.mutators;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.StatementManager;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesNumber;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class NumberMutatorStatementObject<T extends Number> extends BaseStatementObject
{
    protected List<ResolvesNumber<?>> contents = new ArrayList<>();

    protected BiFunction<Number, Number, Number> operation;

    protected Class<T> attemptedReturnType;

    @Override
    public Boolean HandleValue(@NotNull Object content)
    {
        if(content instanceof ResolvesNumber<?> resolvesNumber)
        {
            contents.add(resolvesNumber);
            return true;
        }
        return false;
    }


    @Override
    public Object Resolve()
    {
        if(contents.size() == 1)
        {
            return contents.getFirst().Resolve();
        }
        else if(contents.isEmpty())
        {
            return 0;
        }

        T total = AttemptNumberConversion(attemptedReturnType, operation.apply(contents.get(0).Resolve(), contents.get(1).Resolve()));

        for(int i = 2; i < contents.size(); i++)
        {
            total = AttemptNumberConversion(attemptedReturnType, operation.apply(total, contents.get(i).Resolve()));
        }

        return total;
    }

    @Override
    public String toString()
    {
        return identifier + "(" + contents.toString() + ")";
    }

    @Override
    public void SpreadManager(@NotNull StatementManager newManager)
    {
        manager = newManager;

        contents.forEach(resolvesNumber -> resolvesNumber.SpreadManager(newManager));
    }
}
