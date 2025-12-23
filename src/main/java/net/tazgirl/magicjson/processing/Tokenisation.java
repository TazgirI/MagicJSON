package net.tazgirl.magicjson.processing;

import net.tazgirl.magicjson.registration.RegistersForProcessing;

import java.util.ArrayList;
import java.util.List;

public class Tokenisation
{
    String statement;

    char currentChar;
    List<String> tokens = new ArrayList<>();
    StringBuilder currentToken = new StringBuilder();
    int i = 0;

    public Tokenisation(String statement)
    {
        this.statement = statement;
    }

    public List<String> TokeniseStatement()
    {
        for(i = 0; i < statement.length(); i++)
        {
            currentChar = statement.charAt(i);

//------------------------------------------------------------------------------------------------------------------------------

            // Checks that do not halt this char:

            if(RegistersForProcessing.endChars.contains(currentChar) && !currentToken.isEmpty())
            {
                EndChar();
            }

//------------------------------------------------------------------------------------------------------------------------------

            // Checks that skip to next char:

            if(RegistersForProcessing.soloChars.contains(currentChar))
            {
                SoloChar();
                continue;
            }
            if(currentChar == '"')
            {
                OpenSpeechMarks();
                continue;
            }

            currentToken.append(currentChar);
        }

        if(!currentToken.isEmpty())
        {
            TokenToTokens();
        }
        tokens.removeIf(RegistersForProcessing.excludeChars::isStringExcludable);

        return tokens;
    }

    public void EndChar()
    {
        TokenToTokens();
    }

    public void SoloChar()
    {
        tokens.add(Character.toString(currentChar));
    }

    public void OpenSpeechMarks()
    {
        UntilHit('"');
        CleanEscapeCharacters();
        TokenToTokens();
    }

    public void UntilHit(Character checkCharacter)
    {
        currentToken.append(currentChar);

        boolean lastCharWasEscape = false;

        i++;
        for(i = i; i < statement.length(); i++)
        {
            currentChar = statement.charAt(i);
            currentToken.append(currentChar);

            lastCharWasEscape = currentChar == '\\' && !lastCharWasEscape;

            if(currentChar == checkCharacter && !lastCharWasEscape)
            {
                break;
            }
        }
    }

    public void CleanEscapeCharacters()
    {
        boolean escape = false;

        for(int j = 0; j < currentToken.length(); j++)
        {
            char checkChar = currentToken.charAt(j);

            if(escape)
            {
                if(checkChar == '"')
                {
                    currentToken.deleteCharAt(j-1);
                    j--;
                }

                escape = false;

                // Skip adding second \
                if(checkChar == '\\'){continue;}
            }

            if(checkChar == '\\')
            {
                escape = true;
            }

        }
    }



    public void TokenToTokens()
    {
        tokens.add(currentToken.toString());
        ClearCurrentToken();
    }

    public void ClearCurrentToken()
    {
        currentToken = new StringBuilder();
    }
}
