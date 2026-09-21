package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.client.network.ClientPlayerEntity

object Aes : PlayerClass() {

    override val name = "Aes"

    private var usedDoubleJump = false

    fun doubleJump(player: ClientPlayerEntity) {
        if (usedDoubleJump) return

        usedDoubleJump = true

        player.setVelocity(
            player.velocity.x,
            0.8,
            player.velocity.z
        )

        player.velocityModified = true
    }

    fun update(player: ClientPlayerEntity) {
        if (player.isOnGround) {
            usedDoubleJump = false
        }
    }
}