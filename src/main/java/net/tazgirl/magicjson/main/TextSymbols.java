package net.tazgirl.magicjson.main;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.compounds.and.AndBooleanStatementObject;
import net.tazgirl.magicjson.main.statement_object.compounds.and.AndCompoundStatementObject;
import net.tazgirl.magicjson.main.statement_object.compounds.and.AndNumericalStatementObject;
import net.tazgirl.magicjson.main.statement_object.compounds.or.OrBooleanStatementObject;
import net.tazgirl.magicjson.main.statement_object.compounds.or.OrNumericalStatementObject;
import net.tazgirl.magicjson.main.statement_object.numbers.comparison_objects.LessThanStatementObject;

import java.util.List;
import java.util.Map;

public class TextSymbols
{

    // Values are lowercase "true" or "false" and objects are uppercase "ANDNUM" or "LESSTHAN"

    public static List<Character> tokenEndChars = List.of(
            '(',
            ')',
            ' '
    );

    public static List<Character> tokenExcludeChars = List.of(
            ' ',
            '('
    );

    //NOTE: Stack objects are objects that are added into the stacks object list instead of handled, this is any object that isn't a primitive
    public static Map<String, Class<? extends BaseStatementObject>> tokenStackObjectPairs = Map.of(
            "ANDNUM", AndNumericalStatementObject.class,
            "ANDBOOL", AndBooleanStatementObject.class,
            "AND", AndCompoundStatementObject.class,

            "ORNUM", OrNumericalStatementObject.class,
            "ORBOOL", OrBooleanStatementObject.class,

            "LESSTHAN", LessThanStatementObject.class


    );

    public static List<Character> objectCloseChars = List.of(
            ')'
    );

    public static Map<String, Boolean> booleanTokens = Map.of(
            "true", true,
            "false", false

    );


}
