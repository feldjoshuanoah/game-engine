package com.feldjoshuanoah.gameengine.event;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A manager that handles the events and listeners.
 */
public class EventManager {

    /**
     * The logger for the event manager.
     */
    private static final Logger LOGGER = Logger.getLogger(EventManager.class.getName());

    /**
     * The registered listeners.
     */
    private final List<RegisteredEventListener> listeners;

    /**
     * Creates a new {@code EventManager} instance.
     */
    private EventManager() {
        listeners = new ArrayList<>();
    }

    /**
     * Fires an event.
     *
     * @param event The event to fire.
     */
    public void fire(final Event event) {
        listeners.forEach(listener -> listener.getHandlers().stream().filter(
                handler -> handler.getParameterTypes()[0].isAssignableFrom(event.getClass()))
                .forEach(handler -> {
            try {
                handler.invoke(listener.getListener(), event);
            } catch (final IllegalAccessException | InvocationTargetException exception) {
                if (LOGGER.isLoggable(Level.SEVERE)) {
                    LOGGER.log(Level.SEVERE, "Failed to fire an event.", exception);
                }
            }
        }));
    }

    /**
     * Registers a listener with its handlers.
     *
     * @param listener The listener to register.
     */
    public void register(final EventListener listener) {
        listeners.add(new RegisteredEventListener(listener));
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
