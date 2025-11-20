package net.tazgirl.magicjson.old.main.statement_object.interface_categories;

import net.tazgirl.magicjson.old.main.statement_object.StatementManager;

public interface ResolvesObject extends BaseStatementInterface
{
    // This is for tagging objects of an unkown return value, this has become neccesary with the intended shift to removing functions

    Object Resolve();
    void SpreadManager(StatementManager newManager);
}
