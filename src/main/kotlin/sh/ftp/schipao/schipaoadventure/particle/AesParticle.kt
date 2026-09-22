package sh.ftp.schipao.schipaoadventure.particle

import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType


class AesParticle(clientWorld: ClientWorld,
                  x: Double,
                  y: Double,
                  z: Double,
                  spriteProvider: SpriteProvider,
                  vx: Double,
                  vy: Double,
                  vz: Double)
    : SpriteBillboardParticle(clientWorld, x, y, z, vx, vy, vz)  {

    init {
        this.velocityMultiplier = 0.8f
        this.maxAge = 40
        this.setSpriteForAge(spriteProvider)
        this.red = 1f
        this.green = 1f
        this.blue = 1f
    }

    override fun getType(): ParticleTextureSheet? {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT
    }

    class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<SimpleParticleType?> {
        override fun createParticle(
            parameters: SimpleParticleType?, world: ClientWorld, x: Double, y: Double, z: Double,
            velocityX: Double, velocityY: Double, velocityZ: Double
        ): Particle {
            return AesParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ)
        }
    }
}