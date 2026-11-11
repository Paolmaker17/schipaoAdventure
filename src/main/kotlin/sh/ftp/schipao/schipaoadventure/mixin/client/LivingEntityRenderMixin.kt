package sh.ftp.schipao.schipaoadventure.mixin.client

import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.ModifyArgs
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import org.spongepowered.asm.mixin.injection.invoke.arg.Args
import sh.ftp.schipao.schipaoadventure.client.render.InvisRenderContext

@Mixin(LivingEntityRenderer::class)
abstract class LivingEntityRendererMixin {

    @Inject(
        method = [
            "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
        ],
        at = [At("HEAD")]
    )
    private fun startRender(
        entity: LivingEntity,
        yaw: Float,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        ci: CallbackInfo
    ) {
        InvisRenderContext.current.set(entity)
    }

    @ModifyArgs(
        method = [
            "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
        ],
        at = At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
        )
    )
    private fun modifyTransparency(args: Args) {

        val target = InvisRenderContext.current.get() as? PlayerEntity
            ?: return

        val viewer = MinecraftClient.getInstance().player
            ?: return

        val team = target.scoreboardTeam ?: return

        // Deve essere un giocatore invisibile
        if (!target.hasStatusEffect(StatusEffects.INVISIBILITY)) return

        // Il giocatore che osserva deve appartenere
        // alla stessa squadra AES
        if (viewer.scoreboardTeam != team) return

        if (!team.shouldShowFriendlyInvisibles()) return

        // Manteniamo RGB e impostiamo alpha al 50%
        val originalColor = args.get<Int>(4)

        args.set(
            4,
            (originalColor and 0x00FFFFFF) or (128 shl 24)
        )
    }

    @Inject(
        method = [
            "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
        ],
        at = [At("RETURN")]
    )
    private fun endRender(ci: CallbackInfo) {
        InvisRenderContext.current.remove()
    }
}

