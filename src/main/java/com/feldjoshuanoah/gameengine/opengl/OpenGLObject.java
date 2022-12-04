package com.feldjoshuanoah.gameengine.opengl;

/**
 * An OpenGL object is an OpenGL construct that contains some state. When they are bound to the
 * context, the state that they contain is mapped into the context's name. Thus, changes to context
 * state will be stored in this object, and functions that act on this context state will use the
 * state stored in the object.
 */
public interface OpenGLObject {

    /**
     * Generates the object.
     */
    void generate();

    /**
     * Binds the object.
     */
    void bind();

    /**
     * Unbinds the object.
     */
    void unbind();

    /**
     * Deletes the object.
     */
    void delete();
}
