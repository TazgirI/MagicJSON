package net.tazgirl.magicjson.helpers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.compounds.And;
import net.tazgirl.magicjson.statements.objects.compounds.Or;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.Equals;
import net.tazgirl.magicjson.statements.objects.primitives.numbers.IntegerObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumericalComparisonHandlerTest
{
    @Test
    public void initialTest()
    {
        StatementHolder holder = new StatementHolder();

        And leftOperand = new And(holder);
        leftOperand.HandleBase(new IntegerObject(holder, 2));
        leftOperand.HandleBase(new IntegerObject(holder, 5));

        Or rightOperand = new Or(holder);
        rightOperand.HandleBase(new IntegerObject(holder, 2));
        rightOperand.HandleBase(new IntegerObject(holder, 5));

        Equals equals = new Equals(holder);
        equals.HandleBase(leftOperand);
        equals.HandleBase(rightOperand);

        Assertions.assertEquals(true, equals.Resolve());
    }
}