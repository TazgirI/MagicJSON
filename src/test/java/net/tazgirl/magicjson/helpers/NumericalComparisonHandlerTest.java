package net.tazgirl.magicjson.helpers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.compounds.And;
import net.tazgirl.magicjson.statements.objects.compounds.Or;
import net.tazgirl.magicjson.statements.objects.evaluation.Equals;
import net.tazgirl.magicjson.statements.objects.primitives.numbers.IntegerObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumericalComparisonHandlerTest
{
    @Test
    public void initialTest()
    {
        StatementHolder holder = new StatementHolder(StatementHolder.TEST_CASE.THIS_IS_A_TEST_OPTION_ONLY_FOR_CONTROLLED_SITUATIONS);

        And leftOperand = new And(holder);
        leftOperand.handleBase(new IntegerObject(holder, 2));
        leftOperand.handleBase(new IntegerObject(holder, 5));

        Or rightOperand = new Or(holder);
        rightOperand.handleBase(new IntegerObject(holder, 2));
        rightOperand.handleBase(new IntegerObject(holder, 5));

        Equals equals = new Equals(holder);
        equals.handleBase(leftOperand);
        equals.handleBase(rightOperand);

        Assertions.assertEquals(true, equals.resolve());
    }
}