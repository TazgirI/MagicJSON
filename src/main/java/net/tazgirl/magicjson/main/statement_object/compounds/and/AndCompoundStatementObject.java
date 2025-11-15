package net.tazgirl.magicjson.main.statement_object.compounds.and;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;
import net.tazgirl.magicjson.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ContainsNumbers;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;

import java.util.function.BiFunction;

public class AndCompoundStatementObject extends CompoundStatementObject<CompoundStatementObject<?>>
{
    // NOTE: This class should honestly not be used in its current state unless there's something I'm missing that makes it useful
    // TODO: Rework this class to be a proper generics handler, see also: note in super constructor

    @Override
    public void SetConstants()
    {
        identifier = "AndCompound";
    }

    @Override
    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {

        for(CompoundStatementObject<?> compoundObject: contents)
        {
            if(compoundObject instanceof ContainsNumbers && !compoundObject.numericalTest(test, operand, operandIsLeft)){return false;}
        }


        return CheckBooleans();
    }

    public Boolean CheckBooleans()
    {
        for(CompoundStatementObject<?> compoundObject: contents)
        {
            if(compoundObject instanceof ResolvesBoolean && !compoundObject.Resolve()){return false;}
        }

        return true;
    }

    // NOTE: If you resolve this, any non resolves-boolean children will be ignored, including other CompoundObject<CompoundObject<?>>
    @Override
    public Boolean Resolve()
    {
        return CheckBooleans();
    }
}
