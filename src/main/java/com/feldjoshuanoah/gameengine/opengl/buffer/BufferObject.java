package com.feldjoshuanoah.gameengine.opengl.buffer;

import com.feldjoshuanoah.gameengine.opengl.OpenGLObject;
import org.lwjgl.opengl.GL15;

/**
 * A buffer object is an OpenGL object that stores an array of unformatted memory allocated by the
 * OpenGL context. These can be used to store a wide variety of data sets.
 */
public class BufferObject implements OpenGLObject {

    /**
     * The target to which the buffer object is bound.
     */
    private final int target;

    /**
     * The buffer object name.
     */
    private int name;

    /**
     * Creates a new {@code Buffer} instance. A buffer object name is generated and then bound to
     * the given target.
     *
     * @param target The desired target to which the buffer object is bound.
     */
    public BufferObject(final int target) {
        this.target = target;
    }

    /**
     * Generates a buffer object name. It is guaranteed that the generated name was not in use
     * immediately before the call to {@code generate()}. No buffer objects are associated with the
     * generated buffer object name until they are first bound by calling {@link #bind()}.
     */
    @Override
    public void generate() {
        name = GL15.glGenBuffers();
    }

    /**
     * Binds this buffer object's name to the target. When a buffer object is bound to a target, the
     * previous binding for that target is automatically broken. A buffer object binding created
     * with {@code bind()} remains active until a different buffer object name is bound to the same
     * target, or until the bound buffer object is deleted with {@link #delete()}.
     */
    @Override
    public void bind() {
        GL15.glBindBuffer(target, name);
    }

    /**
     * Unbinds the buffer object name previously bound to the target, and restores the client memory
     * usage for that buffer object target.
     */
    @Override
    public void unbind() {
        GL15.glBindBuffer(target, 0);
    }

    /**
     * Deletes this named buffer object. After a buffer object is deleted, it has no contents, and
     * its name is free for reuse (for example by {@link #generate()}). If a buffer object that is
     * currently bound is deleted, the binding reverts to {@code 0} (the absence of any buffer
     * object, which reverts to client memory usage).
     */
    @Override
    public void delete() {
        GL15.glDeleteBuffers(name);
    }

    /**
     * Creates a new data store for this buffer object. Any pre-existing data store is deleted. The
     * new data store is created with the specified size in bytes and usage.
     *
     * @param size  The desired size in bytes of the buffer object's new data store.
     * @param usage A hint to the GL implementation as to how a buffer object's data store will be
     *              accessed. This enables the GL implementation to make more intelligent decisions
     *              that may significantly impact buffer object performance. It does not, however,
     *              constrain the actual usage of the data store. It can be broken down into two
     *              parts: first, the frequency of access (modification and usage), and second, the
     *              nature of that access. The nature of access must be {@code DRAW}, which means
     *              that the data store contents are modified by the application, and used as the
     *              source for GL drawing and image specification commands. Thus the three valid
     *              values are {@link GL15#GL_STREAM_DRAW} (the data store contents will be modified
     *              once and used at most a few times), {@link GL15#GL_STATIC_DRAW} (the data store
     *              contents will be modified once and used many times), and
     *              {@link GL15#GL_DYNAMIC_DRAW} (the data store contents will be modified
     *              repeatedly and used many times.
     */
    public void setData(final long size, final int usage) {
        GL15.glBufferData(target, size, usage);
    }

    /**
     * Creates a new data store for this buffer object. Any pre-existing data store is deleted. The
     * new data store is created and initialized with the given data and usage.
     *
     * @param data  The data to copy into the data store for initialization.
     * @param usage A hint to the GL implementation as to how a buffer object's data store will be
     *              accessed. This enables the GL implementation to make more intelligent decisions
     *              that may significantly impact buffer object performance. It does not, however,
     *              constrain the actual usage of the data store. It can be broken down into two
     *              parts: first, the frequency of access (modification and usage), and second, the
     *              nature of that access. The nature of access must be {@code DRAW}, which means
     *              that the data store contents are modified by the application, and used as the
     *              source for GL drawing and image specification commands. Thus the three valid
     *              values are {@link GL15#GL_STREAM_DRAW} (the data store contents will be modified
     *              once and used at most a few times), {@link GL15#GL_STATIC_DRAW} (the data store
     *              contents will be modified once and used many times), and
     *              {@link GL15#GL_DYNAMIC_DRAW} (the data store contents will be modified
     *              repeatedly and used many times.
     */
    public void setData(final int[] data, final int usage) {
        GL15.glBufferData(target, data, usage);
    }

    /**
     * Creates a new data store for this buffer object. Any pre-existing data store is deleted. The
     * new data store is created and initialized with the given data and usage.
     *
     * @param data  The data to copy into the data store for initialization.
     * @param usage A hint to the GL implementation as to how a buffer object's data store will be
     *              accessed. This enables the GL implementation to make more intelligent decisions
     *              that may significantly impact buffer object performance. It does not, however,
     *              constrain the actual usage of the data store. It can be broken down into two
     *              parts: first, the frequency of access (modification and usage), and second, the
     *              nature of that access. The nature of access must be {@code DRAW}, which means
     *              that the data store contents are modified by the application, and used as the
     *              source for GL drawing and image specification commands. Thus the three valid
     *              values are {@link GL15#GL_STREAM_DRAW} (the data store contents will be modified
     *              once and used at most a few times), {@link GL15#GL_STATIC_DRAW} (the data store
     *              contents will be modified once and used many times), and
     *              {@link GL15#GL_DYNAMIC_DRAW} (the data store contents will be modified
     *              repeatedly and used many times.
     */
    public void setData(final float[] data, final int usage) {
        GL15.glBufferData(target, data, usage);
    }
}
