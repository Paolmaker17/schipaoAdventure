package sh.ftp.schipao.schipaoadventure

import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity

//Default 1.0f

object ScaleUtil {
    fun setPlayerScale(player: PlayerEntity, scaleValue: Double) {
        val scaleAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_SCALE)

        if (scaleAttribute != null) {
            scaleAttribute.setBaseValue(scaleValue)
        }
    }
}