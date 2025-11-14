package net.tazgirl.magicjson.main.statement_object;

import net.tazgirl.magicjson.main.addresses.StatementAddress;

import java.util.HashMap;
import java.util.Map;

public class StatementManager
{
    StatementAddress statementAddress;

    Map<String, Object> args = new HashMap<>();

    public StatementManager(StatementAddress statementAddress,Map<String, Object> args)
    {
        this.args = args;
        this.statementAddress = statementAddress;
    }

    public StatementAddress getStatementAddress()
    {
        return statementAddress;
    }

    public Map<String, Object> getArgs()
    {
        return args;
    }

}
