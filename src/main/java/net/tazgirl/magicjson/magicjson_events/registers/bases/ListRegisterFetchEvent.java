package net.tazgirl.magicjson.magicjson_events.registers.bases;

import jdk.jfr.Event;
import net.neoforged.neoforge.common.NeoForge;

import java.util.ArrayList;
import java.util.List;

public abstract class ListRegisterFetchEvent<T> extends RegisterFetchEventRoot<List<T>>
{
    protected List<T> registry = new ArrayList<>();
    protected String identifier = getIdentifier();

    public abstract String getIdentifier();

    public void put(T value)
    {
        registry.add(value);
    }

    public void saveToRegistry(List<T> saveLocation)
    {
        saveLocation.addAll(registry);
    }

    @Override
    public List<T> SendAndCollectEvent()
    {
        NeoForge.EVENT_BUS.post(this);
        return this.registry;
    }
}
