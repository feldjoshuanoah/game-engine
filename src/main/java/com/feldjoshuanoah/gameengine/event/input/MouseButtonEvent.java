package com.feldjoshuanoah.gameengine.event.input;

import com.feldjoshuanoah.gameengine.event.Event;
import org.lwjgl.glfw.GLFW;

/**
 * A button event. It is fired when a button is pressed or released.
 */
public final class MouseButtonEvent extends Event {

    /**
     * The mouse button that was pressed or released.
     */
    private final int button;

    /**
     * The button action.
     */
    private final int action;

    /**
     * A bit field describing which modifier keys were held down.
     */
    private final int mods;

    /**
     * Creates a new {@code ButtonEvent} instance.
     *
     * @param window The window that received the event.
     * @param button The mouse button that was pressed or released.
     * @param action One of {@link GLFW#GLFW_PRESS} or {@link GLFW#GLFW_RELEASE}.
     * @param mods   A bit field describing which modifier keys were held down.
     */
    public MouseButtonEvent(final long window, final int button, final int action, final int mods) {
        super(window);
        this.button = button;
        this.action = action;
        this.mods = mods;
    }

    /**
     * Returns the mouse button that was pressed or released.
     *
     * @return The mouse button that was pressed or released.
     */
    public int getButton() {
        return button;
    }

    /**
     * Returns the button action.
     *
     * @return The button action.
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
