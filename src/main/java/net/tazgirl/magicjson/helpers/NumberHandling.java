package net.tazgirl.magicjson.helpers;

import java.util.Map;
import java.util.function.Function;

public class NumberHandling
{

    public static Map<Class<? extends Number>, Function<Number, Number>> requestedTypeToFunction = Map.of(
            Integer.class, NumberHandling::numberToInteger,
            Float.class, NumberHandling::numberToFloat,
            Long.class, NumberHandling::numberToLong,
            Double.class, NumberHandling::numberToDouble
    );


    public static <T extends Number> T getNumberAsType(Class<T> requestType, Number number)
    {
        if(!requestedTypeToFunction.containsKey(requestType)){return null;}

        return (T) requestedTypeToFunction.get(requestType).apply(number);
    }


    public static Integer numberToInteger(Number number)
    {
        number = Math.round(number.doubleValue());

        return number.intValue();
    }

    public static Float numberToFloat(Number number)
    {
        return number.floatValue();
    }

    public static Long numberToLong(Number number)
    {
        number = Math.round(number.doubleValue());

        return number.longValue();
    }

    public static Double numberToDouble(Number number)
    {
        return number.doubleValue();
    }
}
