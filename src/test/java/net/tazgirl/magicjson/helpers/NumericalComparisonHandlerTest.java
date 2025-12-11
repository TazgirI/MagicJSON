package net.tazgirl.magicjson.helpers;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.compounds.And;
import net.tazgirl.magicjson.statements.objects.compounds.Or;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.Equals;
import net.tazgirl.magicjson.statements.objects.primitives.numbers.IntegerObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumericalComparisonHandlerTest
{
    @Test
    public void initialTest()
    {
        StatementHolder holder = new StatementHolder();

        And leftOperand = new And(holder);
        leftOperand.HandleValue(new IntegerObject(holder, 2));
        leftOperand.HandleValue(new IntegerObject(holder, 5));

        Or rightOperand = new Or(holder);
        rightOperand.HandleValue(new IntegerObject(holder, 2));
        rightOperand.HandleValue(new IntegerObject(holder, 5));

        Equals equals = new Equals(holder);
        equals.HandleValue(leftOperand);
        equals.HandleValue(rightOperand);

        Assertions.assertEquals(true, equals.Resolve());
    }
}