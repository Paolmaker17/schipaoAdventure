package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import sh.ftp.schipao.schipaoadventure.playerclasses.PlayerClass

object Ignis : PlayerClass() {

    /*
    Passive - Fire touch
    Passive - Fire resistance
    */

    override val name = "Ignis"

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