package net.tazgirl.magicjson.processing;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.registration.InitRecord;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.statements.objects.Arg;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.StringObject;
import org.checkerframework.checker.units.qual.A;

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

        while(i < tokens.size())
        {
            int pings = 0;
            i++;
            token = tokens.get(i);

            if(RegistersForProcessing.closeTokens.contains(token))
            {
                stack.Close();
                pings++;
            }

            if(RegistersForProcessing.statementObjects.containsKey(token))
            {
                stack.Put(RegistersForProcessing.statementObjects.getAsObject(token, stack.holder));
                pings++;
            }

            if(RegistersForProcessing.primitiveObjects.containsKey(token))
            {
                stack.Put(RegistersForProcessing.primitiveObjects.get(token).init(stack.holder));
                pings++;
            }

            // Pass in unique arguments like constructor params, always pass the string as StatementObjects must do handling
            // Can be written in a way like Divide(10 .modulus 2) but is intended to be written as Divide.modulus(10 2)
            if(token.charAt(0) == '.')
            {
                stack.PutUniqueArgument(token);
                pings++;
            }

            // Any token beginning with '_' is treated as shorthand for an arg fetch
            // Arg("waves") and _waves will both create the same object
            if(token.charAt(0) == '_')
            {
                stack.Put(new Arg(stack.holder));
                stack.Put(new StringObject(stack.holder, token.substring(1)));
                stack.Close(2);
                pings++;
            }

            // Any token beginning with '"' has the first and last character stripped and is then passed as a StringObject
            if(token.charAt(0) == '"')
            {
                if(!(token.length() - 2 <= 0))
                {
                    stack.Put(new StringObject(stack.holder, token.substring(1, token.length() - 1)));
                    stack.Close();
                    pings++;
                }
                else
                {
                    Logging.Debug("Detected token '" + token + "' is a string but is too short to process");
                }
            }

            if(pings == 0)
            {
                Logging.Debug("The token \"" + token + "\" was pinged 0 times during object construction, this may have been on purpose but that is unlikely");
            }
            else
            {
                Logging.Info("The token \"" + token + "\" was pinged " + pings + " time(s) during object construction");
            }
        }

        return stack.finalise();
    }

    public Base getTokenFromAnyRegister(String token)
    {
        if(RegistersForProcessing.statementObjects.getAsObject(token, stack.holder) instanceof Base base){return base;}
        if(RegistersForProcessing.primitiveObjects.get(token) instanceof InitRecord record){return record.init(stack.holder);}

        // CHECKS: Is this every register within the current build?

        return null;
    }

}
