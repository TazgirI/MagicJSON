package net.tazgirl.magicjson.old.main.addresses;

import net.tazgirl.magicjson.Logging;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Address
{

    // Exclude the function/statement divider i.e. instead of magicjson:statement/calculate it would just be magicjson:calculate, the object type determines the registry to check
    String localAddress;

    static List<Character> breakPunctuation = List.of(':', '\\', '/');

    public Address(String localAddress)
    {
        this.localAddress = localAddress;
    }

    public void setLocalAddress(String localAddress)
    {
        this.localAddress = localAddress;
    }

    public String getLocalAddress()
    {
        return localAddress;
    }

    public abstract Object getAsObject();


    // Correct address forms modid:location\name and modid:name {modid, :, location, \, name} and {modid, :, name}
    public static verdict Assess(String addressString)
    {
        List<String> tokens = tokeniseAddress(addressString);

        int colonIndex = tokens.indexOf(":");

        if(colonIndex == -1)
        {
            Logging.Debug("Address failed to assess as it didn't contain a colon    Address token received: " + tokens, false);
            return verdict.NULL;
        }

        if(tokens.size() != 3 && tokens.size() != 5)
        {
            Logging.Debug("Address failed to asses as it was neither 3 parts {\"modid\", \"\\\", \"name\"} or 5 parts {\"modid\", \":\", \"location\", \"\\\", \"name\"}    " +
                    "Address token received: " + tokens, false);
        }

        if(tokens.size() == 3)
        {
            return verdict.DEFAULT;
        }

        if(Objects.equals(tokens.get(colonIndex + 1), "statement"))
        {
            return verdict.STATEMENT;
        }

        if(Objects.equals(tokens.get(colonIndex + 1), "function"))
        {
            return verdict.FUNCTION;
        }


        Logging.Debug("5 part address is improperly formatted, expected modid:location\\name but has received \"" + addressString + "\"     " +
                "Address token (5 part address expects {\"modid\", \":\", \"location\", \"\\\", \"name\"}): " + tokens, false);
        return verdict.NULL;
    }

    public static List<String> tokeniseAddress(String input)
    {
        List<String> returnList = new ArrayList<>();

        int i = 0;

        String currentToken = "";
        char currentChar;

        while(i < input.length())
        {
            currentChar = input.charAt(i);
            if(!breakPunctuation.contains(currentChar))
            {
                currentToken = currentToken.concat(String.valueOf(currentChar));
            }
            else
            {
                if(!currentToken.isEmpty()){returnList.add(currentToken);}

                returnList.add(String.valueOf(currentChar));

                currentToken = "";
            }

            i++;
        }

        if(!currentToken.isEmpty()){returnList.add(currentToken);}


        return returnList;

    }

    public enum verdict
    {
        STATEMENT,
        FUNCTION,
        DEFAULT,
        NULL
    }

}
