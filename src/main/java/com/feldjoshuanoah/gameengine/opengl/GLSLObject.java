package com.feldjoshuanoah.gameengine.opengl;

/**
 * A GLSL object is an object in the OpenGL API that encapsulates the compiled or linked shaders
 * that execute portions of the OpenGL Pipeline. These objects represent code written in the OpenGL
 * Shading Language (GLSL). Though they are called objects, most of them do not fit within the
 * OpenGL object paradigm.
 */
public interface GLSLObject {

    /**
     * Creates an empty GLSL object.
     */
    void create();

    /**
     * Frees the memory and invalidates the name associated with this object. This effectively
     * undoes the effects of a call to {@link #create()}.
     */
    void delete();
}
