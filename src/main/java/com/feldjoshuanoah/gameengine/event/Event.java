package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.glfw.Window;

/**
 * An event.
 */
public class Event {

    /**
     * The window that received the event.
     */
    private final Window window;

    /**
     * Creates a new {@code AbstractEvent} instance.
     *
     * @param window The window that received the event.
     */
    public Event(final long window) {
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
