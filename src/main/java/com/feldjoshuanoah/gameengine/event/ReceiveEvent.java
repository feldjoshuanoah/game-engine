package com.feldjoshuanoah.gameengine.event;

import com.feldjoshuanoah.gameengine.entity.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as being an event receiver.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReceiveEvent {

    /**
     * Returns the components that the target entity of the event must have.
     *
     * @return The components that the target entity of the event must have.
     */
    Class<? extends Component>[] components() default {};
}
