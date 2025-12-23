package net.tazgirl.magicjson.helpers;

import java.util.Arrays;

public interface EnumAliaseGetter<E extends Enum<E>>
{

    String[] aliasses();

    Class<E> myClass();

    static <E extends Enum<E> & EnumAliaseGetter<E>> E get(String string, EnumAliaseGetter<E> enumGetter)
    {
        try
        {
            return E.valueOf(enumGetter.myClass(), string);
        }
        catch (IllegalArgumentException | NullPointerException ignored)
        {

        }

        for(E entry : enumGetter.myClass().getEnumConstants())
        {
            if(Arrays.asList(entry.aliasses()).contains(string))
            {
                return entry;
            }
        }

        return null;
    }
}
