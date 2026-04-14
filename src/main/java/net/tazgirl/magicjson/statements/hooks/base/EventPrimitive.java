package net.tazgirl.magicjson.statements.hooks.base;

import net.neoforged.bus.api.Event;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class EventPrimitive extends Base
{

    Event event;

    public EventPrimitive(StatementHolder holder)
    {
        super(holder);
    }

    public EventPrimitive(StatementHolder holder, Event event)
    {
        super(holder);
        this.event = event;
    }

    @Override
    public Object resolve()
    {
        return event;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        return false;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public Base implicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "";
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {

    }
}
