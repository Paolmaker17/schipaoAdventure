package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity

object Aes : PlayerClass("Aes", PlayerClassInt.AES) {

    /*
    Passive - Double jump
    Passive - No fall damage
    */

    val usedDoubleJump = mutableSetOf<java.util.UUID>()

    fun doubleJump(player: PlayerEntity): Boolean {
        val uuid = player.uuid

        if (usedDoubleJump.contains(uuid)) return false

        usedDoubleJump.add(uuid)
        player.setVelocity(
            player.velocity.x + player.rotationVector.x * 0.2,
            0.8,
            player.velocity.z + player.rotationVector.z * 0.2
        )
        player.velocityModified = true

        return true
    }

    fun update(player: PlayerEntity) {
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 20.0

        if (player.isOnGround)
            usedDoubleJump.remove(player.uuid)
    }
}