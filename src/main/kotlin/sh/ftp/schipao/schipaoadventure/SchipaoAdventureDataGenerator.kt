package sh.ftp.schipao.schipaoadventure

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import sh.ftp.schipao.schipaoadventure.datagen.ModModelProvider

object SchipaoAdventureDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()

		pack.addProvider { output ->
			ModModelProvider(output as FabricDataOutput)
		}
	}
}