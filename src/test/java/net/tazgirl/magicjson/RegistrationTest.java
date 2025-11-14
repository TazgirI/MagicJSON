package net.tazgirl.magicjson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest
{
    @Test
    void locationToAddressSimpleUsecaseJson()
    {
        assertEquals("magicjson:test", Registration.ResourceLocationToAddress("magicjson:magicjson/statement/test.json"));
    }

    @Test
    void locationToAddressSimpleUsecaseTxt()
    {
        assertEquals("magicjson:test", Registration.ResourceLocationToAddress("magicjson:magicjson/statement/test.txt"));
    }

    @Test
    void locationToAddressSubfolderConservation()
    {
        assertEquals("magicjson:testfolder/test", Registration.ResourceLocationToAddress("magicjson:magicjson/statement/testfolder/test.json"));
    }

    @Test
    void locationToAddressShorthandTest()
    {
        assertEquals("magicjson:test", Registration.ResourceLocationToAddress("magicjson:statement/test.txt"), "locationToAddressShorthand");
    }

    @Test
    void locationToAddressShorthandSubfolderConservation()
    {
        assertEquals("magicjson:statementfolder/test", Registration.ResourceLocationToAddress("magicjson:statement/statementfolder/test.txt"));
    }
}