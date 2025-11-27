package net.tazgirl.magicjson.old.memory;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;

import java.util.Map;
import java.util.function.Function;

public class StringObjectRelations
{

    // DISC: These functions have no type checks, if you call any functions with the wrong type it WILL break

    public static Map<String, Function<String, Object>> stringTypeChecks = Map.of(
            "`i`", StringObjectRelations::stringToInt,
            "`f`", StringObjectRelations::stringToFloat,
            "`l`", StringObjectRelations::stringToLong,
            "`d`", StringObjectRelations::stringToDouble,
            "`b`", StringObjectRelations::stringToBoolean

    );

    public static Map<Class<?>,Function<Object, String>> objectStringFunctions = Map.of(
            Integer.class, StringObjectRelations::numberToString,
            Float.class, StringObjectRelations::numberToString,
            Long.class, StringObjectRelations::numberToString,
            Double.class, StringObjectRelations::numberToString,
            Boolean.class, StringObjectRelations::booleanToString,
            String.class, StringObjectRelations::dummyStringToString

    );

    public static String convertObjectToTypeString(Object object)
    {
        Function<Object, String> function = objectStringFunctions.get(object.getClass());

        if(function != null)
        {
            return function.apply(object);
        }

        Logging.Debug("Attempted to create a memory String for the class \"" + object.getClass() + "\", but this class is not supported", false);
        return null;
    }

    public static Object getObject(String string)
    {
        int firstMarkIndex = string.indexOf('`');
        int lastMarkIndex = string.lastIndexOf('`');

        if(!(firstMarkIndex < lastMarkIndex)){return string;}

        String checkString = string.substring(firstMarkIndex, lastMarkIndex + 1);

        return stringTypeChecks.get(checkString).apply(string.substring(0,lastMarkIndex));
    }

    public static String dummyStringToString(Object object)
    {
        return object.toString();
    }

    public static String booleanToString(Object bool)
    {
        if((boolean) bool)
        {
            return "true`b`";
        }

        return "false`b`";
    }

    public static Boolean stringToBoolean(String string)
    {
        return string.equals("true");
    }

    public static Double stringToDouble(String string)
    {
        return stringToNumber(string, Double.class);
    }

    public static Integer stringToInt(String string)
    {
        return stringToNumber(string, Integer.class);
    }

    public static Float stringToFloat(String string)
    {
        return stringToNumber(string, Float.class);
    }

    public static Long stringToLong(String string)
    {
        return stringToNumber(string, Long.class);
    }


    static Map<Class<?>, String> numberSuffixes = Map.of(
            Integer.class, "`i`",
            Float.class, "`f`",
            Long.class, "`l`",
            Double.class, "`d`"
    );

    public static String numberToString(Object number)
    {
        Class<?> numClass = number.getClass();

        String numString = number.toString();

        return numString.concat(numberSuffixes.get(number.getClass()));

    }

    public static <T extends Number> T stringToNumber(String string, Class<T> convertType)
    {
        double dub;

        try
        {
            dub = Double.parseDouble(string);
        }
        catch(NumberFormatException exception)
        {
            LogFail(string, "Double");
            throw exception;
        }

        return BaseStatementObject.AttemptNumberConversion(convertType, dub);
    }

    static void LogFail(String convertString, String failType)
    {
        Logging.Error("String \"" + convertString + "\" was recognised as a " + failType + " but failed to convert", false);
    }
}
