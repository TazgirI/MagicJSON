package net.tazgirl.magicjson.old.main.statement_object.numbers.objects;

import net.tazgirl.magicjson.old.main.DefaultValues;
import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.StatementManager;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.NumericalEvaluator;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.PrimitiveObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesNumber;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public abstract class NumberStatementObject<T extends Number> extends BaseStatementObject implements ResolvesNumber<T>, NumericalEvaluator, PrimitiveObject
{

    T value = null;
    protected Class<T> numberType;


/*    public NumberObject(T value, Class<T> numberType)
    {
        this.value = value;
        this.numberType = numberType;
    }*/

    @Override
    public Boolean numericalTest(@NotNull BiFunction<Number, Number, Boolean> test, @NotNull BaseStatementObject operand, @NotNull Boolean operandIsLeft)
    {
        Object operandResult = operand.Resolve();

        if(operandResult instanceof Number operandValue)
        {
            if(operandIsLeft)
            {
                return test.apply(operandValue, value);
            }

            return test.apply(value, operandValue);
        }


        return operand.numericalTest(test, this, !operandIsLeft);
    }

    public Class<T> getNumberType()
    {
        return numberType;
    }

    // If the warning is somehow valid then tests will catch it before it makes it to prod
    @SuppressWarnings("unchecked")
    @Override
    public T Resolve()
    {
        if(value != null)
        {
            return value;
        }

        return (T) DefaultValues.getDefault(numberType);
    }

    @Override
    public String toString()
    {
        return identifier + "( " + value.toString() + " )";
    }

    public String FailedConversionMessage(String source)
    {
        return "Attempted to convert the following String to a " + identifier + " but failed: " + source;
    }

    @Override
    public void SpreadManager(@NotNull StatementManager newManager)
    {
        manager = newManager;
    }
}
