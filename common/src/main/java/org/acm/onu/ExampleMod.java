package org.acm.onu;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;


public final class ExampleMod {
    public static final String MOD_ID = "acmexamplemod";
    
    // BLOCK REGISTRY
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);
    public static final RegistrySupplier<Block> CRUNCHWRAP_BLOCK = BLOCKS.register(
    		"crunchwrap_block", () -> new Block(
			BlockBehaviour.Properties.of()
				.mapColor(MapColor.COLOR_BLACK)
				.instrument(NoteBlockInstrument.BASEDRUM)
				.requiresCorrectToolForDrops()
				.strength(50.0F, 1200.0F)
			)
    );
    
    // CREATIVE TAB
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
     
    public static final RegistrySupplier<CreativeModeTab> MY_TAB = TABS.register(
            "gangstertacobell_creative_tab", // Tab ID
            () -> CreativeTabRegistry.create(
                    Component.translatable("creativetab.gangster_taco_bell"), // Tab Name
                    () -> new ItemStack(Items.STONE) // Icon
            )
    );
    
    // CUSTOM FOOD PROPERTY
    public static final FoodProperties CRUNCHWRAP_SUPREME_PROPERTIES = new FoodProperties.Builder().nutrition(20).saturationMod(2.0F).meat().build();
    
    // ITEM REGISTRY
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<Item> CRUNCHWRAP_SUPREME_ITEM = ITEMS.register(
    		"crunchwrap_supreme", () -> new Item(new Item.Properties().food(CRUNCHWRAP_SUPREME_PROPERTIES).arch$tab(MY_TAB)));
    public static final RegistrySupplier<Item> CRUNCHWRAP_BLOCK_ITEM = ITEMS.register(
    		"crunchwrap_block", () -> new BlockItem(CRUNCHWRAP_BLOCK.get(), new Item.Properties().arch$tab(MY_TAB)));
    

    public static void init() {
        // Write common initialization code here.
        // We will make most of our code here and in new Java files in this folder.
    	BLOCKS.register();
    	ITEMS.register();
    	TABS.register();
    }
}
