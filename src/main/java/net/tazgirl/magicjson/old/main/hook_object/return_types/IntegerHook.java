package net.tazgirl.magicjson.old.main.hook_object.return_types;

import net.tazgirl.magicjson.old.main.hook_object.HookParameters;
import net.tazgirl.magicjson.old.main.hook_object.ReturnHook;

import java.util.function.Function;

public class IntegerHook extends ReturnHook<Integer>
{
    public IntegerHook(Function<HookParameters, Integer> function)
    {
        super(function);
    }
}
