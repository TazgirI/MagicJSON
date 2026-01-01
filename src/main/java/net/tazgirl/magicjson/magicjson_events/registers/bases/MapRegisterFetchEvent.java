package net.tazgirl.magicjson.magicjson_events.registers.bases;

import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.magicjson.MJLogging;

import java.util.HashMap;
import java.util.Map;

public abstract class MapRegisterFetchEvent<K, V> extends RegisterFetchEventRoot<Map<K, V>>
{
    protected Map<K,V> registry = new HashMap<>();
    protected String identifier = getIdentifier();

    public abstract String getIdentifier();

    public V put(K key, V value)
    {
        V existing = registry.put(key,value);

        if(existing != null)
        {
            MJLogging.Debug("The MapRegister " + identifier + " just overrode the key `" + key.toString() + "` within the event fetch cycle with `" + value.toString() + "`, previous value was: " + existing);
        }

        return existing;
    }

    public void saveToRegistry(Map<K, V> saveLocation)
    {
        registry.forEach((key, value) ->
        {
            V existing = registry.put(key,value);

            if(existing != null)
            {
                MJLogging.Debug("The MapRegisterEvent " + identifier + " just overrode the existing key `" + key.toString() + "` in the corresponding register with `" + value.toString() + "`, previous value was: " + existing);
            }
        });
    }

    @Override
    public Map<K, V> getResult()
    {
        return registry;
    }

    @Override
    public Map<K, V> SendAndCollectEvent()
    {
        NeoForge.EVENT_BUS.post(this);
        return this.registry;
    }
}
