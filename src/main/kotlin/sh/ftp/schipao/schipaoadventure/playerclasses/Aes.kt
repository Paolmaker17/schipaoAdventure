package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.packet.s2c.play.PositionFlag
import net.minecraft.server.world.ServerWorld

object Aes : PlayerClass("Aes", PlayerClassInt.AES) {

    /*
    Passive - Double jump
    Passive - No fall damage
    */

    val usedDoubleJump = mutableSetOf<java.util.UUID>()
    private val teleportCooldowns = mutableMapOf<java.util.UUID, Int>()
    const val TELEPORT_COOLDOWN = 60 // 3 seconds

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

    fun entityTeleport(player: PlayerEntity, target: LivingEntity): Boolean {
        val uuid = player.uuid

        if ((teleportCooldowns[uuid] ?: 0) > 0) return false

        val behind = target.pos.subtract(
            target.rotationVector.multiply(1.5)
        )

        val world = target.world as? ServerWorld ?: return false
        val success = player.teleport(
            world,
            behind.x,
            behind.y,
            behind.z,
            emptySet<PositionFlag>(),
            target.yaw,
            target.pitch
        )

        if (!success) return false

        teleportCooldowns[uuid] = TELEPORT_COOLDOWN
        return true
    }

    fun update(player: PlayerEntity) {
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 20.0

        if (player.isOnGround)
            usedDoubleJump.remove(player.uuid)

        val cooldown = teleportCooldowns[player.uuid] ?: 0
        if (cooldown > 0) {
            teleportCooldowns[player.uuid] = cooldown - 1
        } else {
            teleportCooldowns.remove(player.uuid)
        }
    }
}