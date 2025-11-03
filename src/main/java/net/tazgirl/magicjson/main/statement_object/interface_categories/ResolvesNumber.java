package net.tazgirl.magicjson.main.statement_object.interface_categories;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;

import java.util.Map;
import java.util.function.BiFunction;

public interface ResolvesNumber<T extends Number>
{
    public T Resolve();

    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft);

    public void SpreadArgs(Map<String, Object> newArgs);
}
