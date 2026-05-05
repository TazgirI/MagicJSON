package net.tazgirl.magicjson.subscription.base;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.PrivateCore;

import java.util.Map;

public class ExecutableAddress
{
    public String address;
    public Map<String, Object> preArgs = null;

    public ExecutableAddress(String address)
    {
        this.address = address;
    }

    public ExecutableAddress(String address, Map<String, Object> preArgs)
    {
        this.address = address;
        this.preArgs = preArgs;
    }

    public Object Run()
    {
        if(preArgs == null)
        {
            return MagicJson.runStatement(address);
        }
        else
        {
            return MagicJson.runStatement(address, preArgs);
        }

    }

    public Object Run(Map<String, Object> args)
    {
        return MagicJson.runStatement(address, args);
    }

    public void setPreArgs(Map<String, Object> preArgs)
    {
        this.preArgs = preArgs;
    }
}
