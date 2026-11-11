package sh.ftp.schipao.schipaoadventure.mixin.client

import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import sh.ftp.schipao.schipaoadventure.client.render.InvisRenderRules

@Mixin(
    value = [
        ArmorFeatureRenderer::class,
        HeldItemFeatureRenderer::class,
        ElytraFeatureRenderer::class
    ]
)
abstract class InvisibleEquipMixin {

    @Inject(
        method = [
            "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V"
        ],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun hideEquipment(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        entity: LivingEntity,
        limbAngle: Float,
        limbDistance: Float,
        tickDelta: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float,
        ci: CallbackInfo
    ) {

        if (InvisRenderRules.shouldHideEquipment(entity)) {
            ci.cancel()
        }
    }
}