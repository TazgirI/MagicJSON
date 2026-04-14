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
    final Class<T> subscriptionClass;

    public EventSubscriptionHolder(String address, EventPriority priority, Class<T> subscriptionClass)
    {
        this.address = address;
        this.priority = priority;
        this.subscriptionClass = subscriptionClass;
    }

    abstract Map<String, Object> constructArgs(T event);

    public void subscribe(IEventBus bus)
    {
        bus.addListener(priority, subscriptionClass, this::consume);
    }

    public void consume(T event)
    {
        MagicJson.runStatement(address, constructArgs(event));
    }
}
