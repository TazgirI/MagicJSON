package net.tazgirl.magicjson.main.addresses;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.main.function_object.SourceFunctionHolder;
import net.tazgirl.magicjson.main.function_object.objects.BaseFunctionObject;

public class FunctionAddress extends Address
{
    public FunctionAddress(String localAddress)
    {
        super(localAddress);
    }

    public static FunctionAddress from(String address)
    {
        return new FunctionAddress(address);
    }

    @Override
    public SourceFunctionHolder getAsObject()
    {
        return PrivateCore.getFunction(localAddress);
    }
}
