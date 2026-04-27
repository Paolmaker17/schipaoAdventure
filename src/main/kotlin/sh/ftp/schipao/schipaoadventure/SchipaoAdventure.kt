package sh.ftp.schipao.schipaoadventure

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.ftp.schipao.schipaoadventure.item.ModItemGroups
import sh.ftp.schipao.schipaoadventure.item.ModItems

object SchipaoAdventure : ModInitializer {
	const val MOD_ID :String = "schipaoadventure"
    val LOGGER :Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItemGroups.registerItemGroups()

		ModItems.registerModItems()
	}
}