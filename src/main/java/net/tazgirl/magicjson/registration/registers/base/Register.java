package net.tazgirl.magicjson.registration.registers.base;

import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;

public abstract class Register<T>
{
    protected final String identifier = getIdentifier();

    protected abstract String getIdentifier();

    public abstract RegisterFetchEventRoot<?> getFreshStaticEvent();

    public T fireStaticEvent()
    {
        RegisterFetchEventRoot<?> event = getFreshStaticEvent();
        return (T) event.SendAndCollectEvent();
    }


}
