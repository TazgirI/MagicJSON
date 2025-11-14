package net.tazgirl.magicjson.main;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;

import java.util.List;

public class TokenToStatement
{

    public static BaseStatementObject objectFromTokens(List<String> tokens)
    {

        //EXP: Shouldn't be needed as stack can now handle primitives
/*        if(TextSymbols.tokenStackObjectPairs.get(tokens.getFirst()) == null)
        {
            Logging.Log("Statement did not start with a recognizable token: " + tokens.toString(), Logging.LogType.LOG, false, );
            return null;
        }*/

        StatementStack stack = new StatementStack(tokens);


        for(String token: tokens)
        {
            if(TextSymbols.tokenStackObjectPairs.get(token.toUpperCase()) != null)
            {
                Class<? extends BaseStatementObject> newObjectClass = TextSymbols.tokenStackObjectPairs.get(token.toUpperCase());
                stack.Put(newObjectClass);
            }
            else if(TextSymbols.objectCloseChars.contains(token.charAt(0)))
            {
                stack.Close();
            }
            else if(TextSymbols.booleanTokens.get(token.toLowerCase()) instanceof Boolean bool)
            {
                stack.Put(bool);
            }
            else if(Character.isDigit(token.charAt(0)))
            {
                stack.PutNum(token);
            }
            else if(token.charAt(0) == '"' && token.endsWith("\""))
            {
                stack.Put(token.substring(1, token.length() - 1));
            }
            else
            {
                stack.ParsingError("Attempted to parse unrecognised token");
            }

        }

        return stack.GetStackResult();
    }
}
