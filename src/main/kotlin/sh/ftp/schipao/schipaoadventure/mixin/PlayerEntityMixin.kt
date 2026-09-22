package sh.ftp.schipao.schipaoadventure.mixin

import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import org.spongepowered.asm.mixin.Debug
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Unique
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import sh.ftp.schipao.schipaoadventure.FallDamageHandler
import sh.ftp.schipao.schipaoadventure.PlayerData

@Debug(export = true)
@Mixin(PlayerEntity::class)
abstract class PlayerEntityMixin : PlayerData {

    @Unique
    override var playerClass: Int = -1
        set(value) {
            if (field == value) return
            field = value

            val player = this as PlayerEntity
            if (value == 1) { // Aes
                player.getAttributeInstance(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE)
                    ?.baseValue = Double.MAX_VALUE
            } else {
                player.getAttributeInstance(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE)
                    ?.baseValue = 3.0
            }

            sync()
        }

    @Inject(
        method = ["handleFallDamage"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun handleFallDamage(
        fallDistance: Float,
        damagePerDistance: Float,
        damageSource: DamageSource,
        cir: CallbackInfoReturnable<Boolean>
    ) {
        val player = this as PlayerEntity

        if (!FallDamageHandler.handleFallDamage(player, fallDistance)) {
            cir.returnValue = false
        }
    }

    @Inject(method = ["writeCustomDataToNbt"], at = [At("TAIL")])
    private fun writeCustomDataToNbt(
        nbt: NbtCompound,
        ci: CallbackInfo
    ) {
        (this as PlayerData).serialize(nbt)
        println("SAVING CLASS: $playerClass")
    }

    @Inject(method = ["readCustomDataFromNbt"], at = [At("TAIL")])
    private fun readCustomDataFromNbt(
        nbt: NbtCompound,
        ci: CallbackInfo
    ) {
        (this as PlayerData).deserialize(nbt)
        println("Loaded class: $playerClass")
    }
}