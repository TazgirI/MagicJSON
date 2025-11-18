package net.tazgirl.magicjson.main.hook_object.return_types;

import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;
import net.tazgirl.magicjson.main.hook_object.HookParameter;
import net.tazgirl.magicjson.main.hook_object.HookParameters;
import net.tazgirl.magicjson.main.hook_object.ReturnHook;

import java.util.List;
import java.util.function.Function;

public class IntegerHook extends ReturnHook<Integer>
{
    public IntegerHook(Function<HookParameters, Integer> function)
    {
        super(function);
    }
}
