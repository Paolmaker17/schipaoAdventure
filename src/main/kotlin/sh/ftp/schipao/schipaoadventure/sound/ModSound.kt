package sh.ftp.schipao.schipaoadventure.sound

import net.minecraft.block.jukebox.JukeboxSong
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import sh.ftp.schipao.schipaoadventure.SchipaoAdventure

object ModSounds {
    val PASTA: SoundEvent = registerSoundEvent("pasta")
    val PASTA_KEY: RegistryKey<JukeboxSong?>? =
        RegistryKey.of<JukeboxSong?>(RegistryKeys.JUKEBOX_SONG, Identifier.of(SchipaoAdventure.MOD_ID, "pasta"))


    private fun registerSoundEvent(name: String): SoundEvent {
        val id = Identifier.of(SchipaoAdventure.MOD_ID, name)
        return Registry.register<SoundEvent?, SoundEvent?>(Registries.SOUND_EVENT, id, SoundEvent.of(id))
    }

    fun registerSounds() {
        SchipaoAdventure.LOGGER.info("Registering Mod Sounds for " + SchipaoAdventure.MOD_ID)
    }
}