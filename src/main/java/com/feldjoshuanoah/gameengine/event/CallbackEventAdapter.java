package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.event.input.KeyEvent;

/**
 * An adapter to attach the event handling system to GLFW.
 */
public final class CallbackEventAdapter {

    /**
     * The {@link EventManager} instance.
     */
    private static final EventManager EVENT_MANAGER = EventManager.getInstance();

    /**
     * Not accessible.
     */
    private CallbackEventAdapter() {
    }

    /**
     * Will be called when a key is pressed, repeated or released.
     *
     * @param window   The window that received the event.
     * @param key      The keyboard key that was pressed or released.
     * @param scanCode The system-specific scancode of the key.
     * @param action   The key action.
     * @param mods     Bit field describing which modifier keys were held down.
     */
    public static void keyCallback(final long window, final int key, final int scanCode,
            final int action, final int mods) {
        EVENT_MANAGER.fire(new KeyEvent(window, key, scanCode, action, mods));
    }
}
