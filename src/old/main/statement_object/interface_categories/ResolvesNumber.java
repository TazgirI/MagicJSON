package net.tazgirl.magicjson.old.main.statement_object.interface_categories;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.StatementManager;

import java.util.function.BiFunction;

public interface ResolvesNumber<T extends Number> extends BaseStatementInterface
{
    // Interface isn't for safety, just so methods that specify they want a ResolvesNumber can access the methods from BaseStatement
    // A little annoying but the interface tag system saves more time than is spent moving things to interface

    T Resolve();

    Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft);
    void SpreadManager(StatementManager newManager);

}
