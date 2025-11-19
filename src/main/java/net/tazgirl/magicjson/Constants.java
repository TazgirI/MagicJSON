package net.tazgirl.magicjson;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.tazgirl.magicjson.memory.WorldMemory;

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
        server.overworld().getDataStorage().computeIfAbsent(new SavedData.Factory<>(WorldMemory::create, WorldMemory::load), "WorldMemory");
    }


    // For use when we want something truly empty to compare against and we can't use null in case that was on purpose or is indicating an error
    public enum Empty
    {
        TRUE_EMPTY
    }
}
