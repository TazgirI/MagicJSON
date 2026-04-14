package net.tazgirl.magicjson.subscription;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tazgirl.magicjson.StatementLoadingCompleteEvent;
import net.tazgirl.magicjson.subscription.base.SubscriptionFetcher;

import java.util.List;
import java.util.Map;

@EventBusSubscriber
public class LivingDamageSubscribers
{
    @EventBusSubscriber
    public static class Pre
    {
        private static final String dir = "LivingDamage/Pre";
        static List<EventSubscriptionHolder<LivingDamageEvent.Pre>> holders;

        @SubscribeEvent
        public static void onStatementLoadingComplete(StatementLoadingCompleteEvent event)
        {
            holders = SubscriptionFetcher.setupSubscribers(dir, LivingDamagePreSubscriptionHolder::new, NeoForge.EVENT_BUS);
        }

        public static class LivingDamagePreSubscriptionHolder extends EventSubscriptionHolder<LivingDamageEvent.Pre>
        {
            public LivingDamagePreSubscriptionHolder(String address, EventPriority priority)
            {
                super(address, priority, LivingDamageEvent.Pre.class);
            }

            @Override
            Map<String, Object> constructArgs(LivingDamageEvent.Pre event)
            {
                return Map.of();
            }
        }
    }

    @EventBusSubscriber
    public static class Post
    {
        private static final String dir = "LivingDamage/Post";
        static List<EventSubscriptionHolder<LivingDamageEvent.Post>> holders;

        @SubscribeEvent
        public static void onStatementLoadingComplete(StatementLoadingCompleteEvent event)
        {
            holders = SubscriptionFetcher.setupSubscribers(dir, LivingDamagePostSubscriptionHolder::new, NeoForge.EVENT_BUS);
        }

        public static class LivingDamagePostSubscriptionHolder extends EventSubscriptionHolder<LivingDamageEvent.Post>
        {
            public LivingDamagePostSubscriptionHolder(String address, EventPriority priority)
            {
                super(address, priority, LivingDamageEvent.Post.class);
            }

            @Override
            Map<String, Object> constructArgs(LivingDamageEvent.Post event)
            {
                return Map.of();
            }
        }
    }
}
