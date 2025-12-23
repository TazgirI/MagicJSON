package net.tazgirl.magicjson.game_tests;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.SendMessage;
import net.tazgirl.magicjson.processing.Tokenisation;
import net.tazgirl.magicjson.processing.TokensToHolder;

@EventBusSubscriber(modid = MagicJson.MODID)
public class RunStatementCommand
{
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("runStatement")
                .then(Commands.argument("statement", StringArgumentType.greedyString())
                        .executes(ctx -> {
                            String statement = StringArgumentType.getString(ctx, "statement");
                            if(PrivateCore.hasStatement(statement))
                            {
                                SendMessage.All(PrivateCore.runStatement(statement).toString());
                            }
                            else
                            {
                                String result =  new TokensToHolder(new Tokenisation(statement), "command:run").process().Run().toString();
                                SendMessage.All(result);
                            }

                            return 1;
                        })
                ));
    }
}
