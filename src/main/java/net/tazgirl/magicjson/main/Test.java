package net.tazgirl.magicjson.main;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;

public class Test
{
    public static void main(String[] args)
    {
        System.out.println(Tokenise.TokeniseStatement("ANDBOOL(TRUE ARGBOOL(waves))"));

        BaseStatementObject statementObject = TokenToStatement.objectFromTokens(Tokenise.TokeniseStatement("LESSTHAN(ORNUM(12 14) ANDNUM(13 12))"));

        if(statementObject == null)
        {
            System.out.println("statement is null");
        }

        System.out.println(statementObject.toString());
        System.out.println(statementObject.Resolve());

    }


}
