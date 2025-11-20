package net.tazgirl.magicjson.old.main.statement_object.compounds;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.StatementManager;
import net.tazgirl.magicjson.old.main.statement_object.compounds.and.AndStatementObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class CompoundStatementObject extends BaseStatementObject
{
    public List<BaseStatementObject> contents = new ArrayList<>();

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof BaseStatementObject statementObject)
        {
            contents.add(statementObject);
            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        String childrenStrings = "";

        for(BaseStatementObject child: contents)
        {
            childrenStrings = childrenStrings.concat(child.toString() + " ");
        }

        return identifier + "( " + childrenStrings + " )";
    }

    @Override
    public void SpreadManager(StatementManager newManager)
    {
        manager = newManager;

        contents.forEach(content -> content.SpreadManager(newManager));
    }

    public Boolean compoundNumeracyCheck(BaseStatementObject operand)
    {

    }

    public Boolean CompoundNumericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {
        String compType = this instanceof AndStatementObject ? "and" : "or";

        if(operand instanceof CompoundStatementObject compoundOperand)
        {

        }
        else
        {
            Boolean soloCheckValue = compType.equals("or");

            for(BaseStatementObject statementObject: contents)
            {
                if(statementObject.numericalTest(test, operand, operandIsLeft) == soloCheckValue){return soloCheckValue;}
            }

            return !soloCheckValue;
        }
    }



}
