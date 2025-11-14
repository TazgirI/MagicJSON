package net.tazgirl.magicjson;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@EventBusSubscriber(modid = MagicJson.MODID)

public class Constants
{

    public static MinecraftServer server;

    public static int currentErrorStage = 0;

    public static Object trueEmpty = Empty.TRUE_EMPTY;


    @SubscribeEvent
    public static void OnServerStarting(ServerStartingEvent event)
    {
        server = event.getServer();

    }


    // For use when we want something truly empty to compare against and we can't use null in case that was on purpose or is indicating an error
    public enum Empty
    {
        TRUE_EMPTY
    }
}
