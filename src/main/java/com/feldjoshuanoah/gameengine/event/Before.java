package com.feldjoshuanoah.gameengine.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an event handler as to be processed before one or more other event handlers.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Before {

    /**
     * Returns the event handlers that are executed after the marked event handler was processed.
     *
     * @return The event handlers that are executed after the marked event handler was processed.
     */
    Class<? extends EventHandler>[] value();
}
