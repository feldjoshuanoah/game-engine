package com.feldjoshuanoah.gameengine.opengl;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * A vertex array object is an OpenGL object that stores all of the states needed to supply vertex
 * data. It stores the format of the vertex data as well as the buffer objects providing the vertex
 * data arrays. Note that a VAO merely references the buffers, it does not copy or freeze their
 * contents; if referenced buffers are modified later, those changes will be seen when using the
 * VAO.
 */
public class VertexArrayObject implements OpenGLObject {

    /**
     * The vertex array object name.
     */
    private int name;

    /**
     * The number of generic vertex attribute arrays.
     */
    private int vertexAttribs;

    /**
     * Generates a vertex array object name. It is guaranteed that the generated name was not in use
     * immediately before the call to {@code generate()}. The generated name is marked as used, for
     * the purposes of {@code generate()} only, but they acquire state and type only when they are
     * first bound.
     */
    @Override
    public void generate() {
        name = GL30.glGenVertexArrays();
    }

    /**
     * Binds this vertex array object. If the bind is successful no change is made to the state of
     * the vertex array object, and any previous vertex array object binding is broken.
     */
    @Override
    public void bind() {
        GL30.glBindVertexArray(name);
    }

    /**
     * Breaks the existing vertex array object binding.
     */
    @Override
    public void unbind() {
        GL30.glBindVertexArray(0);
    }

    /**
     * Deletes this vertex array object. Once a vertex array object is deleted it has no contents
     * and its name is again unused. If a vertex array object that is currently bound is deleted,
     * the binding for that object reverts to zero and the default vertex array becomes current.
     */
    @Override
    public void delete() {
        GL30.glDeleteVertexArrays(name);
    }

    /**
     * Sets the layout for all generic vertex attribute arrays.
     *
     * @param layout The layout for all generic vertex attribute arrays.
     */
    public void setVertexAttribLayout(final GLSLDataType[] layout) {
        vertexAttribs = layout.length;
        IntStream.range(0, vertexAttribs).forEach(i -> GL20.glVertexAttribPointer(i, layout[i]
                .getSize(), layout[i].getType(), false, Arrays.stream(layout)
                .mapToInt(GLSLDataType::getByteSize).sum(), IntStream.range(0, i)
                .map(j -> layout[j].getByteSize()).sum()));
    }

    /**
     * Enables all generic vertex attribute arrays. It uses the currently bound vertex array object
     * for the operation. By default, all client-side capabilities are disabled, including all
     * generic vertex attribute arrays. If enabled, the values in the generic vertex attribute
     * arrays will be accessed and used for rendering when calls are made to vertex array commands.
     */
    public void enableVertexAttribArrays() {
        IntStream.range(0, vertexAttribs).forEach(GL20::glEnableVertexAttribArray);
    }

    /**
     * Disables all generic vertex attribute arrays. It uses the currently bound vertex array object
     * for the operation. By default, all client-side capabilities are disabled, including all
     * generic vertex attribute arrays. If enabled, the values in the generic vertex attribute
     * arrays will be accessed and used for rendering when calls are made to vertex array commands.
     */
    public void disableVertexAttribArrays() {
        IntStream.range(0, vertexAttribs).forEach(GL20::glEnableVertexAttribArray);
    }
}
