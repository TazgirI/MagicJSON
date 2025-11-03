package net.tazgirl.magicjson.main.statement_object.interface_categories;

import java.util.Map;

public interface ResolvesBoolean
{
    public Boolean Resolve();
    public void SpreadArgs(Map<String, Object> newArgs);
}
