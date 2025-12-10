package net.tazgirl;

import net.tazgirl.magicjson.data.Constants;
import org.junit.jupiter.api.BeforeAll;

public class RootTest
{
    @BeforeAll
    public static void BeforeAllTests()
    {
        Constants.isTesting = true;
    }

    public static void Print(Object object)
    {
        System.out.println(object.toString());
    }
}
