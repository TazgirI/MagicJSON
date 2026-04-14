package net.tazgirl.magicjson.statements.objects.compounds;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class And extends CompoundBase
{
    public And(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        boolean returnBool = true;
        for(Base base: values)
        {
            if(base.resolve() instanceof Boolean bool && !bool)
            {
                returnBool = false;
                if(breakOnFind){break;}
            }
        }
        return returnBool;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "And";
    }
}
