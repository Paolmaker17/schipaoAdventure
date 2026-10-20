package sh.ftp.schipao.schipaoadventure.playerclasses

abstract class PlayerClass(
    val name: String, val classId: PlayerClassInt) {
}

enum class PlayerClassInt(id: Int) {
    POACEAE(0),
    AES(1),
    AQUA(2),
    IGNIS(3)
}