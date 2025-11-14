package net.tazgirl.magicjson.main.generic_step;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.FunctionManager;
import net.tazgirl.magicjson.main.statement_object.StatementManager;

public class StatementAddressStep extends Step<StatementAddress>
{

    public StatementAddressStep(StatementAddress value)
    {
        super(value);
    }

    @Override
    public StatementManager getExecutable(FunctionManager manager)
    {
        return manager.generateStatementManager(value);
    }

    @Override
    public Object getAsReturnValue(FunctionManager manager)
    {
        return MagicJson.RunStatement(getExecutable(manager));
    }
}
