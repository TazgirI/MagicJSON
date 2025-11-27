package net.tazgirl.magicjson.old.main.statement_object.compounds.and;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesBoolean;

import java.util.function.BiFunction;

public class AndStatementObject extends CompoundStatementObject implements ResolvesBoolean
{



    @Override
    public void SetConstants()
    {
        identifier = "And";
    }

    @Override
    public Boolean Resolve()
    {
        for(BaseStatementObject statementObject: contents)
        {
            if(statementObject instanceof ResolvesBoolean && statementObject.Resolve() instanceof Boolean bool && !bool)
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {

        for(BaseStatementObject statementObject: this.contents)
        {
            if(statementObject.numericalTest(test, operand, operandIsLeft) == false){return false;}
        }

        return Resolve();
    }
}
