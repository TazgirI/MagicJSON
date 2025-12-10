package net.tazgirl.magicjson;

import net.neoforged.neoforge.server.timings.ObjectTimings;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.HashMap;
import java.util.Map;

public class PrivateCore
{
    private static final Map<String, StatementHolder> statementRegister = new HashMap<>();

    public static Object runStatement(String address)
    {
        if(statementRegister.get(address) instanceof StatementHolder holder)
        {
            return holder.Run();
        }
        return null;
    }

    public static String addressBuilder(String namespace, String fileNameAndPath)
    {
        return namespace + ":" + fileNameAndPath;
    }

    static void putStatement(String address, StatementHolder holder)
    {
        StatementHolder existing = statementRegister.put(address, holder);

        if(existing != null)
        {
            Logging.Warn(address + " was just overwritten during Statement registration, if two mods are conflicting then you must create a synonym");
        }
    }
}
