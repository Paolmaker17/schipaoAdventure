package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity

object Ignis : PlayerClass("Ignis", PlayerClassInt.IGNIS) {

    /*
    Passive - Fire touch
    Passive - Fire resistance
    */

    fun onAttack(player: PlayerEntity, entity: LivingEntity) {
        entity.setOnFireFor(4F)
    }

    fun update(player: PlayerEntity){
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 20.0

        player.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.FIRE_RESISTANCE,
                20,
                0,
                false,
                false,
                false
            )
        )
    }
}