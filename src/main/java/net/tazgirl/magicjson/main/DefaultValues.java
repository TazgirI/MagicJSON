package net.tazgirl.magicjson.main;

import java.util.Map;

public class DefaultValues
{
    public static Map<Class<? extends Number>, Number> defaultsMap = Map.of(
            Integer.class, 0,
            Float.class, 0f,
            Double.class, 0.0d,
            Long.class, 0L
    );

    public static Number getDefault(Class<? extends Number> checkClass)
    {
        return defaultsMap.get(checkClass);
    }

}
