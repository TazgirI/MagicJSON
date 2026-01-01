package net.tazgirl.magicjson.game_tests;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.SendMessage;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = MagicJson.MODID)
public class TestCommand
{

    static List<TestRoot> runTests = List.of(new AllRegistersTests(), new TokenisationTests(), new NumericalComparisonTests(), new ArgTests());

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("MagicJSONGameTests")
                .requires(source -> source.hasPermission(4))
                        .executes(context ->
                        {
                            List<Component> sendComponents = new ArrayList<>(TestRoot.runAllTests());

                            TextColor red = TextColor.fromLegacyFormat(ChatFormatting.RED);

                            for(Component component: sendComponents)
                            {
                                SendMessage.All(component);
                                if(component.getStyle().getColor() == red)
                                {
                                    MJLogging.Error(component.getString());
                                }
                            }

                            return 1;
                        })
                );
    }
}
