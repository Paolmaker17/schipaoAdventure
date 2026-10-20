package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity

object Aqua : PlayerClass("Aqua", PlayerClassInt.AQUA) {

    /*
    Passive - 15 hearts
    Passive - Water breathing
    */

    fun update(player: PlayerEntity){
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 30.0

        player.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.WATER_BREATHING,
                20,
                0,
                false,
                false,
                false
            )
        )
    }
}