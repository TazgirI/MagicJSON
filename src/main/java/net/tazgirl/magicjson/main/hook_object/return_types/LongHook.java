package net.tazgirl.magicjson.main.hook_object.return_types;

import net.tazgirl.magicjson.main.hook_object.HookParameters;
import net.tazgirl.magicjson.main.hook_object.ReturnHook;

import java.util.function.Function;

public class LongHook extends ReturnHook<Long>
{
    public LongHook(Function<HookParameters, Long> function)
    {
        super(function);
    }
}
