package net.tazgirl.magicjson.subscription;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tazgirl.magicjson.StatementLoadingCompleteEvent;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.subscription.base.ExecutableAddress;
import net.tazgirl.magicjson.subscription.base.StatementPriorityQueue;
import net.tazgirl.magicjson.subscription.base.SubscriptionFetcher;

import java.util.Map;

public class LivingDamageEventSubscriber
{
    static StatementPriorityQueue preSubscribers;
    static StatementPriorityQueue postSubscribers;

    @SubscribeEvent
    public static void OnStatementLoadingComplete(StatementLoadingCompleteEvent event)
    {
        preSubscribers = SubscriptionFetcher.fetchAddresses("LivingDamage/Pre");
        postSubscribers = SubscriptionFetcher.fetchAddresses("LivingDamage/Post");
    }

    @SubscribeEvent
    public static void OnLivingDamagePre(LivingDamageEvent.Pre event)
    {
        preSubscribers.Run(Map.of(Constants.eventParamName, event));
    }

    @SubscribeEvent
    public static void OnLivingDamagePre(LivingDamageEvent.Post event)
    {
        postSubscribers.Run(Map.of(Constants.eventParamName, event));
    }
}
