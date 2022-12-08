package com.feldjoshuanoah.gameengine.event.input;

import com.feldjoshuanoah.gameengine.event.Event;

/**
 * A cursor position event. It is fired when the cursor is moved.
 */
public final class CursorPositionEvent extends Event {

    /**
     * The new cursor x-coordinate, relative to the left edge of the content area.
     */
    private final double x;

    /**
     * The new cursor y-coordinate, relative to the top edge of the content area.
     */
    private final double y;

    /**
     * Creates a new {@code CursorPositionEvent} instance.
     *
     * @param window The window that received the event.
     * @param x      The new cursor x-coordinate, relative to the left edge of the content area.
     * @param y      The new cursor y-coordinate, relative to the top edge of the content area.
     */
    public CursorPositionEvent(final long window, final double x, final double y) {
        super(window);
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the new cursor x-coordinate, relative to the left edge of the content area.
     *
     * @return The new cursor x-coordinate, relative to the left edge of the content area.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the new cursor y-coordinate, relative to the top edge of the content area.
     *
     * @return The new cursor y-coordinate, relative to the top edge of the content area.
     */
    public double getY() {
        return y;
    }
}
