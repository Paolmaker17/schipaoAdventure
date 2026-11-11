package sh.ftp.schipao.schipaoadventure.client.render

import net.minecraft.entity.LivingEntity

object InvisRenderContext {
    val current = ThreadLocal<LivingEntity?>()
}