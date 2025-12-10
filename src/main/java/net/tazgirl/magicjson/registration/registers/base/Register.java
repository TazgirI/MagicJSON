package net.tazgirl.magicjson.registration.registers.base;

import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;

public abstract class Register<T>
{
    public final String identifier = getIdentifier();

    protected abstract String getIdentifier();

    public abstract RegisterFetchEventRoot<?> getFreshStaticEvent();

    public abstract void putEventOutput(T eventOutput);

    public void fireAndStoreStaticEvent()
    {
        RegisterFetchEventRoot<?> event = getFreshStaticEvent();
        putEventOutput((T) event.SendAndCollectEvent());
    }
}
