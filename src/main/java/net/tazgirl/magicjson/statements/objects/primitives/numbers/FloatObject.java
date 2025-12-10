package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;
import org.jetbrains.annotations.NotNull;

public class FloatObject extends NumberObject<Float>
{
    public FloatObject(StatementHolder holder)
    {
        super(holder);
    }



    @Override
    protected Class<? extends Number> setType()
    {
        return Float.class;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Float";
    }
}
