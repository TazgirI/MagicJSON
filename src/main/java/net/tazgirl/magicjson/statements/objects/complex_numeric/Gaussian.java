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
    public Object Resolve()
    {
        if(untanh)
        {
            return new Random().nextGaussian();
        }
        return Math.tanh(new Random().nextGaussian());
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        if(string.equals(".untanh"))
        {
            untanh = true;
        }
        return false;
    }

    @Override
    public Base ImplicitChild()
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
    public void Replace(Base oldBase, Base newBase)
    {

    }
}
