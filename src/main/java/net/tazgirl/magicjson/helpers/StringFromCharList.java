package net.tazgirl.magicjson.helpers;

import java.util.List;

public class StringFromCharList
{

    public static String get(List<Character> chars)
    {
        StringBuilder stringBuilder = new StringBuilder(chars.size());

        for (char c : chars)
        {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

}
