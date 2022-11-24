package com.feldjoshuanoah.gameengine.event.keyboard;

import org.lwjgl.glfw.GLFW;

/**
 * A key event. It is fired when a key is pressed.
 */
public final class KeyPressEvent extends KeyEvent {

    /**
     * Creates a new {@code KeyPressEvent} instance.
     *
     * @param key      The keyboard key that was pressed, held, or released.
     * @param scanCode The system-specific scancode of the key.
     * @param mods     Bit field describing which modifier keys were held down.
     */
    public KeyPressEvent(final int key, final int scanCode, final int mods) {
        super(key, scanCode, GLFW.GLFW_PRESS, mods);
    }
}
