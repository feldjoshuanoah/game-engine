package com.feldjoshuanoah.gameengine.event;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * A registered event listener.
 */
public class RegisteredEventListener {

    /**
     * The listener.
     */
    private final EventListener listener;

    /**
     * The handlers of the listener.
     */
    private final List<Method> handlers;

    /**
     * Creates a new {@code RegisteredEventListener} instance.
     *
     * @param listener The listener.
     */
    public RegisteredEventListener(final EventListener listener) {
        this.listener = listener;
        this.handlers = Arrays.stream(listener.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(EventHandler.class)).toList();
    }

    /**
     * Returns the listener.
     *
     * @return The listener.
     */
    public EventListener getListener() {
        return listener;
    }

    /**
     * Returns the handlers of the listener.
     *
     * @return The handlers of the listener.
     */
    public List<Method> getHandlers() {
        return handlers;
    }
}
