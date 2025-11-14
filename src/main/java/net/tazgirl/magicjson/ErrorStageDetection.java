package net.tazgirl.magicjson;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

public class ErrorStageDetection
{

    @SubscribeEvent
    public static void OnServerStarting(ServerStartingEvent event)
    {
        Constants.currentErrorStage = 1;
    }

    @SubscribeEvent
    public static void OnServerStarted(ServerStartedEvent event)
    {
        Constants.currentErrorStage = 2;
    }

    @SubscribeEvent
    public static void OnServerStopping(ServerStoppingEvent event)
    {
        Constants.currentErrorStage = 3;
    }

    @SubscribeEvent
    public static void OnServerStopping(ServerStoppedEvent event)
    {
        Constants.currentErrorStage = 4;
    }

}
