package net.tazgirl.magicjson.old.memory;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

import java.util.HashMap;
import java.util.Map;

public class Memory
{
    static Map<String, Object> sessionMemory = new HashMap<>();

    // Put, get and edit same as WorldMemory
    // Does it need different access functions as it uses objects rather than strings?


    @SubscribeEvent
    public static void ServerStopped(ServerStoppedEvent event)
    {
        sessionMemory = new HashMap<>();
    }


}
