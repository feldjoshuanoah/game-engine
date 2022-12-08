package com.feldjoshuanoah.gameengine.event.input;

import com.feldjoshuanoah.gameengine.event.Event;

/**
 * A scroll event. It is fired when a scrolling device is used, such as a mouse wheel or scrolling
 * area of a touchpad.
 */
public class ScrollEvent extends Event {

    /**
     * The scroll offset along the x-axis.
     */
    private final double xOffset;

    /**
     * The scroll offset along the y-axis.
     */
    private final double yOffset;

    /**
     * Creates a new {@code AbstractEvent} instance.
     *
     * @param window  The window that received the event.
     * @param xOffset The scroll offset along the x-axis.
     * @param yOffset The scroll offset along the y-axis.
     */
    public ScrollEvent(final long window, final double xOffset, final double yOffset) {
        super(window);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Returns the scroll offset along the x-axis.
     *
     * @return The scroll offset along the x-axis.
     */
    public double getXOffset() {
        return xOffset;
    }

    /**
     * Returns the scroll offset along the y-axis.
     *
     * @return The scroll offset along the y-axis.
     */
    public double getYOffset() {
        return yOffset;
    }
}
