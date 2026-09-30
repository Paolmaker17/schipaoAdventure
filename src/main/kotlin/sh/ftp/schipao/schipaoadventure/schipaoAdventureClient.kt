package sh.ftp.schipao.schipaoadventure

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.text.Text
import sh.ftp.schipao.schipaoadventure.network.AesDoubleJumpPayload
import sh.ftp.schipao.schipaoadventure.network.AesParticlesPayload
import sh.ftp.schipao.schipaoadventure.particle.AesParticle
import sh.ftp.schipao.schipaoadventure.particle.ModParticles

class schipaoAdventureClient :ClientModInitializer {
    companion object {
        fun handlePlayerDataPayload(payload: PlayerDataPayload, ctx: ClientPlayNetworking.Context) {
            val player = ctx.client().player!!
            player.sendMessage(Text.literal("got ${payload.data}"))
            (player as PlayerData).deserialize(payload.data)
        }
    }

    private var opened = false

    var wasJumpPressed = false
    var wasOnGround = false

    override fun onInitializeClient() {

        ClientPlayNetworking.registerGlobalReceiver(
            PlayerDataPayload.ID,
            schipaoAdventureClient::handlePlayerDataPayload
        )

        ParticleFactoryRegistry.getInstance().register(ModParticles.AES_PARTICLE, AesParticle::Factory)
        ParticleFactoryRegistry.getInstance().register(ModParticles.AES_PARTICLE_2, AesParticle::Factory)
        ParticleFactoryRegistry.getInstance().register(ModParticles.AES_PARTICLE_3,AesParticle::Factory)

        ClientPlayNetworking.registerGlobalReceiver(
            AesParticlesPayload.ID
        ) { _, context ->
            context.client().execute {

                val player = context.client().player
                    ?: return@execute

                repeat(15) {

                    val offsetX = (Math.random() - 0.5) * 0.4
                    val offsetY = Math.random() * 0.8
                    val offsetZ = (Math.random() - 0.5) * 0.4

                    player.clientWorld.addParticle(
                        ModParticles.AES_PARTICLE,
                        player.x + offsetX,
                        player.y + offsetY,
                        player.z + offsetZ,
                        0.0,
                        0.02,
                        0.0
                    )

                    player.clientWorld.addParticle(
                        ModParticles.AES_PARTICLE_2,
                        player.x + offsetX,
                        player.y + offsetY,
                        player.z + offsetZ,
                        0.0,
                        0.02,
                        0.0
                    )

                    player.clientWorld.addParticle(
                        ModParticles.AES_PARTICLE_3,
                        player.x + offsetX,
                        player.y + offsetY,
                        player.z + offsetZ,
                        0.0,
                        0.02,
                        0.0
                    )
                }
            } }

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val player = client.player ?: return@register
            val data = player as PlayerData

            // Class selection
            if (!opened && data.playerClass == -1) {

                client.setScreen(
                    CustomClassChoiceScreen(
                        Text.literal("Choose Class")
                    ) {
                        opened = false
                    }
                )
                opened = true
            }

            // Aes
            val jumpPressed = client.options.jumpKey.isPressed
            val justPressed = jumpPressed && !wasJumpPressed

            if (
                justPressed &&
                !wasOnGround &&
                data.playerClass == 1
            ) {
                ClientPlayNetworking.send(
                    AesDoubleJumpPayload()
                )
            }

            wasJumpPressed = jumpPressed
            wasOnGround = player.isOnGround
        }
    }
}