package net.tazgirl.magicjson.old.main.addresses;

import net.tazgirl.magicjson.old.PrivateCore;
import net.tazgirl.magicjson.old.main.function_object.SourceFunctionHolder;

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
