package net.tazgirl.magicjson.main;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tokenise
{

    public static List<String> TokeniseStatement(String statement)
    {
        statement = statement.replace("\n", "").replace("\r", " ");

        int i = 0;

        List<String> returnTokens = new ArrayList<>();
        List<Character> currentToken = new ArrayList<>();

        while(i < statement.length())
        {
            Character currentChar = statement.charAt(i);

            if(TextSymbols.tokenEndChars.contains(currentChar))
            {
                String symbol = SymbolFromChars(currentToken);

                if(!symbol.isEmpty())
                {
                    returnTokens.add(symbol);
                }

                currentToken = new ArrayList<>();
            }

            if(currentChar.equals(')'))
            {
                currentToken.add(currentChar);
                returnTokens.add(SymbolFromChars(currentToken));
                currentToken = new ArrayList<>();

                i++;
                continue;
            }

            if(currentChar.equals('"'))
            {
                returnTokens.add(SymbolFromChars(currentToken));
                currentToken.add(currentChar);

                while(i < statement.length() - 1)
                {
                    i++;

                    currentChar = statement.charAt(i);

                    currentToken.add(currentChar);

                    if(currentChar == '"' )
                    {
                        returnTokens.add(SymbolFromChars(currentToken));
                        currentToken = new ArrayList<>();

                        break;
                    }
                }

                i++;
                continue;
            }

            if(!TextSymbols.tokenExcludeChars.contains(currentChar))
            {
                currentToken.add(currentChar);
            }

            i++;
        }

        returnTokens.removeIf(String::isEmpty);

        return returnTokens;
    }


    public static String SymbolFromChars(List<Character> chars)
    {
        String returnString = "";
        for(Character character: chars)
        {
            returnString = returnString.concat(String.valueOf(character));
        }

        return returnString.replace(" ","");
    }

}
