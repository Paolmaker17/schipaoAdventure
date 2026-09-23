package sh.ftp.schipao.schipaoadventure.particle

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.SimpleParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import sh.ftp.schipao.schipaoadventure.SchipaoAdventure


object ModParticles {
    val AES_PARTICLE :SimpleParticleType =
        registerParticle("aes_particle", FabricParticleTypes.simple())
    val AES_PARTICLE_2 :SimpleParticleType =
        registerParticle("aes_particle_2", FabricParticleTypes.simple())
    val AES_PARTICLE_3 :SimpleParticleType =
        registerParticle("aes_particle_3", FabricParticleTypes.simple())

    private fun registerParticle(name: String?, particleType: SimpleParticleType): SimpleParticleType {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SchipaoAdventure.MOD_ID, name), particleType)
    }

    fun registerParticles() {
        SchipaoAdventure.LOGGER.info("Registering Particles for " + SchipaoAdventure.MOD_ID)
    }
}