package sh.ftp.schipao.schipaoadventure.network

import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

data class AesDoubleJumpPayload(
    val unused: Int = 0
) : CustomPayload {

    companion object {
        val ID = CustomPayload.Id<AesDoubleJumpPayload>(
            Identifier.of("schipaoadventure", "aes_double_jump")
        )

        val CODEC: PacketCodec<PacketByteBuf, AesDoubleJumpPayload> =
            PacketCodec.unit(AesDoubleJumpPayload())
    }

    override fun getId(): CustomPayload.Id<AesDoubleJumpPayload> = ID
}