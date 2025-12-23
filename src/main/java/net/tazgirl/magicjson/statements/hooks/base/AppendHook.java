package net.tazgirl.magicjson.statements.hooks.base;

import net.tazgirl.magicjson.processing.Stack;
import net.tazgirl.magicjson.processing.TokensToHolder;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

public abstract class AppendHook
{
    public abstract void Trigger(Base appendee, Stack stack, StatementHolder holder, TokensToHolder tokensToHolder);
}
