package sh.ftp.schipao.schipaoadventure.network

import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

data class AesParticlesPayload(
    val unused: Int = 0
) : CustomPayload {

    companion object {
        val ID = CustomPayload.Id<AesParticlesPayload>(
            Identifier.of("schipaoadventure", "aes_particles")
        )

        val CODEC: PacketCodec<PacketByteBuf, AesParticlesPayload> =
            PacketCodec.unit(AesParticlesPayload())
    }

    override fun getId(): CustomPayload.Id<AesParticlesPayload> = ID
}