package sh.ftp.schipao.schipaoadventure

import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.toast.SystemToast
import net.minecraft.text.Text
import net.minecraft.client.MinecraftClient

class CustomClassChoiceScreen(title: Text) : Screen(title) {

    override fun init() {
        val centerX = width / 2
        val centerY = height / 2

        val button = ButtonWidget.builder(
            Text.literal("Hello World")
        ) {
            showToast()
        }.dimensions(centerX - 60, centerY - 10, 120, 20)
            .build()

        addDrawableChild(button)
    }

    private fun showToast() {
        val client = MinecraftClient.getInstance()

        client.toastManager.add(
            SystemToast.create(
                client,
                SystemToast.Type.NARRATOR_TOGGLE,
                Text.literal("Hello World!"),
                Text.literal("This is a toast.")
            )
        )

        MinecraftClient.getInstance().setScreen(null)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)

        context.drawText(
            textRenderer,
            Text.literal("Special Button"),
            width / 2 - 60,
            height / 2 - 40,
            0xFFFFFF,
            true
        )
    }
}