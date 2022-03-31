
package io.github.prismwork.emitrades;

import com.google.common.collect.ImmutableSet;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import io.github.prismwork.emitrades.config.EMITradesConfig;
import io.github.prismwork.emitrades.recipe.VillagerTrade;
import io.github.prismwork.emitrades.util.EntityEmiStack;
import io.github.prismwork.emitrades.util.TradeProfile;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class EMITradesPlugin implements EmiPlugin {
    public static final VillagerProfession WANDERING_TRADER_PLACEHOLDER = new VillagerProfession(
            "wandering_trader",
            entry -> false,
            entry -> false,
            ImmutableSet.<Item>builder().build(),
            ImmutableSet.<Block>builder().build(),
            SoundEvents.ENTITY_WANDERING_TRADER_YES
    );
    public static final EmiRecipeCategory VILLAGER_TRADES
            = new EmiRecipeCategory(new Identifier("emitrades", "villager_trades"), EmiStack.of(Items.EMERALD));
    public static EMITradesConfig.Config CONFIG;
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("emitrades.json5").toFile();

    @Override
    public void register(EmiRegistry registry) {
        CONFIG = EMITradesConfig.load(CONFIG_FILE);