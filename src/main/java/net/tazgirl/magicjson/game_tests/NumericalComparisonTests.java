package net.tazgirl.magicjson.game_tests;

import net.minecraft.network.chat.Component;
import net.tazgirl.magicjson.processing.Tokenisation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NumericalComparisonTests extends TestRoot
{
    static
    {
        myTests.add(NumericalComparisonTests::SingleOnSingleTests);
    }

    static Map<String, Boolean> testStatements = Map.of(
            "and(true true)", true,
            "and(false true)", false,
            "and(true false)", false,
            "and(false false)", false,
            "or(true true)", true,
            "or(false false)", false,
            "or(true false)", true,
            "or(false true)", true
    );

    public static List<Component> SingleOnSingleTests()
    {
        List<Component> returnComponents = new ArrayList<>();

        for(Map.Entry<String, Boolean> entry: testStatements.entrySet())
        {
            if(constructStatement(entry.getKey()).Run() == entry.getValue())
            {
                returnComponents.add(CustomSuccessMessage(entry.getKey() + " passed"));
            }
            else
            {
                returnComponents.add(CustomFailMessage(entry.getKey() + " failed, expected " + entry.getValue()));
            }
        }

        return returnComponents;
    }

}
