package net.tazgirl.magicjson.statements.objects.compounds;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class Or extends CompoundBase
{
    public Or(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        boolean returnBool = true;
        for(Base base: values)
        {
            if(base.Resolve() instanceof Boolean bool && bool)
            {
                returnBool = false;
                if(breakOnFind){break;}
            }
        }
        return returnBool;
    }

    @Override

    @Override
    public @NotNull String setIdentifier()
    {
        return "Or";
    }
}
