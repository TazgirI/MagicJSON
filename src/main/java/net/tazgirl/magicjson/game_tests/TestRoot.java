package net.tazgirl.magicjson.game_tests;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public abstract class TestRoot
{
    static List<Supplier<Collection<Component>>> myTests = new ArrayList<>();

    public static Object expected;
    public static Object result;

    public static List<Component> runAllTests()
    {
        List<Component> returnComponents = new ArrayList<>();

        for(Supplier<Collection<Component>> supplier: myTests)
        {
            returnComponents.addAll(supplier.get());
        }

        return returnComponents;
    }

    public static Component assertFailure(String testName)
    {
        return Component.literal(testName + " expected \"" + expected.toString() + "\" instead got \"" + result.toString() + "\"").setStyle(Style.EMPTY.withColor(ChatFormatting.RED));
    }

    public static Component assertSuccess(String testName)
    {
        return Component.literal(testName + " passed").setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN));
    }

    public static Component assertion(String testName)
    {

        if(expected != result)
        {
            return assertFailure(testName);
        }
        return assertSuccess(testName);
    }

    public static List<Component> assertionList(String testName)
    {
        return List.of(assertion(testName));
    }


}
