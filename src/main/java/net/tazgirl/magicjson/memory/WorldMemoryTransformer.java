package net.tazgirl.magicjson.memory;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class WorldMemoryTransformer
{

    public static Map<String, Function<String, Object>> stringTypeMatcherMap = Map.of(
            "integer", Integer::valueOf,
            "double", Double::valueOf,
            "float", Float::valueOf,
            "long", Long::valueOf,
            "string", WorldMemoryTransformer::stringReturn,
            "uuid", UUID::fromString

    );

    public static Map<Class<?>, String> classPrefixes = Map.of(
            Integer.class, "integer",
            Double.class, "double",
            Float.class, "float",
            Long.class, "long",
            String.class, "string",
            UUID.class, "uuid"

    );

    public static String objToString(Object object)
    {
        String prefix = classPrefixes.get(object.getClass());
       if(prefix != null)
       {
           return prefix + "/`" + object;
       }

        return null;
    }



    public static Object stringToObj(String string)
    {
        // test/`value returns 4
        int index = string.indexOf("/`");
        // test/`value returns test
        String typeString = string.substring(0, index);
        // test/`value returns value
        String checkString = string.substring(index + 2);

        return stringTypeMatcherMap.get(typeString).apply(checkString);
    }

    static Object stringReturn(String string)
    {
        return string;
    }


}
