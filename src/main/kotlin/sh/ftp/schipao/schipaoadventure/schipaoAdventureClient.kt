package sh.ftp.schipao.schipaoadventure

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.text.Text
import sh.ftp.schipao.schipaoadventure.playerclasses.Aes

class schipaoAdventureClient :ClientModInitializer {
    companion object {
        fun handlePlayerDataPayload(payload: PlayerDataPayload, ctx: ClientPlayNetworking.Context) {
            val player = ctx.client().player!!
            player.sendMessage(Text.literal("got ${payload.data}"))
            (player as PlayerData).deserialize(payload.data)
        }
    }

    private var opened = false

    private var wasOnGround = true
    private var wasJumpPressed = false

    override fun onInitializeClient() {

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

            //Aes
            Aes.update(player)
            val jumpPressed = client.options.jumpKey.isPressed
            val justPressed = jumpPressed && !wasJumpPressed

            if (
                justPressed &&
                !wasOnGround &&
                data.playerClass == 1
            ) {
                Aes.doubleJump(player)
            }

            wasJumpPressed = jumpPressed
            wasOnGround = player.isOnGround
        }

        ClientPlayNetworking.registerGlobalReceiver(PlayerDataPayload.ID, schipaoAdventureClient::handlePlayerDataPayload)
    }
}