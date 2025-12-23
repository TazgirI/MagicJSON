package net.tazgirl.magicjson;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.memory.Memory;
import net.tazgirl.magicjson.memory.WorldMemory;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.processes.TokenSynonyms;

@EventBusSubscriber(modid = MagicJson.MODID)
public class MainProcessingDispatcher
{
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onServerStarting(ServerStartingEvent event)
    {
        Constants.server = event.getServer();
        Constants.chatDebugFlags = Config.CHAT_DEBUG_FLAGS.getAsBoolean();
        Memory.worldMemory = WorldMemory.getForOverworld();
        TokenSynonyms.RegisterSynonyms();
        RegistersForProcessing.FireAllRegisterFetches();

        RunStatementProcessing.ProcessStatements(event);

        Constants.loadingComplete = true;
        NeoForge.EVENT_BUS.post(new StatementLoadingCompleteEvent());
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event)
    {
        if(Config.CLEAR_HOLDER_RELATIONS.get())
        {
            PrivateCore.clearHolderRelations();
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onServerStopped(ServerStoppedEvent event)
    {
        Constants.loadingComplete = false;
    }

}
