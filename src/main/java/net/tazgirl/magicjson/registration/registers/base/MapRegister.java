package net.tazgirl.magicjson.registration.registers.base;

import net.tazgirl.magicjson.MJLogging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MapRegister<K, V> extends Register<Map<K,V>>
{
    protected final Map<K, V> register = new HashMap<>();

    protected List<MapRegister<K, ?>> registersToDuplicateCheck = List.of();

    public V put(K key, V value)
    {
        boolean clear = duplicateSafety(key, registersToDuplicateCheck);

        if(clear)
        {
            return register.put(key, value);
        }

        return null;
    }

    public Boolean containsKey(K key)
    {
        return register.containsKey(key);
    }

    public V get(K key)
    {
        return register.get(key);
    }

    public boolean duplicateSafety(K key, List<MapRegister<K, ?>> registersToCheck)
    {
        if(registersToCheck.isEmpty())
        {
            return true;
        }

        String fail = "";
        for(MapRegister<K, ?> register : registersToCheck)
        {
            if(register.containsKey(key))
            {
                fail = register.identifier;
                break;
            }
        }

        if(fail.isEmpty())
        {
            return true;
        }

        MJLogging.crossRegisterDuplicateLog(identifier, fail);
        return false;

    }

    @Override
    public void putEventOutput(Map<K,V> eventOutput)
    {
        for(Map.Entry<K, V> entry : eventOutput.entrySet())
        {
            put(entry.getKey(), entry.getValue());
        }
    }
}
