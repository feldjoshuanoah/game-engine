package com.feldjoshuanoah.gameengine.entity;

import java.util.Optional;

/**
 * A non-existent entity. This can be used instead of straight {@code null} to avoid
 * {@code NullPointerException}s (following the null object pattern). It gobbles up and ignores all
 * attempts to modify its components.
 */
public final class NullEntity extends Entity {

    /**
     * Not accessible.
     */
    private NullEntity() {
    }

    @Override
    public <T extends AbstractComponent> Optional<T> getComponent(final Class<T> clazz) {
        return Optional.empty();
    }

    @Override
    public <T extends AbstractComponent> boolean hasComponent(final Class<T> clazz) {
        return false;
    }

    @Override
    public void addComponent(final AbstractComponent component) {
    }

    @Override
    public <T extends AbstractComponent> void removeComponent(final Class<T> clazz) {
    }

    /**
     * Returns the instance.
     *
     * @return The instance.
     */
    public static NullEntity getInstance() {
        return SingletonHelper.INSTANCE;
    }

    /**
     * A helper class to make the {@code NullEntity} a singleton.
     */
    private static final class SingletonHelper {

        /**
         * The {@code NullEntity} instance.
         */
        private static final NullEntity INSTANCE = new NullEntity();

        /**
         * Not accessible.
         */
        private SingletonHelper() {
        }
    }
}
