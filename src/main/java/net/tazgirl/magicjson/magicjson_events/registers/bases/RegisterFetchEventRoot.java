package net.tazgirl.magicjson.magicjson_events.registers.bases;

import net.neoforged.bus.api.Event;

import java.util.Set;

public abstract class RegisterFetchEventRoot<T> extends Event
{
    public abstract T SendAndCollectEvent();

    public abstract T getResult();
}
