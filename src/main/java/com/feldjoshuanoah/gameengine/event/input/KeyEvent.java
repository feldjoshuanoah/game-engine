package com.feldjoshuanoah.gameengine.event.input;

import com.feldjoshuanoah.gameengine.event.Event;
import org.lwjgl.glfw.GLFW;

/**
 * A key event. It is fired when a key is pressed, held, or released.
 */
public final class KeyEvent extends Event {

    /**
     * The keyboard key that was pressed, held, or released.
     */
    private final int key;

    /**
     * The system-specific scancode of the key.
     */
    private final int scanCode;

    /**
     * The key action.
     */
    private final int action;

    /**
     * A bit field describing which modifier keys were held down.
     */
    private final int mods;

    /**
     * Creates a new {@code KeyEvent} instance.
     *
     * @param window   The window that received the event.
     * @param key      The keyboard key that was pressed, held, or released.
     * @param scanCode The system-specific scancode of the key.
     * @param action   {@link GLFW#GLFW_PRESS}, {@link GLFW#GLFW_RELEASE}, or
     *                 {@link GLFW#GLFW_REPEAT}.
     * @param mods     A bit field describing which modifier keys were held down.
     */
    public KeyEvent(final long window, final int key, final int scanCode, final int action,
            final int mods) {
        super(window);
        this.key = key;
        this.scanCode = scanCode;
        this.action = action;
        this.mods = mods;
    }

    /**
     * Returns the keyboard key that was pressed, held, or released.
     *
     * @return The keyboard key that was pressed, held, or released.
     */
    public int getKey() {
        return key;
    }

    /**
     * Returns the system-specific scancode of the key.
     *
     * @return The system-specific scancode of the key.
     */
    public int getScanCode() {
        return scanCode;
    }

    /**
     * Returns the key action.
     *
     * @return The key action.
     */
    public int getAction() {
        return action;
    }

    /**
     * Returns a bit field describing which modifier keys were held down.
     *
     * @return A bit field describing which modifier keys were held down.
     */
    public int getMods() {
        return mods;
    }
}
