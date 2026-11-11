package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.packet.s2c.play.PositionFlag
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import sh.ftp.schipao.schipaoadventure.LookTargetHelper
import sh.ftp.schipao.schipaoadventure.ScaleUtil.setPlayerScale
import java.util.UUID

object Aes : PlayerClass("Aes", PlayerClassInt.AES) {

    /*
    Passive - Double jump
    Passive - No fall damage

    Primary - Tp behind entity
    Secondary - Invisibility
    */

    val usedDoubleJump = mutableSetOf<UUID>()
    private val teleportCooldowns = mutableMapOf<UUID, Int>()
    private val invisibilityCooldowns = mutableMapOf<UUID, Int>()

    fun doubleJump(player: PlayerEntity): Boolean {
        val uuid = player.uuid

        if (usedDoubleJump.contains(uuid)) return false

        usedDoubleJump.add(uuid)
        player.setVelocity(
            player.velocity.x + player.rotationVector.x * 0.3,
            0.8,
            player.velocity.z + player.rotationVector.z * 0.3
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

        target.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.GLOWING,
                40,
                0,
                false,
                false,
                false
            )
        )

        if (!success) return false

        teleportCooldowns[uuid] = 60
        return true
    }

    fun invisibility(player: PlayerEntity): Boolean {
        val serverPlayer = player as? ServerPlayerEntity ?: return false
        val uuid = player.uuid

        if ((invisibilityCooldowns[uuid] ?: 0) > 0) {
            return false
        }

        syncClass1Team(serverPlayer, true)

        serverPlayer.removeStatusEffect(StatusEffects.INVISIBILITY)

        serverPlayer.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.INVISIBILITY,
                200,
                0,
                false,
                false,
                false
            )
        )
        invisibilityCooldowns[uuid] = 200
        return true
    }

    fun syncClass1Team(player: ServerPlayerEntity, isClass1: Boolean) {
        val world = player.world as? ServerWorld ?: return
        val scoreboard = world.server.scoreboard

        val team = scoreboard.getTeam("aes")
            ?: if (isClass1) {
                scoreboard.addTeam("aes")
            } else {
                return
            }

        team.setShowFriendlyInvisibles(true)

        val playerName = player.name.string

        if (isClass1) {
            scoreboard.addScoreHolderToTeam(playerName, team)
        } else {
            if (scoreboard.getScoreHolderTeam(playerName) == team) {
                scoreboard.clearTeam(playerName)
            }
        }
    }

    fun update(player: PlayerEntity) {
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 20.0
        setPlayerScale(player, 1.0)

        if (player.isOnGround)
            usedDoubleJump.remove(player.uuid)

        val cooldown = teleportCooldowns[player.uuid] ?: 0
        if (cooldown > 0)
            teleportCooldowns[player.uuid] = cooldown - 1
        else
            teleportCooldowns.remove(player.uuid)


        val invisCooldown = invisibilityCooldowns[player.uuid] ?: 0
        if (invisCooldown > 0)
            invisibilityCooldowns[player.uuid] = invisCooldown - 1
        else
            invisibilityCooldowns.remove(player.uuid)

    }
}