package net.tazgirl.magicjson.main.statement_object.interface_categories;

import net.tazgirl.magicjson.main.statement_object.StatementManager;

public interface ResolvesString extends BaseStatementInterface
{
    // Interface isn't for safety, just so methods that specify they want a ResolvesString can access the methods from BaseStatement
    // A little annoying but the interface tag system saves more time than is spent moving things to interface

    String Resolve();
    void SpreadManager(StatementManager newManager);
}
