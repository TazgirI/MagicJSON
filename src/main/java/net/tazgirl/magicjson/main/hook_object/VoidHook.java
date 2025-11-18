package net.tazgirl.magicjson.main.hook_object;

import net.tazgirl.magicjson.Constants;
import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;

import java.util.List;
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
