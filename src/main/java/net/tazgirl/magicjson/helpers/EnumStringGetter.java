package net.tazgirl.magicjson.helpers;

public class EnumStringGetter
{

    public static <T extends Enum<T>> T get(Class<T> e, String s)
    {
        try
        {
            s = s.substring(1).toUpperCase();
            return Enum.valueOf(e, s);
        }
        catch(IllegalArgumentException | NullPointerException exception)
        {
            return null;
        }
    }
}
