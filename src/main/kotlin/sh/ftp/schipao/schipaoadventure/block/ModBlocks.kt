package sh.ftp.schipao.schipaoadventure.block

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.CraftingTableBlock
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.block.MapColor
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.math.intprovider.UniformIntProvider
import sh.ftp.schipao.schipaoadventure.SchipaoAdventure

object ModBlocks {
    val MITHRIL_ORE :Block = registerBlock("mithril_ore",
        ExperienceDroppingBlock(
            UniformIntProvider.create(3, 7),
            AbstractBlock.Settings.create()
                .mapColor(MapColor.STONE_GRAY)
                .sounds(BlockSoundGroup.STONE)
                .strength(3f, 4f)
                .requiresTool()
        ))

    val DEEPSLATE_MITHRIL_ORE :Block = registerBlock("deepslate_mithril_ore",
        ExperienceDroppingBlock(
            UniformIntProvider.create(3, 7),
            AbstractBlock.Settings.create()
                .mapColor(MapColor.DEEPSLATE_GRAY)
                .sounds(BlockSoundGroup.DEEPSLATE)
                .strength(3.5f, 4f)
                .requiresTool()
        ))

    val MITHRIL_BLOCK :Block = registerBlock("mithril_block",
        Block(AbstractBlock.Settings.create()
                .mapColor(MapColor.DARK_RED)
                .sounds(BlockSoundGroup.METAL)
                .strength(5.0F, 6.0F)
                .requiresTool()
        )
    )

    val RAW_MITHRIL_BLOCK :Block = registerBlock("raw_mithril_block",
        Block(AbstractBlock.Settings.create()
                .mapColor(MapColor.DARK_RED)
                .sounds(BlockSoundGroup.METAL)
                .strength(5.0F, 6.0F)
                .requiresTool()
        )
    )

    private fun registerBlock(name :String, block : Block) :Block{
        registerBlockItem(name, block)
        return Registry.register(Registries.BLOCK, Identifier.of(SchipaoAdventure.MOD_ID, name), block)
    }

    private fun registerBlockItem(name :String, block : Block){
        Registry.register(Registries.ITEM, Identifier.of(SchipaoAdventure.MOD_ID, name),
            BlockItem(block, Item.Settings()))
    }

    fun registerModBlocks(){
        SchipaoAdventure.LOGGER.info("Registering Mod Blocks"+ SchipaoAdventure.MOD_ID)
    }
}