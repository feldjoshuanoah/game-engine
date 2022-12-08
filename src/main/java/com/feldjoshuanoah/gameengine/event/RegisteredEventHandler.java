package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.entity.Entity;
import com.google.common.collect.Lists;

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
     * The event handlers to process afterwards.
     */
    private final List<Class<? extends EventHandler>> before;

    /**
     * The event handlers to process before.
     */
    private final List<Class<? extends EventHandler>> after;

    /**
     * Creates a new {@code RegisteredEventListener} instance.
     *
     * @param handler The handler.
     */
    public RegisteredEventHandler(final EventHandler handler) {
        this.handler = handler;
        receivers = Arrays.stream(handler.getClass().getMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers())
                        && method.isAnnotationPresent(ReceiveEvent.class)
                        && method.getReturnType().equals(EventResult.class)
                        && Event.class.isAssignableFrom(method.getParameterTypes()[0])
                        && Entity.class.isAssignableFrom(method.getParameterTypes()[1])).toList();
        before = handler.getClass().isAnnotationPresent(Before.class) ? Arrays.stream(handler
                .getClass().getAnnotation(Before.class).value()).toList() : Lists.newArrayList();
        after = handler.getClass().isAnnotationPresent(After.class) ? Arrays.stream(handler
                .getClass().getAnnotation(After.class).value()).toList() : Lists.newArrayList();
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

    public List<Class<? extends EventHandler>> getBefore() {
        return before;
    }

    public List<Class<? extends EventHandler>> getAfter() {
        return after;
    }
}
