package net.tazgirl.magicjson.processing;

import net.tazgirl.magicjson.Config;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.registration.PrimitiveInitRecord;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.statements.objects.Execute;
import net.tazgirl.magicjson.statements.objects.memory.args.ArgGet;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.NullObject;
import net.tazgirl.magicjson.statements.objects.primitives.StringObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TokensToHolder
{
    final Tokenisation tokenisation;
    List<String> tokens;
    final Stack stack;
    int i = 0;
    String token;

    public TokensToHolder(Tokenisation tokenisation, String debugResourceAddress)
    {
        this.tokenisation = tokenisation;
        this.stack = new Stack(debugResourceAddress, this);
    }

    public StatementHolder process()
    {
        tokens = tokenisation.TokeniseStatement();

        if(tokens.isEmpty())
        {
            MJLogging.debug("Statement at debug address \"" + stack.processingResourceAddress + "\" returned an empty tokens list");
            stack.put(new NullObject(stack.holder));
            return stack.finalise();
        }

        while(i < tokens.size())
        {
            int pings = 0;
            token = tokens.get(i);
            char zeroChar = token.charAt(0);

            if(RegistersForProcessing.closeTokens.contains(token))
            {
                stack.Close();
                pings++;
            }

            // Pass in unique arguments like constructor params, always pass the string as StatementObjects must do handling
            // Can be written in a way like Divide(10 .modulus 2) but is intended to be written as Divide.modulus(10 2)
            // This isn't implemented with the lead char register as it is handled completely uniquely
            if(RegistersForProcessing.statementObjects.containsKey(token))
            {
                stack.put(RegistersForProcessing.statementObjects.getAsObject(token, stack.holder));
                pings++;
            }
            else if(zeroChar == '.')
            {
                if(token.charAt(1) == '.' && RegistersForProcessing.appendHooks.containsKey(token.substring(2)))
                {
                    try
                    {
                        RegistersForProcessing.appendHooks.get(token).getDeclaredConstructor().newInstance().Trigger(stack.objectStack.getLast(), stack, stack.holder, this);
                        pings++;
                    }
                    catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                    {
                        MJLogging.error("Failed to construct the AppendHook with the token of " + token.substring(2));
                    }
                }
                else
                {
                    stack.PutUniqueArgument(token);
                    pings++;
                }
            }
            // The passed String WILL be stripped of the leading char before being applied
            else if(RegistersForProcessing.leadCharTokens.containsKey(zeroChar))
            {
                Base base = RegistersForProcessing.leadCharTokens.get(zeroChar).apply(token.substring(1), stack.holder);
                if(base != null)
                {
                    stack.put(base);
                }
                else
                {
                    MJLogging.debug("leadCharToken attempted to build but returned null");
                }

                pings++;
            }


            else if(RegistersForProcessing.primitiveObjects.containsKey(token))
            {
                stack.put(RegistersForProcessing.primitiveObjects.get(token).init(stack.holder));
                // Primitives close themselves even if it doesn't feel like it i.e and(true true) the ending bracket closes the second true not the and
//                if(tokens.get(i + 1).equals(")"))
//                {
//                    stack.Close();
//                }
                pings++;
            }
            else if(RegistersForProcessing.statementKeywords.containsKey(token))
            {
                Execute execute = new Execute(stack.holder, RegistersForProcessing.statementKeywords.get(token));
                stack.put(execute);
                pings++;
            }
            else if(Character.isDigit(zeroChar))
            {
                stack.PutNum(token);
                pings++;
            }

            else if(zeroChar == '_')
            {
sdgsdgsdgsdgsdgds
            }
            // Any token beginning with '"' has the first and last character stripped and is then passed as a StringObject
            else if(zeroChar == '"')
            {
                if(!(token.length() - 2 <= 0))
                {
                    stack.put(new StringObject(stack.holder, token.substring(1, token.length() - 1)));
                    pings++;
                }
                else
                {
                    MJLogging.debug("Detected token '" + token + "' is a string but is too short to process");
                }
            }
            // Any token that directly equals "{" triggers the implicitChild of the last added token to stack
            else if (token.equals("{"))
            {
                stack.put(stack.objectStack.getLast().implicitChild());
                pings++;
            }

            if(Config.OBJECTIFICATION_PING_COUNT.getAsBoolean())
            {
                MJLogging.info("The token \"" + token + "\" was pinged " + pings + " time(s) during object construction");
            }
            if(Config.OBJECTIFICATION_PING_CONTENTS.getAsBoolean())
            {
                MJLogging.info(stack.objectStack.toString());
            }



            // Do not move, too many bugs and edge cases if at start of iteration
            i++;
        }

        return stack.finalise();
    }

    public Base getTokenFromAnyRegister(String token)
    {
        if(RegistersForProcessing.statementObjects.getAsObject(token, stack.holder) instanceof Base base){return base;}
        if(RegistersForProcessing.primitiveObjects.get(token) instanceof PrimitiveInitRecord record){return record.init(stack.holder);}

        // CHECKS: Is this every register within the current build?

        return null;
    }

}
