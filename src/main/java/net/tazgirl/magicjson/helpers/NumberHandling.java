package net.tazgirl.magicjson.helpers;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NumberHandling
{
    public static Map<String, Class<? extends Number>> numberClassStrings = Map.of(
            ".integer", Integer.class,
            ".float", Float.class,
            ".long", Long.class,
            ".double", Double.class
    );

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



    static Map<Class<?>, Integer> priorityMap = Map.of(
            Integer.class, 1,
            Float.class, 2,
            Long.class, 4,
            Double.class, 3
    );

    public static Class<? extends Number> findReturnType(Class<? extends Number> input1, Class<? extends Number> input2)
    {
        int input1Priority = priorityMap.get(input1);
        int input2Priority = priorityMap.get(input2);

        if(input1Priority < input2Priority)
        {
            return input2;
        }

        return input1;
    }

    public static Class<? extends Number> findReturnType(Number input1, Number input2)
    {
        return findReturnType(input1.getClass(), input2.getClass());
    }

    public static Class<? extends Number> findReturnType(List<Number> inputs)
    {
        int priority = 0;
        Number heaviestNumber = inputs.getFirst();
        for(Number number: inputs)
        {
            if(priorityMap.get(number.getClass()) instanceof Integer integer && integer > priority)
            {
                priority = integer;
                heaviestNumber = number;
            }
            if(priority == 4){break;}
        }

        return heaviestNumber.getClass();
    }


}
