package sh.ftp.schipao.schipaoadventure.playerclasses

import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import sh.ftp.schipao.schipaoadventure.playerclasses.PlayerClass

object Poaceae :PlayerClass() {

    /*
    Passive - Fire touch
    Passive - Fire breathing
    */

    override val name = "Poaceae"

    fun update(player: PlayerEntity){
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 20.0
    }
}