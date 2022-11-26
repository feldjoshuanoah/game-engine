package com.feldjoshuanoah.gameengine.event.input;

import com.feldjoshuanoah.gameengine.event.AbstractEvent;
import org.lwjgl.glfw.GLFW;

/**
 * A cursor enter event. It is fired when the cursor enters or leaves the content area of the
 * window.
 */
public class CursorEnterEvent extends AbstractEvent {

    /**
     * {@code true} if the cursor entered the window's content area, or {@code false} if it left it.
     */
    private final boolean entered;

    /**
     * Creates a new {@code CursorEnterEvent} instance.
     *
     * @param window  The window that received the event.
     * @param entered {@code true} if the cursor entered the window's content area, or {@code false}
     *                if it left it.
     */
    public CursorEnterEvent(final long window, final boolean entered) {
        super(window);
        this.entered = entered;
    }

    /**
     * Returns {@code true} if the cursor entered the window's content area, or {@code false} if it
     * left it.
     *
     * @return {@code true} if the cursor entered the window's content area, or {@code false} if it
     *         left it.
     */
    public boolean isEntered() {
        return entered;
    }
}
