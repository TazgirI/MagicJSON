package net.tazgirl.magicjson.statements.objects.complex_numeric;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Gaussian extends Base
{

    boolean untanh = false;

    public Gaussian(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        if(untanh)
        {
            return new Random().nextGaussian();
        }
        return Math.tanh(new Random().nextGaussian());
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        return false;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        if(string.equals(".untanh"))
        {
            untanh = true;
        }
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
        return "Gaussian";
    }

    @Override
    public String toString()
    {
        return identifier;
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {

    }
}
