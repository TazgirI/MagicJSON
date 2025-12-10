package net.tazgirl.magicjson.registration.registers.base;

import java.util.HashMap;
import java.util.Map;

public abstract class MapRegister<K, V> extends Register<Map<K,V>>
{
    protected final Map<K, V> register = new HashMap<>();

    public V put(K key, V value)
    {
        return register.put(key, value);
    }

    public Boolean containsKey(K key)
    {
        return register.containsKey(key);
    }

    public V get(K key)
    {
        return register.get(key);
    }

    @Override
    public void putEventOutput(Map<K,V> eventOutput)
    {
        register.putAll(eventOutput);
    }
}
