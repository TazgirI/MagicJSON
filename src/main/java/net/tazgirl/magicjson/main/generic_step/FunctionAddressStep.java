package net.tazgirl.magicjson.main.generic_step;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.main.addresses.FunctionAddress;
import net.tazgirl.magicjson.main.function_object.FunctionManager;

public class FunctionAddressStep extends Step<FunctionAddress>
{
    public FunctionAddressStep(FunctionAddress value)
    {
        super(value);
    }

    @Override
    public FunctionManager getExecutable(FunctionManager manager)
    {
        return manager.copyWithNewAddress(value);
    }

    @Override
    public Object getAsReturnValue(FunctionManager manager)
    {
        return MagicJson.RunFunction(getExecutable(manager));
    }
}
