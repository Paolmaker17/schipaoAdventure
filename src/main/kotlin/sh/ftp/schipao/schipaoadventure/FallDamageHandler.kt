package sh.ftp.schipao.schipaoadventure

import net.minecraft.entity.player.PlayerEntity

object FallDamageHandler {

    fun handleFallDamage(
        player: PlayerEntity,
        fallDistance: Float
    ): Boolean {
        return (player as? PlayerData)?.playerClass != 1
    }
}