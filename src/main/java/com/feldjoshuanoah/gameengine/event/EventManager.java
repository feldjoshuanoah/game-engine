package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.entity.Entity;
import com.feldjoshuanoah.gameengine.entity.NullEntity;
import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A manager that handles the events and supplies then to their respective receivers.
 */
public final class EventManager {

    /**
     * The logger for the event manager.
     */
    private static final Logger LOGGER = Logger.getLogger(EventManager.class.getName());

    /**
     * The registered handlers.
     */
    private final List<RegisteredEventHandler> handlers;

    /**
     * Creates a new {@code EventManager} instance.
     */
    private EventManager() {
        handlers = Lists.newArrayList();
    }

    /**
     * Fires an event.
     *
     * @param event Th event to fire.
     */
    public void fire(final AbstractEvent event) {
        fire(event, NullEntity.getInstance());
    }

    /**
     * Fires an event with the given target entity.
     *
     * @param event The event to fire.
     * @param entity The target entity.
     */
    public void fire(final AbstractEvent event, final Entity entity) {
        handlers.forEach(handler -> handler.getReceivers().stream().filter(
                receiver -> receiver.getParameterTypes()[0].isAssignableFrom(event.getClass())
                        && Arrays.stream((receiver.getAnnotation(ReceiveEvent.class)).components())
                        .allMatch(entity::hasComponent)).forEach(receiver -> {
            try {
                receiver.invoke(handler.getHandler(), event, entity);
            } catch (final IllegalAccessException | InvocationTargetException exception) {
                if (LOGGER.isLoggable(Level.SEVERE)) {
                    LOGGER.log(Level.SEVERE, "Failed to fire an event.", exception);
                }
            }
        }));
    }

    /**
     * Registers a handler with its receivers.
     *
     * @param handler The handler to register.
     */
    public void register(final EventHandler handler) {
        handlers.add(new RegisteredEventHandler(handler));
    }

    /**
     * Returns the instance.
     *
     * @return The instance.
     */
    public static EventManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    /**
     * A helper class to make the {@code EventManager} a singleton.
     */
    private static final class SingletonHelper {

        /**
         * The {@code EventManager} instance.
         */
        private static final EventManager INSTANCE = new EventManager();

        /**
         * Not accessible.
         */
        private SingletonHelper() {
        }
    }
}
