package net.tazgirl.magicjson;

import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.HashMap;
import java.util.Map;

public class PrivateCore
{
    private static final Map<String, StatementHolder> statementRegister = new HashMap<>();

    public static Object runStatement(String address)
    {
        if(Constants.server != null && statementRegister.get(address) instanceof StatementHolder holder)
        {
            return holder.run();
        }
        return null;
    }

    public static Object runStatement(String address, Map<String, Object> args)
    {
        if(Constants.server != null && statementRegister.get(address) instanceof StatementHolder holder)
        {
            holder.setArgs(args);
            return holder.run();
        }
        return null;
    }

    public static StatementResultAndArgs runStatementAndGetFinalArgs(String address)
    {
        return runStatementAndGetFinalArgs(address,new HashMap<>());
    }

    public static StatementResultAndArgs runStatementAndGetFinalArgs(String address, Map<String, Object> args)
    {
        if(Constants.server != null && statementRegister.get(address) instanceof StatementHolder holder)
        {
            holder.setArgs(args);
            return holder.runAndReturnFinalArgs();
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
            MJLogging.warn(address + " was just overwritten during Statement registration, if two mods are conflicting then you must create a synonym");
        }
    }

    public static boolean hasStatement(String address)
    {
        return statementRegister.containsKey(address);
    }

    static void clearHolderRelations()
    {
        for(StatementHolder holder: statementRegister.values())
        {
            holder.clearRelations();
        }
    }

    public record StatementResultAndArgs(Object result, Map<String, Object> args)
    {

    }
}
