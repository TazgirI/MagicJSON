package net.tazgirl.magicjson.game_tests;

import net.minecraft.network.chat.Component;
import net.tazgirl.magicjson.processing.Tokenisation;

import java.util.ArrayList;
import java.util.List;

public class NumericalComparisonTests extends TestRoot
{
    static
    {
        myTests.add(NumericalComparisonTests::SingleOnSingleTests);
    }

    static List<String> testStatements = List.of(

    );

    public static List<Component> SingleOnSingleTests()
    {
        List<Component> returnComponents = new ArrayList<>();

        for(String string: testStatements)
        {
            if(constructStatement(string).Run() instanceof Boolean bool && bool)
            {
                returnComponents.add(CustomSuccessMessage(string + " passed"));
            }
            else
            {
                returnComponents.add(CustomFailMessage(string + " failed"));
            }
        }

        return returnComponents;
    }

}
