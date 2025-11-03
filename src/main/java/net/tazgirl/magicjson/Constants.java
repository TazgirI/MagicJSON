package net.tazgirl.magicjson;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@EventBusSubscriber(modid = MagicJson.MODID, bus = EventBusSubscriber.Bus.GAME)

public class Constants
{

    public static MinecraftServer server;


    @SubscribeEvent
    public static void OnServerStarting(ServerStartedEvent event)
    {
        server = event.getServer();
    }


}
