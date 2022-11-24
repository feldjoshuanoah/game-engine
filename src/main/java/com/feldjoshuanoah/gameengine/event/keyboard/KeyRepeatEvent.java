package com.feldjoshuanoah.gameengine.event.keyboard;

import org.lwjgl.glfw.GLFW;

/**
 * A key event. It is fired when a key is repeated.
 */
public final class KeyRepeatEvent extends KeyEvent {

    /**
     * Creates a new {@code KeyRepeatEvent} instance.
     *
     * @param key      The keyboard key that was pressed, held, or released.
     * @param scanCode The system-specific scancode of the key.
     * @param mods     Bit field describing which modifier keys were held down.
     */
    public KeyRepeatEvent(final int key, final int scanCode, final int mods) {
        super(key, scanCode, GLFW.GLFW_REPEAT, mods);
    }
}
