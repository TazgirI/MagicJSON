package net.tazgirl.magicjson.memory;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import java.util.HashMap;
import java.util.Map;

public class Memory
{
    public static Map<String, Object> runtimeMemory = new HashMap<>();

    public static WorldMemory worldMemory = WorldMemory.getForOverworld();

    public static Map<String, Object> getRuntimeMemory()
    {
        return runtimeMemory;
    }

    public static Map<String, Object> getWorldMemoryMap()
    {
        return worldMemory.getWorldMemoryMap();
    }


    // Runtime memory object
    // World memory tag system, add, get, set and IsValid (need a way for people to check if data can go in tag) objects

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void OnServerStopped(ServerStartedEvent event)
    {
        runtimeMemory = new HashMap<>();
    }
}
