package net.tazgirl.magicjson.statements.objects.numeric_evaluators;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.compounds.CompoundBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Equals extends NumericEvaluatorBase
{
    @Override
    BiFunction<Number, Number, Boolean> createEvaluator()
    {
        return null;
    }


//    List<List<Object>> allResolveValues = new ArrayList<>();
//    int maxLoops = 1;
//    List<Integer> compoundIndexes = new ArrayList<>();
//
//        for(
//    Base base: values)
//    {
//        allResolveValues.add(new ArrayList<>());
//    }
//
//        for(int i = 0; i < values.size(); i++)
//    {
//        Base base = values.get(i);
//        if(base instanceof CompoundBase compoundBase)
//        {
//            compoundIndexes.add(i);
//            if(compoundBase.numberOfContents() > maxLoops)
//            {
//                maxLoops = compoundBase.numberOfContents();
//            }
//        }
//        else
//        {
//            allResolveValues.get(i).add(values.get(i).Resolve());
//        }
//    }
//
//
//        for(int i = 0; i < maxLoops; i++)
//    {
//        List<Number> roundSet = new ArrayList<>();
//
//        for(int j = 0; j < values.size(); j++)
//        {
//            if(values.get(j) instanceof CompoundBase compoundBase)
//            {
//                allResolveValues.get(j).add(compoundBase.ResolveSpecific(i));
//            }
//        }
//
//    }
//

}
