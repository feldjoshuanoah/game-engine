package com.feldjoshuanoah.gameengine.opengl.shader;

import org.lwjgl.opengl.GL20;

/**
 * A fragment shader object is intended to run on the programmable fragment processor.
 */
public class FragmentShaderObject extends ShaderObject {

    /**
     * Creates a new {@code FragmentShaderObject} instance.
     */
    public FragmentShaderObject() {
        super(GL20.GL_FRAGMENT_SHADER);
    }
}
