package net.tazgirl.magicjson.optionals;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.optionals.tests.ResultTest;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class StringStatementOptional extends StatementOptional<String> implements IStatementOptional<String>, CharSequence, Comparable<String>
{
    private static final String executablePrefix = "\\_";

    private StringStatementOptional(OptionalValue<String> optionalValue)
    {
        super(optionalValue);
    }

    // String statement objects check starting characters to determine if they are to be executed or returned raw
    // TODO: Ensure this is recorded in documentation
    public static StringStatementOptional from(String string)
    {
        if(string.startsWith(executablePrefix))
        {
            MJLogging.Info("StringStatementOptional determined to be executable: " + string);
            return new StringStatementOptional(OptionalValue.from(string.substring(2), ResultTest.STRING));
        }

        MJLogging.Info("StringStatementOptional determined to be literal: " + string);

        return new StringStatementOptional(OptionalValue.from(string));
    }

    @Override
    public int length()
    {
        String result = optionalValue.get();
        return result == null ? -1 : result.length();
    }

    @Override
    public char charAt(int index)
    {

        return optionalValue.get().charAt(index);
    }

    @Override
    public @NotNull CharSequence subSequence(int start, int end)
    {
        return optionalValue.get().subSequence(start, end);
    }

    @Override
    public int compareTo(@NotNull String o)
    {
        return o.compareTo(optionalValue.get());
    }

    @Override
    public OptionalValue<String> getOptional()
    {
        return optionalValue;
    }
}
