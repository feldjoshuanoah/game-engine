package com.feldjoshuanoah.gameengine.opengl.shader;

import org.lwjgl.opengl.GL20;

/**
 * A vertex shader object is intended to run on the programmable vertex processor.
 */
public class VertexShaderObject extends ShaderObject {

    /**
     * Creates a new {@code VertexShaderObject} instance.
     */
    public VertexShaderObject() {
        super(GL20.GL_VERTEX_SHADER);
    }
}
