package net.tazgirl.magicjson.data;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextSymbols
{

    // TODO: Move to Register classes

    public static List<Character> endChars = new ArrayList<>(List.of(
            '(',
            ')',
            ' ',
            '.' // '.' ends the current token but isn't a solo so Add.integer would become [ "Add", ".integer"]
    ));

    public static List<Character> soloChars = new ArrayList<>(List.of(
            '(',
            ')',
            ' '
    ));

    public static List<Character> excludeChars = new ArrayList<>(List.of(
            ' '
    ));

    public static boolean excludedTokenStrings(String string)
    {
        return (string.length() == 1 && excludeChars.contains(string.charAt(0)) || string.isEmpty());
    }

    public static Map<String, Class<? extends Base>> baseClassTokens = new HashMap<>(Map.of(



    ));

    public static Base getObjectFromToken(String token, StatementHolder holder)
    {
        Class<? extends Base> tokenObjectClass = baseClassTokens.get(token.toLowerCase());

        if(tokenObjectClass != null)
        {
            try
            {
                return tokenObjectClass.getDeclaredConstructor(StatementHolder.class).newInstance(holder);
            }
            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }


        Logging.Debug("Attempted to get an object from unrecognised token: " + token);
        return null;
    }

    public static List<Character> closeChars = new ArrayList<>(List.of(
            ')'
    ));

    public static Map<String, Boolean> booleanTokens = new HashMap<>(Map.of(
            "true", true,
            "false", false
    ));


    public static Map<String, Object> uniqueArguments = new HashMap<>(Map.of(
            ".integer", Integer.class,
            ".int", Integer.class,
            ".float", Float.class,
            ".long", Long.class,
            ".double", Double.class
    ));


}
