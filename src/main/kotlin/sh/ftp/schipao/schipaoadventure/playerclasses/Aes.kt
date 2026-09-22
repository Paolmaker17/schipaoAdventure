package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import sh.ftp.schipao.schipaoadventure.particle.ModParticles


object Aes : PlayerClass() {

    //Passive - Double jump
    override val name = "Aes"

    private var usedDoubleJump = false

    fun doubleJump(player: ClientPlayerEntity) {
        if (usedDoubleJump) return

        usedDoubleJump = true

        player.setVelocity(
            player.velocity.x + player.rotationVector.x * 0.2,
            0.8,
            player.velocity.z + player.rotationVector.z * 0.2
        )

        player.velocityModified = true
    }

    fun update(player: ClientPlayerEntity) {
        if (player.isOnGround) {
            usedDoubleJump = false
        }

        if (usedDoubleJump) {
            repeat(5) {
                player.clientWorld.addParticle(
                    ModParticles.AES_PARTICLE,
                    player.x + (Math.random() - 0.5) * 0.4,
                    player.y + Math.random() * 0.8,
                    player.z + (Math.random() - 0.5) * 0.4,
                    0.0,
                    0.02,
                    0.0
                )
            }
        }
    }
}