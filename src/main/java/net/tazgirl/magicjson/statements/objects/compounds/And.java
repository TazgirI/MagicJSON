package net.tazgirl.magicjson.statements.objects.compounds;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.NumericEvaluatorBase;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Parameter;
import java.util.function.BiFunction;

public class And extends CompoundBase
{
    public And(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        boolean returnBool = true;
        for(Base base: values)
        {
            if(base.Resolve() instanceof Boolean bool && !bool)
            {
                returnBool = false;
                if(breakOnFind){break;}
            }
        }
        return returnBool;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "And";
    }

    @Override
    public Boolean NumericalTest(Object rightHandOperand, NumericEvaluatorBase evaluator, boolean invert)
    {
        boolean returnResult = true;

        if(rightHandOperand instanceof Number num)
        {
            for(Base base: values)
            {
                boolean loopResult = true;
                Object resolveResult = base.Resolve();

                if(resolveResult instanceof Number num2)
                {
                    evaluator.getEvaluator().apply()
                }
            }
        }
    }
}
