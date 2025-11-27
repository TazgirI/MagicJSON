package net.tazgirl.magicjson.processing;

import net.minecraft.resources.ResourceLocation;
import net.tazgirl.magicjson.data.TextSymbols;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.List;

public class TokensToHolder
{
    final List<String> tokens;
    final Stack stack;
    int i = 0;
    String token;

    public TokensToHolder(List<String> tokens, String debugResourceAddress)
    {
        this.tokens = tokens;
        this.stack = new Stack(debugResourceAddress, this);
    }

    public StatementHolder process()
    {
        while(i < tokens.size())
        {
            i++;
            token = tokens.get(i);

            if(TextSymbols.endChars.contains(token.charAt(0)))
            {
                stack.Close();
            }

            // Handle unique arguments comparable to constructor params that are expressed as objectname.argument(...)
            if(token.charAt(0) == '.')
            {
                Object argument = TextSymbols.uniqueArguments.get(token);

                if(argument != null)
                {
                    stack.PutUniqueArgument(argument);
                }
            }

        }

        return stack.finalise();
    }

}
