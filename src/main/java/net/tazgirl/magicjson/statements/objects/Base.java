package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.NumericEvaluatorBase;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public abstract class Base
{

    protected StatementHolder holder;
    protected static String identifier;

    public Base(StatementHolder holder)
    {
        identifier = setIdentifier();
        this.holder = holder;
    }


    public abstract Object Resolve();

    @NotNull
    public abstract Boolean HandleValue(Object object);

    @NotNull
    public abstract Boolean HandleUniqueArgument(String string);


    public abstract Base ImplicitChild();

    @NotNull
    public abstract String setIdentifier();

    @Override
    public abstract String toString();

    public abstract Class<?>[] SoftResolve();

    public void DebugUnHandledType(Class<?> failedType)
    {
        Logging.Debug("Failed to handle Object of type \"" + failedType.toString() + "\" in a(n) " + identifier + " within: " + holder.getAddress());
    }
}
