package net.tazgirl.magicjson.game_tests;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.tazgirl.magicjson.processing.Tokenisation;
import net.tazgirl.magicjson.processing.TokensToHolder;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;


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

    public static Component CustomFailMessage(String string)
    {
        return Component.literal(string).setStyle(Style.EMPTY.withColor(ChatFormatting.RED));
    }

    public static Component CustomSuccessMessage(String string)
    {
        return Component.literal(string).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN));
    }

    public static Component assertFailure(String testName)
    {
        return assertFailure(testName, expected, result);
    }

    public static Component assertSimpleFailure(String testName)
    {
        return Component.literal(testName + " failed").setStyle(Style.EMPTY.withColor(ChatFormatting.RED));
    }

    public static Component assertFailure(String testName, Object expectedAlt, Object resultAlt)
    {
        return Component.literal(testName + " expected \"" + expectedAlt.toString() + "\" instead got \"" + resultAlt.toString() + "\"").setStyle(Style.EMPTY.withColor(ChatFormatting.RED));
    }

    public static Component assertSuccess(String testName)
    {
        return Component.literal(testName + " passed").setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN));
    }

    public static Component assertSingle(String testName)
    {
        return assertSingle(testName, expected, result);
    }

    public static Component assertSingle(String testName, Object expectedAlt, Object resultAlt)
    {
        if(expectedAlt != resultAlt)
        {
            return assertFailure(testName, expectedAlt, resultAlt);
        }
        return assertSuccess(testName);
    }

    public static List<Component> assertLists(String testName)
    {
        if(expected instanceof List<?> expectedList && result instanceof List<?> resultList && expectedList.size() == resultList.size())
        {
            List<Component> returnList = new ArrayList<>();
            for(int i = 0; i < expectedList.size(); i++)
            {
                returnList.add(assertSingle(testName + "[" + i + "]", expectedList.get(i), resultList.get(i)));
            }
            return returnList;
        }
        return List.of(CustomFailMessage("Attempted to assert lists for the test" + testName + " but either expected or result weren't lists or were of unequal sizes"));
    }

    public static StatementHolder constructStatement(String string)
    {
        return new TokensToHolder(new Tokenisation(string), "test:build").process();
    }
}
