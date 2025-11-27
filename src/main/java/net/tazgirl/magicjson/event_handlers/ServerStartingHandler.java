package net.tazgirl.magicjson.event_handlers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.registration.processes.HookTokenSynonyms;

@EventBusSubscriber(modid = MagicJson.MODID)
public class ServerStartingHandler
{
    @SubscribeEvent
    public static void OnServerStarting(ServerStartingEvent event)
    {
        HookTokenSynonyms.RegisterSynonyms(event);
    }

}
