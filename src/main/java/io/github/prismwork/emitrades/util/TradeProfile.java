
package io.github.prismwork.emitrades.util;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public interface TradeProfile {
    TradeOffers.Factory offer();

    VillagerProfession profession();
