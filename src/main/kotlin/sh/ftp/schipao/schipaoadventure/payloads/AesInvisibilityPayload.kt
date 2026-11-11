package sh.ftp.schipao.schipaoadventure.payloads

import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

data class AesInvisibilityPayload(
    val unused: Int = 0
) : CustomPayload {

    override fun getId() = ID

    companion object {
        val ID = CustomPayload.Id<AesInvisibilityPayload>(
            Identifier.of("schipaoadventure", "aes_invisibility")
        )

        val CODEC: PacketCodec<RegistryByteBuf, AesInvisibilityPayload> =
            PacketCodec.unit(AesInvisibilityPayload())
    }
}
