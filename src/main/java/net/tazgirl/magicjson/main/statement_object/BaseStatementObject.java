package net.tazgirl.magicjson.main.statement_object;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.main.statement_object.interface_categories.BaseStatementInterface;
import org.checkerframework.checker.units.qual.N;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public abstract class BaseStatementObject implements BaseStatementInterface
{
    public String identifier = "";

    public StatementManager manager;

    public BaseStatementObject()
    {
        SetConstants();
    }

    public abstract void SetConstants();

    // One at time
    public abstract Boolean HandleValue(Object content);

    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {
        return null;
    }

    public abstract Object Resolve();

    public abstract void SpreadManager(StatementManager newManager);


    public BaseStatementObject NullCheckArgs(BaseStatementObject arg1, BaseStatementObject arg2)
    {
        return arg1 != null ? arg1 : arg2;
    }

    public String LogLocation()
    {
        return "Object: " + this.identifier + "\n" +
                "Statement address: " + this.manager.statementAddress.getLocalAddress();
    }

    @Override
    public abstract String toString();

    static Map<Class<? extends Number>,Function<Number, Number>> classMatch = Map.of(
            Integer.class, BaseStatementObject::IntegerConversion,
            Float.class, BaseStatementObject::FloatConversion,
            Long.class, BaseStatementObject::LongConversion,
            Double.class, BaseStatementObject::DoubleConversion
    );

    public static <T extends Number>  T AttemptNumberConversion(Class<T> expectedTypeClass, Number number)
    {
        return (T) classMatch.get(expectedTypeClass).apply(number);
    }

    public static Integer IntegerConversion(Number number)
    {
        number = Math.round(number.doubleValue());

        return number.intValue();
    }

    public static Float FloatConversion(Number number)
    {
        return number.floatValue();
    }

    public static Long LongConversion(Number number)
    {
        number = Math.round(number.doubleValue());

        return number.longValue();
    }

    public static Double DoubleConversion(Number number)
    {
        return number.doubleValue();
    }

    public BaseStatementObject ImplicitChild()
    {
        Logging.Debug("Attempted to fetch the implicit child of an object without one, \"(\" token has been skipped. (An implicit child is a \"(\" character without an object token such as \"ANDBOOL\" in front of it)", false);
        return null;
    }

}
