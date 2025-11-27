package net.tazgirl.magicjson.old.main.hook_object.return_types;

import net.tazgirl.magicjson.old.main.hook_object.HookParameters;
import net.tazgirl.magicjson.old.main.hook_object.ReturnHook;

import java.util.function.Function;

public class FloatHook extends ReturnHook<Float>
{
    public FloatHook(Function<HookParameters, Float> function)
    {
        super(function);
    }
}
