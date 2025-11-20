package net.tazgirl.magicjson.old.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokeniseTest
{

    @Test
    void tokeniseStatementStringSafe()
    {
        assertEquals("\"test\"",Tokenise.TokeniseStatement("\"test\"").getFirst());
    }
}