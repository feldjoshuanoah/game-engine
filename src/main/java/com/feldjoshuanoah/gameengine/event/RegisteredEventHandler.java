package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.entity.Entity;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * A registered event handler.
 */
public class RegisteredEventHandler {

    /**
     * The handler.
     */
    private final EventHandler handler;

    /**
     * The receivers of the handler.
     */
    private final List<Method> receivers;

    /**
     * Creates a new {@code RegisteredEventListener} instance.
     *
     * @param handler The handler.
     */
    public RegisteredEventHandler(final EventHandler handler) {
        this.handler = handler;
        this.receivers = Arrays.stream(handler.getClass().getMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers())
                        && method.isAnnotationPresent(ReceiveEvent.class)
                        && method.getReturnType().equals(EventResult.class)
                        && AbstractEvent.class.isAssignableFrom(method.getParameterTypes()[0])
                        && Entity.class.isAssignableFrom(method.getParameterTypes()[1])).toList();
    }

    /**
     * Returns the handler.
     *
     * @return The handler.
     */
    public EventHandler getHandler() {
        return handler;
    }

    /**
     * Returns the receivers of the handler.
     *
     * @return The receivers of the handler.
     */
    public List<Method> getReceivers() {
        return receivers;
    }
}
