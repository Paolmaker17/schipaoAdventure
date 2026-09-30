package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import sh.ftp.schipao.schipaoadventure.playerclasses.PlayerClass

object Poaceae :PlayerClass() {

    /*
    Passive - Regen
    Passive - Speed
    */

    override val name = "Poaceae"

    fun update(player: PlayerEntity){
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 20.0

        player.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.REGENERATION,
                20,
                0,
                false,
                false,
                false
            )
        )
        player.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.SPEED,
                20,
                1,
                false,
                false,
                false
            )
        )

    }
}