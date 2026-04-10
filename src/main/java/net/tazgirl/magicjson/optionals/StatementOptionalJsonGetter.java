package net.tazgirl.magicjson.optionals;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.tazgirl.magicjson.MJLogging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public record StatementOptionalJsonGetter<T>(String key, Class<T> type)
{
    // Just don't touch and it works fine, the unsafe cast is a lie
    static Map<Class<?>, Function<JsonElement, IStatementOptional<?>>> deserializers = Map.of(
            Double.class, OptionalFrom::DOUBLE,
            Integer.class, OptionalFrom::INT,
            String.class, OptionalFrom::STRING,
            Boolean.class, OptionalFrom::BOOL,
            Long.class, OptionalFrom::LONG,
            Float.class, OptionalFrom::FLOAT);

    public T read(JsonObject jsonObject)
    {
        Function<JsonElement, T> deserializer = (Function<JsonElement, T>) deserializers.get(type);
        if(deserializer != null)
        {
            return deserializer.apply(jsonObject.get(key));
        }

        return null;
    }

    public static Map<String, Object> getAll(List<StatementOptionalJsonGetter<?>> getters, JsonObject jsonObject)
    {
        Map<String, Object> returnMap = new HashMap<>();
        for(StatementOptionalJsonGetter<?> getter : getters)
        {
            Object value = getter.read(jsonObject);
            if(value == null)
            {
                MJLogging.Debug("Attempted to read \"" + getter.key + "\" from JsonObject but found a null value");
                return null;
            }

            returnMap.put(getter.key, value);
        }



        return returnMap;
    }

    public static <E> StatementOptionalJsonGetter<E> getFrom(List<StatementOptionalJsonGetter<?>> getters, String key, Class<E> type)
    {
        for(StatementOptionalJsonGetter<?> getter : getters)
        {
            if(getter.key.equals(key))
            {
                return (StatementOptionalJsonGetter<E>) getter;
            }
        }

        return null;
    }

}
