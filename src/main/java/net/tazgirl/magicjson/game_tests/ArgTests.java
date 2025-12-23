package net.tazgirl.magicjson.game_tests;

import net.minecraft.network.chat.Component;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.List;
import java.util.Map;

public class ArgTests extends TestRoot
{
    static
    {
        myTests.add(ArgTests::trueArgTest);
    }

    public static List<Component> trueArgTest()
    {
        StatementHolder holder = constructStatement("arg(\"value\")");
        holder.setArgs(Map.of("value", true));
        if(holder.Run() instanceof Boolean bool && bool)
        {
            return List.of(assertSuccess("trueArgTest"));
        }

        return List.of(assertSimpleFailure("trueArgTest"));
    }

}
