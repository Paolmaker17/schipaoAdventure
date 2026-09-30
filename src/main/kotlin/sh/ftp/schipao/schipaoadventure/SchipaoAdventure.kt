package sh.ftp.schipao.schipaoadventure

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.LivingEntity
import net.minecraft.util.ActionResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sh.ftp.schipao.schipaoadventure.block.ModBlocks
import sh.ftp.schipao.schipaoadventure.item.ModItemGroups
import sh.ftp.schipao.schipaoadventure.item.ModItems
import sh.ftp.schipao.schipaoadventure.network.AesDoubleJumpPayload
import sh.ftp.schipao.schipaoadventure.network.AesParticlesPayload
import sh.ftp.schipao.schipaoadventure.playerclasses.Aes
import sh.ftp.schipao.schipaoadventure.playerclasses.Aqua
import sh.ftp.schipao.schipaoadventure.playerclasses.Ignis
import sh.ftp.schipao.schipaoadventure.playerclasses.Poaceae

object SchipaoAdventure : ModInitializer {
	const val MOD_ID :String = "schipaoadventure"
    val LOGGER :Logger = LoggerFactory.getLogger(MOD_ID)

	fun handlePlayerDataPayload(payload: PlayerDataPayload, ctx: ServerPlayNetworking.Context) =
		(ctx.player() as PlayerData).deserialize(payload.data)

	override fun onInitialize() {
		// Player data networking
		PayloadTypeRegistry.playS2C().register(PlayerDataPayload.ID, PlayerDataPayload.CODEC)
		PayloadTypeRegistry.playC2S().register(PlayerDataPayload.ID, PlayerDataPayload.CODEC)

		PayloadTypeRegistry.playC2S().register(AesDoubleJumpPayload.ID, AesDoubleJumpPayload.CODEC)
		PayloadTypeRegistry.playS2C().register(AesParticlesPayload.ID, AesParticlesPayload.CODEC)

		ServerPlayNetworking.registerGlobalReceiver(PlayerDataPayload.ID, this::handlePlayerDataPayload)

		// Sync player data when joining
		ServerPlayConnectionEvents.JOIN.register { handler, _, _ ->
			val player = handler.player
			val data = player as PlayerData

			data.sync()
		}

		//Ignis fire touch
		AttackEntityCallback.EVENT.register { player, world, hand, entity, result ->
			if(!world.isClient && entity is LivingEntity){
				val data = player as PlayerData
				if ( data.playerClass == 3)
					Ignis.onAttack(player, entity)
			}

			ActionResult.PASS
		}

		// Aes double jump
		ServerPlayNetworking.registerGlobalReceiver(AesDoubleJumpPayload.ID) { _, context ->
			val player = context.player()
			context.server().execute {

				val data = player as PlayerData

				if (data.playerClass != 1)
					return@execute

				if (player.isOnGround)
					return@execute

				if (!Aes.doubleJump(player))
					return@execute

				ServerPlayNetworking.send(
					player,
					AesParticlesPayload()
				)
			}
		}

		ServerTickEvents.END_SERVER_TICK.register { server ->
			server.playerManager.playerList.forEach { player ->
				val data = player as PlayerData
				when (data.playerClass) {
					0 -> Poaceae.update(player)
					1 -> Aes.update(player)
					2 -> Aqua.update(player)
					3 -> Ignis.update(player)
				}
			}
		}

		ModItemGroups.registerItemGroups()
		ModItems.registerModItems()
		ModBlocks.registerModBlocks()

		ComandCustom.register()
	}
}