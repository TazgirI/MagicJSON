package net.tazgirl.magicjson.game_tests;

import net.minecraft.network.chat.Component;
import net.tazgirl.magicjson.processing.Tokenisation;


import java.util.List;

public class TokenisationTests extends TestRoot
{
    static
    {
        myTests.add(TokenisationTests::basicTokenise);
    }

    public static List<Component> basicTokenise()
    {
        if(List.of("add","(", "12d", "13d", ")").toString().equals(new Tokenisation("add(12d 13d)").TokeniseStatement().toString()))
        {
            return List.of(assertSuccess("basicTokenise"));
        }

        return List.of(assertFailure("basicTokenise"));
    }

}
