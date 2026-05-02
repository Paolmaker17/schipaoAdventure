package sh.ftp.schipao.schipaoadventure.item.custom

import net.minecraft.item.Item
import net.minecraft.command.argument.EntityArgumentType.player
import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import sh.ftp.schipao.schipaoadventure.item.ModItems

class FeatherItem(settings: Settings) : Item(settings){
    override fun inventoryTick(
        stack: ItemStack,
        world: World,
        entity: Entity,
        slot: Int,
        selected: Boolean) {

        if (entity is PlayerEntity && !world.isClient) {

            if (!entity.isOnGround && !entity.hasStatusEffect(StatusEffects.SLOW_FALLING)) {
                entity.addStatusEffect(
                    StatusEffectInstance(
                        StatusEffects.SLOW_FALLING,
                        20,
                        0,
                        true,
                        false
                    )
                )
            } else if (entity.isOnGround){
                entity.removeStatusEffect(StatusEffects.SLOW_FALLING)
            }

        }

        super.inventoryTick(stack, world, entity, slot, selected)
    }
}