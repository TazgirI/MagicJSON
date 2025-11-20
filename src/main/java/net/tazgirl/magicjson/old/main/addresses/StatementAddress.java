package net.tazgirl.magicjson.old.main.addresses;

import net.tazgirl.magicjson.old.PrivateCore;
import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;

public class StatementAddress extends Address
{
    public StatementAddress(String localAddress)
    {
        super(localAddress);
    }

    public static StatementAddress from(String address)
    {
        return new StatementAddress(address);
    }

    @Override
    public BaseStatementObject getAsObject()
    {
        return PrivateCore.getStatement(localAddress);
    }
}
