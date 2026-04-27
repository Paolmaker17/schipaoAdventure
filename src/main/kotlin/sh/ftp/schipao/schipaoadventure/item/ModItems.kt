package sh.ftp.schipao.schipaoadventure.item

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sh.ftp.schipao.schipaoadventure.SchipaoAdventure

object ModItems {
    val RAW_MITHRIL :Item = registerItem("raw_mithril", Item(Item.Settings()))
    val MITHRIL_INGOT :Item = registerItem("mithril_ingot", Item(Item.Settings()))

    fun registerModItems(){
        SchipaoAdventure.LOGGER.info("Registering Mod Items for "+ SchipaoAdventure.MOD_ID)
    }

    private fun registerItem(name :String, item :Item) :Item{
        return Registry.register(Registries.ITEM, Identifier.of(SchipaoAdventure.MOD_ID, name), item)
    }

}