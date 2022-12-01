package com.feldjoshuanoah.gameengine.entity;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An entity.
 */
public final class Entity {

    /**
     * The components.
     */
    private final List<AbstractComponent> components;

    /**
     * Creates a new {@code Entity} instance.
     */
    public Entity() {
        components = new ArrayList<>();
    }

    /**
     * Returns a component by its class.
     *
     * @param  clazz The class of the desired component.
     * @return       The component.
     * @param  <T>   The component type.
     */
    public <T extends AbstractComponent> Optional<T> getComponent(final Class<T> clazz) {
        return components.stream().filter(component -> clazz.isAssignableFrom(component.getClass()))
                .findFirst().map(clazz::cast);
    }

    /**
     * Returns {@code true} if the entity has a component with the given class, {@code false}
     * otherwise.
     *
     * @param  clazz The class of the component to find.
     * @return       {@code true} if the entity has a component with the given class.
     * @param  <T>   The component type.
     */
    public <T extends AbstractComponent> boolean hasComponent(final Class<T> clazz) {
        return components.stream().anyMatch(component -> clazz.isAssignableFrom(component
                .getClass()));
    }

    /**
     * Adds a component.
     *
     * @param component The component to add.
     */
    public void addComponent(final AbstractComponent component) {
        Preconditions.checkArgument(component != null);
        if (components.stream().noneMatch(c -> c.getClass() == component.getClass())) {
            components.add(component);
        }
    }

    /**
     * Removes a component by its class.
     *
     * @param clazz The class of the component to remove.
     * @param <T>   The component type.
     */
    public <T extends AbstractComponent> void removeComponent(final Class<T> clazz) {
        components.stream().filter(component -> clazz.isAssignableFrom(component.getClass()))
                .findFirst().ifPresent(components::remove);
    }

    /**
     * Returns the components.
     *
     * @return The components.
     */
    public List<AbstractComponent> getComponents() {
        return List.copyOf(components);
    }
}
