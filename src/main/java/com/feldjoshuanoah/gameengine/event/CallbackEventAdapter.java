package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.event.input.*;
import org.lwjgl.glfw.GLFW;

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
     * @param key      The keyboard key that was pressed, held, or released.
     * @param scanCode The system-specific scancode of the key.
     * @param action   {@link GLFW#GLFW_PRESS}, {@link GLFW#GLFW_RELEASE}, or
     *                 {@link GLFW#GLFW_REPEAT}.
     * @param mods     A bit field describing which modifier keys were held down.
     */
    public static void keyCallback(final long window, final int key, final int scanCode,
            final int action, final int mods) {
        EVENT_MANAGER.fire(new KeyEvent(window, key, scanCode, action, mods));
    }

    /**
     * Will be called when a button is pressed or released.
     *
     * @param window The window that received the event.
     * @param button The mouse button that was pressed or released.
     * @param action One of {@link GLFW#GLFW_PRESS} or {@link GLFW#GLFW_RELEASE}.
     * @param mods   A bit field describing which modifier keys were held down.
     */
    public static void mouseButtonCallback(final long window, final int button, final int action,
            final int mods) {
        EVENT_MANAGER.fire(new MouseButtonEvent(window, button, action, mods));
    }

    /**
     * Will be called when the cursor is moved.
     *
     * @param window The window that received the event.
     * @param x      The new cursor x-coordinate, relative to the left edge of the content area.
     * @param y      The new cursor y-coordinate, relative to the top edge of the content area.
     */
    public static void cursorPositionCallback(final long window, final double x, final double y) {
        EVENT_MANAGER.fire(new CursorPositionEvent(window, x, y));
    }

    /**
     * Will be called when the cursor enters or leaves the content area of the window.
     *
     * @param window  The window that received the event.
     * @param entered {@code true} if the cursor entered the window's content area, or {@code false}
     *                if it left it.
     */
    public static void cursorEnterCallback(final long window, final boolean entered) {
        EVENT_MANAGER.fire(new CursorEnterEvent(window, entered));
    }

    /**
     * Will be called when a scrolling device is used, such as a mouse wheel or scrolling area of a
     * touchpad.
     *
     * @param window  The window that received the event.
     * @param xOffset The scroll offset along the x-axis.
     * @param yOffset The scroll offset along the y-axis.
     */
    public static void scrollCallback(final long window, final double xOffset,
            final double yOffset) {
        EVENT_MANAGER.fire(new ScrollEvent(window, xOffset, yOffset));
    }
}
