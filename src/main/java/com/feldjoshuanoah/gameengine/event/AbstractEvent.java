package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.Window;

/**
 * An event.
 */
public abstract class AbstractEvent {

    /**
     * The window that received the event.
     */
    private final Window window;

    /**
     * Creates a new {@code Event} instance.
     *
     * @param window The window that received the event.
     */
    public AbstractEvent(final long window) {
        this.window = Window.getWindow(window);
    }

    /**
     * Returns the window that received this event.
     *
     * @return The window.
     */
    public Window getWindow() {
        return window;
    }
}
