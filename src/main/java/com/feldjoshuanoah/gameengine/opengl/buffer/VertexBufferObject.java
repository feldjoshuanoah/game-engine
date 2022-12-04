package com.feldjoshuanoah.gameengine.opengl.buffer;

import com.feldjoshuanoah.gameengine.opengl.buffer.BufferObject;
import org.lwjgl.opengl.GL15;

/**
 * A vertex buffer object is the common term for a normal buffer object when it is used as a source
 * for vertex array data.
 */
public final class VertexBufferObject extends BufferObject {

    /**
     * Creates a new {@code VertexBufferObject} instance.
     */
    public VertexBufferObject() {
        super(GL15.GL_ARRAY_BUFFER);
    }

    @Override
    public void setData(final int[] data, final int usage) {
        throw new UnsupportedOperationException();
    }
}
