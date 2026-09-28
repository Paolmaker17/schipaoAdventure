package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import sh.ftp.schipao.schipaoadventure.particle.ModParticles


object Aes : PlayerClass() {

    /*
    Passive - Double jump
    Passive - No fall damage
    */

    override val name = "Aes"
    private var usedDoubleJump = false
    //private var usedDash = false

    fun doubleJump(player: ClientPlayerEntity) {
        if (usedDoubleJump) return

        usedDoubleJump = true

        player.setVelocity(
            player.velocity.x + player.rotationVector.x * 0.2,
            0.8,
            player.velocity.z + player.rotationVector.z * 0.2
        )
        player.velocityModified = true

        if (usedDoubleJump) {
            repeat(15) {
                val offsetX = (Math.random() - 0.5) * 0.4
                val offsetY = Math.random() * 0.8
                val offsetZ = (Math.random() - 0.5) * 0.4

                player.clientWorld.addParticle(ModParticles.AES_PARTICLE,
                    player.x + offsetX, player.y + offsetY, player.z + offsetZ, 0.0, 0.02, 0.0)
                player.clientWorld.addParticle(ModParticles.AES_PARTICLE_2,
                    player.x + offsetX, player.y + offsetY, player.z + offsetZ, 0.0, 0.02, 0.0)
                player.clientWorld.addParticle(ModParticles.AES_PARTICLE_3,
                    player.x + offsetX, player.y + offsetY, player.z + offsetZ, 0.0, 0.02, 0.0)
            }
        }
    }
/*
    fun dash(player: ClientPlayerEntity) {
        if (usedDash) return

        usedDash = true

        player.setVelocity(
            player.velocity.x + player.rotationVector.x * 0.5,
            player.velocity.y,
            player.velocity.z + player.rotationVector.z * 0.5
        )
        player.velocityModified = true

        if (usedDash) {
            repeat(15) {
                val offsetX = (Math.random() - 0.5) * 0.4
                val offsetY = Math.random() * 0.8
                val offsetZ = (Math.random() - 0.5) * 0.4

                player.clientWorld.addParticle(ModParticles.AES_PARTICLE,
                    player.x + offsetX, player.y + offsetY, player.z + offsetZ, 0.0, 0.02, 0.0)
                player.clientWorld.addParticle(ModParticles.AES_PARTICLE_2,
                    player.x + offsetX, player.y + offsetY, player.z + offsetZ, 0.0, 0.02, 0.0)
                player.clientWorld.addParticle(ModParticles.AES_PARTICLE_3,
                    player.x + offsetX, player.y + offsetY, player.z + offsetZ, 0.0, 0.02, 0.0)
            }
        }
    }
*/
    fun update(player: ClientPlayerEntity) {
        if (player.isOnGround) {
            usedDoubleJump = false
        }
    }
}