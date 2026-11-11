package sh.ftp.schipao.schipaoadventure.client.render

import net.minecraft.client.MinecraftClient
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity

object InvisRenderRules {

    fun shouldHideEquipment(entity: LivingEntity): Boolean {
        val target = entity as? PlayerEntity ?: return false

        if (!target.isInvisible) return false

        val viewer = MinecraftClient.getInstance().player
            ?: return false

        if (target.uuid == viewer.uuid) return true
        return target.isInvisibleTo(viewer)
    }
}