package net.tazgirl.magicjson;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class SendMessage
{
    //NOTE: Missing the NullThrow from Tutilz when I moved it, less safe than Tutilz but should be fine


    public static boolean All(String message)
    {
        return All(Component.literal(message));
    }

    public static boolean All(Component message)
    {
        MinecraftServer server = Constants.server;
        List<ServerPlayer> players;

        if (server != null && !(players = server.getPlayerList().getPlayers()).isEmpty())
        {
            for (ServerPlayer player : players)
            {
                player.sendSystemMessage(message);
            }
            return true;
        }

        return false;
    }



    public static void Specific(String message,ServerPlayer player)
    {
        Specific(Component.literal(message), player);
    }

    public static void Specific(Component message,ServerPlayer player)
    {
        player.sendSystemMessage(message);
    }

}
