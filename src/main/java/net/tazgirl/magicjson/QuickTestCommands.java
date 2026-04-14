package net.tazgirl.magicjson;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.tazgirl.magicjson.game_tests.TestRoot;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class QuickTestCommands
{
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("TestDoubleConfig")
                .requires(source -> source.hasPermission(4))
                .executes(context ->
                {
                    SendMessage.All(Config.TEST_DOUBLE_OPTIONAL.get().toString());

                    return 1;
                })
        );
    }
}
