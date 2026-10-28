
package sh.ftp.schipao.schipaoadventure

import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.util.hit.EntityHitResult

object LookTargetHelper {
    fun getTargetEntity(): Entity? {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return null

        val range = 100.0
        val start = player.getCameraPosVec(1.0f)
        val direction = player.getRotationVec(1.0f)
        val end = start.add(direction.multiply(range))

        val box = player.boundingBox.stretch(
            direction.multiply(range)
        ).expand(1.0)

        val hit = net.minecraft.entity.projectile.ProjectileUtil.raycast(
            player,
            start,
            end,
            box,
            { entity -> !entity.isSpectator && entity.isAlive },
            range * range
        )

        return (hit as? EntityHitResult)?.entity
    }
}
