package net.tazgirl.magicjson.statements.objects.memory.args;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public abstract class ArgBase extends Base
{
    public ArgBase(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        // Handled before any arguments are input basically just allows Arg.get() instead of Argget
        // On second thought, I'm not going to add Argget so all Arg instructions are through unique arguments
        switch (string)
        {
            case ".get" ->
            {
                holder.Replace(this, new ArgGet(holder));
                return true;
            }
            case ".set" ->
            {
                holder.Replace(this, new ArgSet(holder));
                return true;
            }
            case ".create", ".make", ".new" ->
            {
                holder.Replace(this, new ArgCreate(holder));
                return true;
            }
        }
        return false;
    }
}
