package net.tazgirl.magicjson.old.main.statement_object;

import net.tazgirl.magicjson.old.main.addresses.StatementAddress;

import java.util.Map;

public class StatementManager
{
    StatementAddress statementAddress;

    Map<String, Object> args;

    public StatementManager(StatementAddress statementAddress, Map<String, Object> args)
    {
        this.args = args;
        this.statementAddress = statementAddress;
    }

    public StatementManager(Map<String, Object> args)
    {
        this.args = args;
        this.statementAddress = StatementAddress.from("none");
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
