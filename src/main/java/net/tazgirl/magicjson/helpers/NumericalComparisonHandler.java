package net.tazgirl.magicjson.helpers;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.compounds.And;
import net.tazgirl.magicjson.statements.objects.compounds.CompoundBase;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.NumericEvaluatorBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static net.tazgirl.magicjson.data.Constants.trueEmpty.TRUE_EMPTY;

public class NumericalComparisonHandler
{
    public static Boolean RunTest(Base leftOperand, Base rightOperand, NumericEvaluatorBase evaluator)
    {
        boolean leftIsCompound = leftOperand instanceof CompoundBase;
        boolean rightIsCompound = rightOperand instanceof CompoundBase;

        if(!leftIsCompound && !rightIsCompound)
        {
            return bothNonCompounds(leftOperand, rightOperand, evaluator);
        }

        if(!leftIsCompound)
        {
            return oneSideCompound(leftOperand, (CompoundBase) rightOperand, evaluator, false);
        }
        if(!rightIsCompound)
        {
            return oneSideCompound(rightOperand, (CompoundBase) leftOperand, evaluator,true);
        }

        return bothSidesCompound((CompoundBase) leftOperand, (CompoundBase) rightOperand, evaluator);



    }

    // AND < OR -> all AND < any number from OR
    // AND < AND -> all left AND < all right AND
    // OR < AND -> any OR < all of AND
    // OR < OR -> any left OR < any right OR

    // Ands must be processed in their entirety before processing the OR step by step
    // This means booleans in an AND will take precendent before the OR is looked at
    // ORRRR we only compare about Booleans in Ands once we reach their value? So a regular return in the OR doesn't trigger anything but an earlier index boolean could take priority
    static Boolean bothSidesCompound(CompoundBase leftOperand, CompoundBase rightOperand, NumericEvaluatorBase evaluator)
    {
        // (is AND)
        boolean leftDefault = leftOperand instanceof And;
        boolean rightDefault = rightOperand instanceof And;

        // TODO: May be pointless with ANDS being pre-processed
        if(!leftOperand.breakOnFind && !rightOperand.breakOnFind)
        {
            return noBreakComparison(leftOperand, rightOperand, evaluator, leftDefault, rightDefault);
        }

        if(!leftDefault && !rightDefault)
        {
            return compareOrs(leftOperand, rightOperand, evaluator);
        }
        if(leftDefault && rightDefault)
        {
            return compareAnds(leftOperand, rightOperand, evaluator);
        }


        return compareAndToOr(leftOperand, rightOperand, evaluator, leftDefault, rightDefault);
    }

    // WARN: Ensure documentation states any AND to OR comparison will cause the AND to call any functions up to a break point (end or false) before anything in the OR is checked
    static Boolean compareAndToOr(CompoundBase leftOperand, CompoundBase rightOperand, NumericEvaluatorBase evaluator, boolean leftDefault, boolean rightDefault)
    {
        // NOTE: Always assume the left most value is the AND, if that is incorrect the invert the equation

        // If leftDefault is true then it is AND and we are handling AND < OR, if it is false then it is an OR and we are handling OR < AND or OR > AND after invert
        BiFunction<Number, Number, Boolean> evaluatorFunction;

        boolean flipped = false;
        int andFirstExit = -1;

        if(leftDefault)
        {
            evaluatorFunction = evaluator.getEvaluator();
        }
        else
        {
            flipped = true;
            evaluatorFunction = evaluator.getDirectionalInverseEvaluator();
            CompoundBase holder = rightOperand;
            rightOperand = leftOperand;
            leftOperand = holder;
        }


        List<Object> leftResults = new ArrayList<>();

        for(int i = 0; i < leftOperand.numberOfContents(); i++)
        {
            Object leftResult = leftOperand.ResolveSpecific(i);

            leftResults.add(leftResult);

            if(leftResult instanceof Boolean bool && !bool && andFirstExit == -1)
            {
                andFirstExit = i;

                if(leftOperand.breakOnFind)
                {
                    break;
                }
            }
        }

        if(andFirstExit != -1)
        {
            return compareAndToOrNoNumeracy(rightOperand, andFirstExit, flipped);
        }

        List<Number> leftNumbers = leftResults.stream().filter(Number.class::isInstance).map(Number.class::cast).toList();
        List<Number> rightNumbers = new ArrayList<>();

        boolean returnValue = false;
        boolean returnFound = false;
        for(int i = 0; i < rightOperand.numberOfContents(); i++)
        {
            Object resolveResult = rightOperand.ResolveSpecific(i);

            if(returnFound)
            {
                continue;
            }

            if(resolveResult instanceof Number num)
            {
                rightNumbers.add(num);

                // Only runs comparison if rightNumbers has updated
                if(listCompare(leftNumbers, rightNumbers, evaluatorFunction, true, false))
                {
                    returnValue = true;
                    returnFound = true;
                }
            }
            else if(resolveResult instanceof Boolean bool && bool)
            {
                returnValue = true;
                returnFound = true;
            }

            if(returnFound && rightOperand.breakOnFind)
            {
                break;
            }
        }

        return returnValue;


    }


    // Apparently I had difficulty visualising this structure, so a lot of comments were used
    // TODO: Check if rewrite is needed once I can visualise the structure
    static Boolean compareAndToOrNoNumeracy(CompoundBase rightOperand, int andFirstExit, boolean flipped)
    {
        boolean returnValue = false;
        boolean returnFound = false;

        // No numeric comparison needed as there's a guaranteed exit point
        for(int i = 0; i < rightOperand.numberOfContents(); i++)
        {
            // If rightOperand is actually secretly on the left then it has priority in case of exit ties and so andFirstExit should be 1 higher
            int priorityPass = flipped ? 1 : 0;

            // OR(1 2 3 4 true) < AND(1 2 3 4 false) flipped is true so AND(1 2 3 4 false) > OR(1 2 3 4 true)
            // andFirstExit = 4
            // On i == 4, !4 >= 5! so current loop should finish
            // AND(1 2 3 4 false) < OR(1 2 3 4 true) flipped is false
            // On i == 4, 4 >= 4 so current loop should close if the if allows it
            if((i >= andFirstExit + priorityPass || returnFound) && rightOperand.breakOnFind)
            {
                break;
            }


            Object resolveResult = rightOperand.ResolveSpecific(i);

            // andFirstExit = 4 && i = 4
            // flipped is true: 4 < 5, return value is stored
            // flipped is false: !4 < 4!, returnValue is ignored
            if(i < andFirstExit + priorityPass && resolveResult instanceof Boolean bool && bool)
            {
                returnValue = true;
                returnFound = true;
            }
        }
        return returnValue;
    }

    static Boolean compareAnds(CompoundBase leftOperand, CompoundBase rightOperand, NumericEvaluatorBase evaluator)
    {
        List<Number> leftNumbers = new ArrayList<>();
        List<Number> rightNumbers = new ArrayList<>();

        boolean returnNotFound = true;

        for(int i  = 0; i < Math.max(leftOperand.numberOfContents(), rightOperand.numberOfContents()); i++)
        {
            Object leftResult = (returnNotFound || !leftOperand.breakOnFind) && leftOperand.numberOfContents() > i ? leftOperand.ResolveSpecific(i) : null;
            Object rightResult = (returnNotFound || !rightOperand.breakOnFind) && rightOperand.numberOfContents() > i ? rightOperand.ResolveSpecific(i) : null;

            if(leftResult == null && rightResult == null)
            {
                break;
            }

            if((leftResult instanceof Boolean bool1 && !bool1) || (rightResult instanceof Boolean bool2 && !bool2))
            {
                returnNotFound = false;
            }

            if(leftResult instanceof Number num)
            {
                leftNumbers.add(num);
            }
            if(rightResult instanceof Number num)
            {
                rightNumbers.add(num);
            }
        }

        if(returnNotFound)
        {
            returnNotFound = listCompare(leftNumbers, rightNumbers, evaluator.getEvaluator(), true, true);
        }


        // If no return found then the ANDS returned true for all tests
        return returnNotFound;
    }

    static Boolean compareOrs(CompoundBase leftOperand, CompoundBase rightOperand, NumericEvaluatorBase evaluator)
    {
        List<Number> leftNumbers = new ArrayList<>();
        List<Number> rightNumbers = new ArrayList<>();

        boolean returnFound = false;
        for(int i  = 0; i < Math.max(leftOperand.numberOfContents(), rightOperand.numberOfContents()); i++)
        {
            Object leftResult = (!returnFound || !leftOperand.breakOnFind) && leftOperand.numberOfContents() > i ? leftOperand.ResolveSpecific(i) : null;
            Object rightResult = (!returnFound || !rightOperand.breakOnFind) && rightOperand.numberOfContents() > i ? rightOperand.ResolveSpecific(i) : null;

            if(leftResult == null && rightResult == null)
            {
                break;
            }

            if(!returnFound)
            {
                if((leftResult instanceof Boolean bool1 && bool1) || (rightResult instanceof Boolean bool2 && bool2))
                {
                    return true;
                }

                if(leftResult instanceof Number num)
                {
                    leftNumbers.add(num);
                }
                if(rightResult instanceof Number num)
                {
                    rightNumbers.add(num);
                }

                if(listCompare(leftNumbers, rightNumbers, evaluator.getEvaluator(), false, false))
                {
                    returnFound = true;
                }
            }
        }

        return returnFound;
    }

    static boolean listCompare(List<Number> leftNumbers, List<Number> rightNumbers, BiFunction<Number, Number, Boolean> evaluatorFunction, boolean leftDefault, boolean rightDefault)
    {
        // All examples use < or lessThan
        boolean returnValue;

        if(leftDefault && rightDefault)
        {
            // All leftNum are less than all rightNum
            returnValue = true;

            for(Number leftNum: leftNumbers)
            {
                for (Number rightNum: rightNumbers)
                {
                    if(!evaluatorFunction.apply(leftNum, rightNum))
                    {
                        // Could return here but breakign twice for readability
                        returnValue = false;
                        break;
                    }
                }
                if(!returnValue)
                {
                    break;
                }
            }
            return returnValue;
        }
        if(leftDefault)
        {
            // All leftNum are less than any rightNum
            returnValue = true;
            for(Number leftNum: leftNumbers)
            {
                boolean loopBool = false;
                for(Number rightNum: rightNumbers)
                {
                    if(evaluatorFunction.apply(leftNum, rightNum))
                    {
                        loopBool = true;
                        break;
                    }
                }
                if(!loopBool)
                {
                    returnValue = false;
                    break;
                }
            }
            return returnValue;
        }
        if(rightDefault)
        {
            // Any leftNum is less than every rightNum
            returnValue = false;
            for(Number leftNum: leftNumbers)
            {
                boolean loopBool = false;
                for(Number rightNum: rightNumbers)
                {
                    loopBool = evaluatorFunction.apply(leftNum, rightNum);
                    if(!loopBool){break;}
                }
                if(loopBool)
                {
                    returnValue = true;
                    break;
                }
            }
            return returnValue;
        }

        returnValue = false;

        // Any leftNum is less than any rightNum
        for(Number leftNum: leftNumbers)
        {
            for(Number rightNum: rightNumbers)
            {
                if(evaluatorFunction.apply(leftNum, rightNum))
                {
                    returnValue = true;
                    break;
                }
            }
            if(returnValue)
            {
                break;
            }
        }

        return returnValue;
    }

    static Boolean noBreakComparison(CompoundBase leftOperand, CompoundBase rightOperand, NumericEvaluatorBase evaluator, boolean leftDefault, boolean rightDefault)
    {
        List<Number> leftNums = new ArrayList<>();
        List<Number> rightNums = new ArrayList<>();

        int cycles = Math.max(leftOperand.numberOfContents(), rightOperand.numberOfContents());

        for(int i = 0; i < cycles; i++)
        {
            Object leftResult = leftOperand.numberOfContents() > i ? leftOperand.ResolveSpecific(i) : null;
            Object rightResult = rightOperand.numberOfContents() > i ? rightOperand.ResolveSpecific(i) : null;

            if(leftResult instanceof Boolean bool && bool != leftDefault)
            {
                return bool;
            }
            else if(leftResult instanceof Number num)
            {
                leftNums.add(num);
            }
            if(rightResult instanceof Boolean bool && bool != rightDefault)
            {
                return bool;
            }
            else if(rightResult instanceof Number num)
            {
                rightNums.add(num);
            }
        }
        // If operand is an OR containing a true, return true
        // If operand is an AND containing a false, return false
        // i.e. if leftOverrideBool == false and leftDefault = true (meaning it's an and) then comparison is skipped as the boolean makes it pointless

        return listCompare(leftNums,rightNums, evaluator.getEvaluator(), leftDefault, rightDefault);
    }

    // TODO: This is so abhorrently fucked, mainly when dealing with opening Boolean edge cases
    // Haveing an IF at the start of the for loop would cut down on the opening nest but would reduce performance for more cycles
    static Boolean singleToOr(Base leftOperand, CompoundBase rightOperand, BiFunction<Number, Number, Boolean> evaluator, boolean inverted)
    {
        boolean returnValue = false;
        boolean returnFound = false;

        int jumpStep = 0;

        Number leftNum = null;

        if(inverted)
        {
            jumpStep = 1;

            Object tempRight = rightOperand.ResolveSpecific(0);

            if(tempRight instanceof Boolean bool && bool)
            {
                returnValue = true;
                returnFound = true;
            }
            else
            {
                Object leftResolveHolder = leftOperand.Resolve();
                if(leftResolveHolder instanceof Boolean bool)
                {
                    returnValue = bool;
                    returnFound = true;
                }
                else if(leftResolveHolder instanceof Number num)
                {
                    leftNum = num;
                    if(tempRight instanceof Number rightNum && evaluator.apply(leftNum, rightNum))
                    {
                        returnValue = true;
                        returnFound = true;
                    }
                }
                else
                {
                    returnValue = false;
                    returnFound = true;
                }
            }

        }


        for(int i = jumpStep; i < rightOperand.numberOfContents(); i++)
        {

            Object rightResult = rightOperand.ResolveSpecific(i);

            if(returnFound)
            {
                continue;
            }

            if(rightResult instanceof Number num && evaluator.apply(leftNum, num))
            {
                returnValue = true;
                returnFound = true;
                if(rightOperand.breakOnFind)
                {
                    break;
                }
            }
            else if(rightResult instanceof Boolean bool && bool)
            {
                returnValue = true;
                returnFound = true;
                if(rightOperand.breakOnFind)
                {
                    break;
                }
            }
        }

        return returnValue;
    }

    static Boolean singleToAnd(Base leftOperand, CompoundBase rightOperand, BiFunction<Number, Number, Boolean> evaluator)
    {

    }

    static Boolean oneSideCompound(Base leftOperand, CompoundBase rightOperand, NumericEvaluatorBase evaluator, Boolean invertDirection)
    {
        boolean defaultResult = rightOperand instanceof And;
        boolean returnResult = defaultResult;
        BiFunction<Number, Number, Boolean> evaluatorFunction = invertDirection ? evaluator.getDirectionalInverseEvaluator() : evaluator.getEvaluator();

        Object leftResult = leftOperand.Resolve();
        Number leftNum;

        if(leftResult instanceof Boolean bool){return bool;}

        if(leftResult instanceof Number num){leftNum = num;}
        else{return true;}

        for(int i = 0; i < rightOperand.numberOfContents(); i++)
        {
            boolean loopResult = defaultResult;
            Object compoundLoopResult = rightOperand.ResolveSpecific(i);

            if(compoundLoopResult instanceof Boolean bool){loopResult = bool;}
            else if(compoundLoopResult instanceof Number number)
            {
                loopResult = evaluatorFunction.apply(leftNum, number);
            }


            if(loopResult != defaultResult)
            {
                returnResult = loopResult;
                if(rightOperand.breakOnFind)
                {
                    break;
                }
            }
        }

        return returnResult;
    }

    // If no nums found it defaults to false
    static Boolean bothNonCompounds(Base leftOperand, Base rightOperand, NumericEvaluatorBase evaluator)
    {
        Object leftResult = leftOperand.Resolve();
        Object rightResult = rightOperand.Resolve();

        if(leftResult instanceof Boolean){return (Boolean) leftResult;}
        if(rightResult instanceof Boolean){return (Boolean) rightResult;}

        if(leftResult instanceof Number leftNumber && rightResult instanceof Number rightNumber)
        {
            return evaluator.getEvaluator().apply(leftNumber, rightNumber);
        }

        return false;
    }
}
