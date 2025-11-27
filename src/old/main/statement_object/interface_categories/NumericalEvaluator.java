package net.tazgirl.magicjson.old.main.statement_object.interface_categories;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;

import java.util.function.BiFunction;

public interface NumericalEvaluator extends BaseStatementInterface
{
    // Interface isn't for safety, just so methods that specify they want a NumericalEvaluator can access the methods from BaseStatement
    // A little annoying but the interface tag system saves more time than is spent moving things to interface

    Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft);
}
