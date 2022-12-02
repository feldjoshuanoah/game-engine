package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.entity.Entity;
import com.feldjoshuanoah.gameengine.entity.NullEntity;
import com.feldjoshuanoah.gameengine.util.TopologicalSorter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
     * A lookup table for the registered handlers.
     */
    private final Map<Class<? extends EventHandler>, RegisteredEventHandler> handlersLookup;

    /**
     * Creates a new {@code EventManager} instance.
     */
    private EventManager() {
        handlers = Lists.newArrayList();
        handlersLookup = Maps.newHashMap();
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
        final RegisteredEventHandler registered = new RegisteredEventHandler(handler);
        handlers.add(registered);
        handlersLookup.put(handler.getClass(), registered);
        sortHandlers();
    }

    private void sortHandlers() {
        final TopologicalSorter<RegisteredEventHandler> sorter = new TopologicalSorter<>();
        sorter.addVertices(handlers);
        handlers.forEach(handler -> {
            handler.getBefore().forEach(before -> sorter.addEdge(handler, handlersLookup.get(before)));
            handler.getAfter().forEach(after -> sorter.addEdge(handlersLookup.get(after), handler));
        });
        handlers.clear();
        handlers.addAll(sorter.sort());
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
