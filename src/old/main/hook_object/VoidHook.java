package net.tazgirl.magicjson.old.main.hook_object;

import net.tazgirl.magicjson.old.Constants;

import java.util.function.Consumer;

public class VoidHook extends Hook<Consumer<HookParameters>>
{
    public VoidHook(Consumer<HookParameters> function)
    {
        super(function);
    }

    @Override
    public Object RunHook(HookParameters parameters)
    {
        function.accept(parameters);
        return Constants.trueEmpty;
    }
}
