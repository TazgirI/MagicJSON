package net.tazgirl.magicjson.statements.hooks;

import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.helpers.UAtoEnum;
import net.tazgirl.magicjson.statements.hooks.base.EventHook;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class LivingDamageEvent extends EventHook
{
    State state = State.PRE;

    public LivingDamageEvent(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object RunHook(Map<String, Base> hookArguments)
    {
        if(state == State.PRE)
        {
            return RunPre(hookArguments);
        }
        else
        {
            return RunPost(hookArguments);
        }
    }

    private Object RunPost(Map<String, Base> hookArguments)
    {
        if(hookArguments.get(Constants.eventParamName).Resolve() instanceof net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Post postEvent)
        {
            return switch(function)
            {
                case ".newdamage",".new" -> postEvent.getNewDamage();
                case ".originaldamage",".original" -> postEvent.getOriginalDamage();
                case ".blockeddamage", ".blocked" -> postEvent.getBlockedDamage();
                case ".entity", ".victim" -> postEvent.getEntity();
                case ".attacker" -> postEvent.getSource().getEntity();
                case ".directattacker", ".directentity" -> postEvent.getSource().getDirectEntity();

                default -> null;
            };
        }

        return null;
    }

    private Object RunPre(Map<String, Base> hookArguments)
    {
        if(hookArguments.get(Constants.eventParamName).Resolve() instanceof net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Pre preEvent)
        {
            return switch(function)
            {
                case ".newdamage",".new" -> preEvent.getNewDamage();
                case ".originaldamage",".original" -> preEvent.getOriginalDamage();
                case ".blockeddamage", ".blocked" -> preEvent.getContainer().getBlockedDamage();
                case ".entity", ".victim" -> preEvent.getEntity();
                case ".attacker" -> preEvent.getSource().getEntity();
                case ".directattacker", ".directentity" -> preEvent.getSource().getDirectEntity();
                default -> null;
            };
        }

        return null;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        if(UAtoEnum.get(State.class, string) instanceof State newState)
        {
            state = newState;
            return true;
        }
        
        return super.HandleUniqueArgument(string);
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "LivingDamage" + state.name().toLowerCase() + "( " + arguments + " )";
    }

    enum State
    {
        PRE,
        POST
    }
}
