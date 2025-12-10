package net.tazgirl.magicjson;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.processes.TokenSynonyms;

@EventBusSubscriber(modid = MagicJson.MODID)
public class MainProcessingDispatcher
{
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onServerStarting(ServerStartingEvent event)
    {
        Constants.server = event.getServer();
        TokenSynonyms.RegisterSynonyms(event);
        RegistersForProcessing.FireAllRegisterFetches();



        Constants.loadingComplete = true;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onServerStopped(ServerStoppedEvent event)
    {
        Constants.loadingComplete = false;
    }

}
