package net.tazgirl.magicjson.subscription;

import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.subscription.base.ExecutableAddress;

import java.util.Map;

public abstract class EventSubscriptionHolder<T extends Event>
{
    final EventPriority priority;
    final String address;

    public EventSubscriptionHolder(String address, EventPriority priority)
    {
        this.address = address;
        this.priority = priority;
    }

    abstract Map<String, Object> constructArgs(T event);

    public void subscribe(IEventBus bus, Class<T> type)
    {
        bus.addListener(priority, type, this::consume);
    }

    public void consume(T event)
    {
        MagicJson.runStatement(address, constructArgs(event));
    }
}
