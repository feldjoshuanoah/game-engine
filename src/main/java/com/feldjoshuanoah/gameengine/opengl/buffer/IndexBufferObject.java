package com.feldjoshuanoah.gameengine.opengl.buffer;

import org.lwjgl.opengl.GL15;

/**
 * An index buffer object. Indexed rendering requires an array of indices; all vertex attributes
 * will use the same index from this index array. The index array is provided by a buffer object
 * bound to the {@link GL15#GL_ELEMENT_ARRAY_BUFFER} binding point. When a buffer is bound to
 * {@link GL15#GL_ELEMENT_ARRAY_BUFFER}, all drawing commands will use indices from that buffer. The
 * index buffer binding is stored within the VAO. If no VAO is bound, then you cannot bind a buffer
 * object to {@link GL15#GL_ELEMENT_ARRAY_BUFFER}.
 */
public final class IndexBufferObject extends BufferObject {

    /**
     * Creates a new {@code IndexBufferObject} instance.
     */
    public IndexBufferObject() {
        super(GL15.GL_ELEMENT_ARRAY_BUFFER);
    }

    @Override
    public void setData(final float[] data, final int usage) {
        throw new UnsupportedOperationException();
    }
}
