package com.minelittlepony.unicopia.item;

import static com.minelittlepony.unicopia.EquinePredicates.*;

import com.minelittlepony.unicopia.USounds;
import com.minelittlepony.unicopia.Unicopia;
import com.minelittlepony.unicopia.block.UBlocks;
import com.minelittlepony.unicopia.entity.UEntities;
import com.minelittlepony.unicopia.magic.spell.ScorchSpell;
import com.minelittlepony.unicopia.toxin.DynamicToxicBlockItem;
import com.minelittlepony.unicopia.toxin.DynamicToxicItem;
import com.minelittlepony.unicopia.toxin.ToxicBlockItem;
import com.minelittlepony.unicopia.toxin.ToxicItem;
import com.minelittlepony.unicopia.toxin.Toxicity;
import com.minelittlepony.unicopia.toxin.Toxin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Items;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.TallBlockItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;
import net.minecraft.util.registry.Registry;

public interface UItems {

    AppleItem GREEN_APPLE = register(new AppleItem(FoodComponents.APPLE), "apple_green");
    AppleItem SWEET_APPLE = register(new AppleItem(FoodComponents.APPLE), "apple_sweet");
    AppleItem SOUR_APPLE = register(new AppleItem(FoodComponents.APPLE), "apple_sour");

    ZapAppleItem ZAP_APPLE = register(new ZapAppleItem(), "zap_apple");

    AppleItem ROTTEN_APPLE = register(new RottenAppleItem(FoodComponents.APPLE), "rotten_apple");
    AppleItem cooked_zap_apple = register(new AppleItem(FoodComponents.APPLE), "cooked_zap_apple");

    Item CLOUD_MATTER = register(new Item(new Item.Settings().group(ItemGroup.MATERIALS)), "cloud_matter");
    Item DEW_DROP = register(new Item(new Item.Settings().group(ItemGroup.MATERIALS)), "dew_drop");

    CloudPlacerItem RACING_CLOUD_SPAWNER = register(new CloudPlacerItem(UEntities.RACING_CLOUD), "racing_cloud_spawner");
    CloudPlacerItem CONSTRUCTION_CLOUD_SPAWNER = register(new CloudPlacerItem(UEntities.CONSTRUCTION_CLOUD), "construction_cloud_spawner");
    CloudPlacerItem WILD_CLOUD_SPAWNER = register(new CloudPlacerItem(UEntities.WILD_CLOUD), "wild_cloud_spawner");

    Item CLOUD_BLOCK = register(new PredicatedBlockItem(UBlocks.CLOUD_BLOCK, new Item.Settings().group(ItemGroup.MATERIALS), INTERACT_WITH_CLOUDS), "cloud_block");
    Item ENCHANTED_CLOUD_BLOCK = register(new PredicatedBlockItem(UBlocks.ENCHANTED_CLOUD_BLOCK, new Item.Settings().group(ItemGroup.MATERIALS), INTERACT_WITH_CLOUDS), "enchanted_cloud_block");
    Item PACKED_CLOUD_BLOCK = register(new PredicatedBlockItem(UBlocks.DENSE_CLOUD_BLOCK, new Item.Settings().group(ItemGroup.MATERIALS), INTERACT_WITH_CLOUDS), "packed_cloud_block");

    Item CLOUD_STAIRS = register(new PredicatedBlockItem(UBlocks.CLOUD_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS), INTERACT_WITH_CLOUDS), "cloud_stairs");

    Item CLOUD_FENCE = register(new PredicatedBlockItem(UBlocks.CLOUD_FENCE, new Item.Settings().group(ItemGroup.DECORATIONS), INTERACT_WITH_CLOUDS), "cloud_fence");

    Item CLOUD_ANVIL = register(new PredicatedBlockItem(UBlocks.CLOUD_ANVIL, new Item.Settings().group(ItemGroup.DECORATIONS), INTERACT_WITH_CLOUDS), "cloud_anvil");

    Item MUSIC_DISC_CRUSADE = register(createRecord(USounds.RECORD_CRUSADE), "music_disc_crusade");
    Item MUSIC_DISC_PET = register(createRecord(USounds.RECORD_PET), "music_disc_pet");
    Item MUSIC_DISC_POPULAR = register(createRecord(USounds.RECORD_POPULAR), "music_disc_popular");
    Item MUSIC_DISC_FUNK = register(createRecord(USounds.RECORD_FUNK), "music_disc_funk");

    Item HIVE_WALL_BLOCK = register(new BlockItem(UBlocks.HIVE_WALL_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)), "hive_wall_block");
    Item CHITIN_SHELL = register(new Item(new Item.Settings().group(ItemGroup.MATERIALS)), "chitin_shell");
    Item CHITIN_SHELL_BLOCK = register(new BlockItem(UBlocks.CHITIN_SHELL_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)), "chitin_shell_block");
    Item CHISELED_CHITIN_SHELL_BLOCK = register(new BlockItem(UBlocks.CHISELED_CHITIN_SHELL_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)), "chiseled_chitin_shell_block");
    Item SLIME_DROP = register(new BlockItem(UBlocks.SLIME_DROP, new Item.Settings().group(ItemGroup.MATERIALS)), "slime_drop");
    Item SLIME_LAYER = register(new BlockItem(UBlocks.SLIME_LAYER, new Item.Settings().group(ItemGroup.DECORATIONS)), "slime_layer");

    Item MISTED_DOOR = register(new TallBlockItem(UBlocks.MISTED_GLASS_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)), "misted_door");
    Item LIBRARY_DOOR = register(new TallBlockItem(UBlocks.LIBRARY_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)), "library_door");
    Item BAKERY_DOOR = register(new TallBlockItem(UBlocks.BAKERY_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)), "bakery_door");
    Item DIAMOND_DOOR = register(new TallBlockItem(UBlocks.DIAMOND_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)), "diamond_door");

    Item SUGAR_BLOCK = register(new BlockItem(UBlocks.SUGAR_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)), "sugar_block");

    Item CLOUD_SLAB = register(new PredicatedBlockItem(UBlocks.CLOUD_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS), INTERACT_WITH_CLOUDS), "cloud_slab");
    Item ENCHANTED_CLOUD_SLAB = register(new PredicatedBlockItem(UBlocks.ENCHANTED_CLOUD_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS), INTERACT_WITH_CLOUDS), "enchanted_cloud_slab");
    Item DENSE_CLOUD_SLAB = register(new PredicatedBlockItem(UBlocks.DENSE_CLOUD_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS), INTERACT_WITH_CLOUDS), "dense_cloud_slab");

    MagicGemItem GEM = register(new MagicGemItem(), "gem");
    MagicGemItem CORRUPTED_GEM = register(new CursedMagicGemItem(), "corrupted_gem");

    BagOfHoldingItem BAG_OF_HOLDING = register(new BagOfHoldingItem(), "bag_of_holding");
    AlicornAmuletItem ALICORN_AMULET = register(new AlicornAmuletItem(), "alicorn_amulet");

    SpellbookItem SPELLBOOK = register(new SpellbookItem(), "spellbook");
    Item STAFF_MEADOW_BROOK = register(new StaffItem(new Item.Settings().maxCount(1).maxDamage(2)), "staff_meadow_brook");
    Item STAFF_REMEMBERANCE = register(new EnchantedStaffItem(new Item.Settings(), new ScorchSpell()), "staff_remembrance");

    Item SPEAR = register(new SpearItem(new Item.Settings().maxCount(1).maxDamage(500)), "spear");

    MossItem MOSS = register(new MossItem(new Item.Settings()), "moss");

    Item ALFALFA_SEEDS = register(new AliasedBlockItem(UBlocks.ALFALFA_CROPS, new Item.Settings()
            .group(ItemGroup.MATERIALS)
            .food(new FoodComponent.Builder()
                    .hunger(1)
                    .saturationModifier(4)
                    .build())), "alfalfa_seeds");
    Item ALFALFA_LEAVES = register(new Item(new Item.Settings()
            .group(ItemGroup.FOOD)
            .food(new FoodComponent.Builder()
                    .hunger(1)
                    .saturationModifier(3)
                    .build())), "alfalfa_leaves");

    Item ENCHANTED_TORCH = register(new BlockItem(UBlocks.ENCHANTED_TORCH, new Item.Settings().group(ItemGroup.DECORATIONS)), "enchanted_torch");


    Item CEREAL = register(new SugaryItem(new Item.Settings()
            .group(ItemGroup.FOOD)
            .food(new FoodComponent.Builder()
                    .hunger(9)
                    .saturationModifier(0.8F)
                    .build())
            .maxCount(1)
            .recipeRemainder(Items.BOWL), 1), "cereal");
    Item SUGAR_CEREAL = register(new SugaryItem(new Item.Settings()
            .group(ItemGroup.FOOD)
            .food(new FoodComponent.Builder()
                    .hunger(20)
                    .saturationModifier(-2)
                    .build())
            .maxCount(1)
            .recipeRemainder(Items.BOWL), 110), "sugar_cereal");

    TomatoSeedsItem TOMATO_SEEDS = register(new TomatoSeedsItem(), "tomato_seeds");
    TomatoItem TOMATO = register(new TomatoItem(4, 34), "tomato");
    RottenTomatoItem ROTTEN_TOMATO = register(new RottenTomatoItem(4, 34), "rotten_tomato");

    TomatoItem CLOUDSDALE_TOMATO = register(new TomatoItem(16, 4), "cloudsdale_tomato");
    RottenTomatoItem ROTTEN_CLOUDSDALE_TOMATO = register(new RottenTomatoItem(5, 34), "rotten_cloudsdale_tomato");

    Item APPLE_SEEDS = register(new BlockItem(UBlocks.APPLE_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)), "apple_seeds");
    Item APPLE_LEAVES = register(new BlockItem(UBlocks.APPLE_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)), "apple_leaves");

    Item DAFFODIL_DAISY_SANDWICH = register(new DynamicToxicItem(new Item.Settings(), 3, 2, UseAction.EAT, Toxicity::fromStack), "daffodil_daisy_sandwich");
    Item HAY_BURGER = register(new DynamicToxicItem(new Item.Settings(), 3, 4, UseAction.EAT, Toxicity::fromStack), "hay_burger");
    Item HAY_FRIES = register(new ToxicItem(new Item.Settings(), 1, 5, UseAction.EAT, Toxicity.SAFE), "hay_fries");
    Item SALAD = register(new DynamicToxicItem(new Item.Settings().recipeRemainder(Items.BOWL), 4, 2, UseAction.EAT, Toxicity::fromStack), "salad");

    Item WHEAT_WORMS = register(new ToxicItem(new Item.Settings(), 1, 0, UseAction.EAT, Toxicity.SEVERE), "wheat_worms");
    Item MUG = register(new Item(new Item.Settings().group(ItemGroup.MATERIALS)), "mug");
    Item CIDER = register(new ToxicItem(new Item.Settings().recipeRemainder(MUG), 4, 2, UseAction.DRINK, Toxicity.MILD), "apple_cider");
    Item JUICE = register(new ToxicItem(new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE), 2, 2, UseAction.DRINK, Toxicity.SAFE), "juice");
    Item BURNED_JUICE = register(new ToxicItem(new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE), 3, 1, UseAction.DRINK, Toxicity.FAIR), "burned_juice");

    Item CLOUD_SPAWN_EGG = register(new SpawnEggItem(UEntities.CLOUD, 0x4169e1, 0x7fff00, new Item.Settings().group(ItemGroup.MISC)), "cloud_spawn_egg");
    Item BUTTERFLY_SPAWN_EGG = register(new SpawnEggItem(UEntities.BUTTERFLY, 0x222200, 0xaaeeff, new Item.Settings().group(ItemGroup.MISC)), "cloud_spawn_egg");

    static <T extends Item> T register(T item, String name) {
        return Registry.ITEM.add(new Identifier(Unicopia.MODID, name), item);
    }

    static MusicDiscItem createRecord(SoundEvent sound) {
        return new MusicDiscItem(1, sound, new Item.Settings()
                .maxCount(1)
                .group(ItemGroup.MISC)
                .rarity(Rarity.RARE)
            ) {};
    }

    static void bootstrap() {
        // FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(zap_apple), new ItemStack(cooked_zap_apple), 0.1F);
        // FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(juice), new ItemStack(burned_juice), 0);
        // FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(cuccoon), new ItemStack(chitin_shell), 0.3F);
    }

    interface VanillaOverrides {
        StickItem STICK = register(new StickItem(), Items.STICK);
        ExtendedShearsItem SHEARS = register(new ExtendedShearsItem(), Items.SHEARS);

        AppleItem APPLE = register(new AppleItem(FoodComponents.APPLE), Items.APPLE);

        Item GRASS = register(new DynamicToxicBlockItem(Blocks.GRASS, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE, Toxin.NAUSEA), Items.GRASS);
        Item FERN = register(new DynamicToxicBlockItem(Blocks.FERN, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SEVERE, Toxin.STRENGTH), Items.FERN);
        Item DEAD_BUSH = register(new DynamicToxicBlockItem(Blocks.DEAD_BUSH, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SEVERE, Toxin.NAUSEA), Items.DEAD_BUSH);

        Item DANDELION = register(new ToxicBlockItem(Blocks.DANDELION, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.DANDELION);
        Item POPPY = register(new ToxicBlockItem(Blocks.POPPY, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SEVERE), Items.POPPY);
        Item BLUE_ORCHID = register(new ToxicBlockItem(Blocks.BLUE_ORCHID, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.BLUE_ORCHID);
        Item ALLIUM = register(new ToxicBlockItem(Blocks.ALLIUM, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.FAIR), Items.ALLIUM);
        Item AZUER_BLUET = register(new DynamicToxicBlockItem(Blocks.AZURE_BLUET, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE, Toxin.RADIOACTIVITY), Items.AZURE_BLUET);
        Item RED_TULIP = register(new ToxicBlockItem(Blocks.RED_TULIP, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.RED_TULIP);
        Item ORANGE_TULIP = register(new ToxicBlockItem(Blocks.ORANGE_TULIP, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.ORANGE_TULIP);
        Item WHITE_TULIP = register(new ToxicBlockItem(Blocks.WHITE_TULIP, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.FAIR), Items.WHITE_TULIP);
        Item PINK_TULIP = register(new ToxicBlockItem(Blocks.PINK_TULIP, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.PINK_TULIP);
        Item OXEYE_DAISY = register(new DynamicToxicBlockItem(Blocks.OXEYE_DAISY, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SEVERE, Toxin.BLINDNESS), Items.OXEYE_DAISY);
        Item CORNFLOWER = register(new ToxicBlockItem(Blocks.CORNFLOWER, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.CORNFLOWER);

        Item ROSE_BUSH = register(new DynamicToxicBlockItem(Blocks.ROSE_BUSH, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE, Toxin.DAMAGE), Items.ROSE_BUSH);
        Item PEONY = register(new ToxicBlockItem(Blocks.PEONY, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.PEONY);
        Item TALL_GRASS = register(new ToxicBlockItem(Blocks.TALL_GRASS, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SAFE), Items.TALL_GRASS);
        Item LARGE_FERN = register(new DynamicToxicBlockItem(Blocks.LARGE_FERN, new Item.Settings().group(ItemGroup.DECORATIONS), 2, 1, UseAction.EAT, Toxicity.SEVERE, Toxin.DAMAGE), Items.LARGE_FERN);

        static <T extends Item> T register(T newItem, Item oldItem) {
            return Registry.ITEM.set(Registry.ITEM.getRawId(oldItem), Registry.ITEM.getId(oldItem), newItem);
        }
    }
}
