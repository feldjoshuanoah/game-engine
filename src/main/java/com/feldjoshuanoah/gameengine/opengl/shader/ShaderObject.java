package com.feldjoshuanoah.gameengine.opengl.shader;

import com.feldjoshuanoah.gameengine.opengl.GLSLObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 * A shader object is used to maintain the source code strings that define a shader.
 */
public class ShaderObject implements GLSLObject {

    /**
     * The type of the shader object.
     */
    private final int type;

    /**
     * The shader object name.
     */
    private int name;

    /**
     * Creates a new {@code ShaderObject} instance with the given type.
     *
     * @param type The type of the shader to create.
     */
    public ShaderObject(final int type) {
        this.type = type;
    }

    /**
     * Creates an empty shader object and sets the name to a non-zero value by which it can be
     * referenced.
     */
    @Override
    public void create() {
        name = GL20.glCreateShader(type);
    }

    /**
     * Frees the memory and invalidates the name associated with this shader object. This
     * effectively undoes the effects of a call to {@link #create()}. If a shader object to be
     * deleted is attached to a program object, it will be flagged for deletion, but it will not be
     * deleted until it is no longer attached to any program object, for any rendering context (i.e,
     * it must be detached from wherever it was attached before it will be deleted). To determine
     * whether an object has been flagged for deletion, call {@link #getDeleteStatus()}.
     */
    @Override
    public void delete() {
        GL20.glDeleteShader(name);
    }

    /**
     * Compiles the source code strings that have been stored in this shader object for
     * implementations that support a shader compiler. Compilation of a shader can fail for a number
     * of reasons as specified by the OpenGL ES Shading Language Specification.
     */
    public void compile() {
        GL20.glCompileShader(name);
    }

    /**
     * Returns the source code from this shader object. The source code string for a shader object
     * is the result of a previous call to {@link #setSource(String)}.
     *
     * @return The source code.
     */
    public String getSource() {
        return GL20.glGetShaderSource(name);
    }

    /**
     * Sets the source code in this shader object. Any source code previously stored in the shader
     * object is completely replaced. The source code string is not scanned or parsed at this time;
     * it is simply copied into the shader object.
     *
     * @param source The source code to load into the shader.
     */
    public void setSource(final String source) {
        GL20.glShaderSource(name, source);
    }

    /**
     * Returns the information log for this shader object. The information log for a shader object
     * is modified when the shader is compiled. The information log for a shader object is a string
     * that may contain diagnostic messages, warning messages, and other information about the last
     * compile operation. When a shader object is created, its information log will be a string of
     * length {@code 0}.
     *
     * @return The information log.
     */
    public String getInfoLog() {
        return GL20.glGetShaderInfoLog(name);
    }

    /**
     * Returns {@code true} if this shader object is currently flagged for deletion, and
     * {@code false} otherwise.
     *
     * @return {@code true} if the shader object is currently flagged for deletion.
     */
    public boolean getDeleteStatus() {
        return GL20.glGetShaderi(name, GL20.GL_DELETE_STATUS) == GL11.GL_TRUE;
    }

    /**
     * Returns {@code true} if the last compile operation on this shader object was successful, and
     * {@code false} otherwise.
     *
     * @return {@code true} if the last compile operation on this shader object was successful.
     */
    public boolean getCompileStatus() {
        return GL20.glGetShaderi(name, GL20.GL_COMPILE_STATUS) == GL11.GL_TRUE;
    }

    /**
     * Returns the number of characters in the information log for this shader object including the
     * null termination character (i.e., the size of the character buffer required to store the
     * information log). If the shader object has no information log, a value of {@code 0} is
     * returned.
     *
     * @return The number of characters in the information log.
     */
    public int getInfoLogLength() {
        return GL20.glGetShaderi(name, GL20.GL_INFO_LOG_LENGTH);
    }

    /**
     * Returns the length of the shader source for this shader object, including the null
     * termination character (i.e., the size of the character buffer required to store the shader
     * source). If no source code exists, {@code 0} is returned.
     *
     * @return The length of the shader source.
     */
    public int getSourceLength() {
        return GL20.glGetShaderi(name, GL20.GL_SHADER_SOURCE_LENGTH);
    }

    /**
     * Returns {@link GL20#GL_VERTEX_SHADER} if this shader object is a vertex shader object and
     * {@link GL20#GL_FRAGMENT_SHADER} if it is a fragment shader object.
     *
     * @return {@link GL20#GL_VERTEX_SHADER} if this shader object is a vertex shader object and
     *         {@link GL20#GL_FRAGMENT_SHADER} if it is a fragment shader object.
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the shader object name.
     *
     * @return The shader object name.
     */
    public int getName() {
        return name;
    }
}
