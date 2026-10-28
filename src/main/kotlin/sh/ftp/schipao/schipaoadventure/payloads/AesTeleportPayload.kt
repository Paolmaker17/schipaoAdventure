package sh.ftp.schipao.schipaoadventure.network

import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

data class AesTeleportPayload(val targetEntityId: Int) : CustomPayload {
    companion object {
        val ID = CustomPayload.Id<AesTeleportPayload>(
            Identifier.of("schipaoadventure", "aes_teleport")
        )

        val CODEC: PacketCodec<RegistryByteBuf, AesTeleportPayload> =
            PacketCodec.tuple(
                PacketCodecs.VAR_INT,
                AesTeleportPayload::targetEntityId,
                ::AesTeleportPayload
            )
    }

    override fun getId(): CustomPayload.Id<AesTeleportPayload> = ID
}