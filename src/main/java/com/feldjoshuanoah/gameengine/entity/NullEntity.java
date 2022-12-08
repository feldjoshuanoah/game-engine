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
        super();
    }

    @Override
    public <T extends Component> Optional<T> getComponent(final Class<T> clazz) {
        return Optional.empty();
    }

    @Override
    public <T extends Component> boolean hasComponent(final Class<T> clazz) {
        return false;
    }

    @Override
    public void addComponent(final Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Component> void removeComponent(final Class<T> clazz) {
        throw new UnsupportedOperationException();
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
