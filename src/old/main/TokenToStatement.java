package net.tazgirl.magicjson.old.main;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;

import java.util.List;

public class TokenToStatement
{

    public static BaseStatementObject objectFromTokens(List<String> tokens, String statementAddress)
    {

        //EXP: Shouldn't be needed as stack can now handle primitives
/*        if(TextSymbols.tokenStackObjectPairs.get(tokens.getFirst()) == null)
        {
            Logging.Log("Statement did not start with a recognizable token: " + tokens.toString(), Logging.LogType.LOG, false, );
            return null;
        }*/

        StatementStack stack = new StatementStack(tokens, statementAddress);


        for(int i = 0; i < tokens.size(); i++)
        {
            String token = tokens.get(i);
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
            // Allows for statements to avoid declaring their child (purely a qol feature)
            // For example, the ReturnHookStatementObject will create an implicit HookParameterStatementObject so you can write ("name" 5) instead of PARAM("name" 5)
            else if(token.charAt(0) == '(' &&
                    // Checks if the token directly before the open bracket was a declared stack object i.e PARAM( returns !true but PARAM(( returns !false
                    !(TextSymbols.tokenStackObjectPairs.containsKey(tokens.get(i - 1))) && stack.objectStack.getLast().ImplicitChild() instanceof BaseStatementObject statementObject)
            {
                stack.objectStack.add(statementObject);
            }
            else
            {
                stack.ParsingError("Attempted to parse unrecognised token");
            }

        }

        return stack.GetStackResult();
    }
}
