package net.tazgirl.magicjson.old.main.statement_object.interface_categories;

import net.tazgirl.magicjson.old.main.statement_object.StatementManager;

public interface ResolvesBoolean extends BaseStatementInterface
{
    // Interface isn't for safety, just so methods that specify they want a ResolvesBoolean can access the methods from BaseStatement
    // A little annoying but the interface tag system saves more time than is spent moving things to interface

    Boolean Resolve();
    void SpreadManager(StatementManager newManager);
}
