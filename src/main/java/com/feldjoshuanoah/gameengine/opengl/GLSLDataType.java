package com.feldjoshuanoah.gameengine.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 * The OpenGL Shading Language defines a number of data types.
 */
public enum GLSLDataType {

    /**
     * An IEEE-754 single-precision floating-point number.
     */
    FLOAT(1, GL11.GL_FLOAT),
    /**
     * A signed, two's complement, 32-bit integer.
     */
    INT(1, GL11.GL_INT),
    /**
     * An unsigned 32-bit integer.
     */
    UNSIGNED_INT(1, GL11.GL_UNSIGNED_INT),
    /**
     * A conditional type, values may be either {@code true} or {@code false}.
     */
    BOOL(1, GL20.GL_BOOL),
    /**
     * A 2-component vector of single-precision floating-point numbers.
     */
    VEC2(2, GL11.GL_FLOAT),
    /**
     * A 2-component vector of signed integers.
     */
    IVEC2(2, GL11.GL_INT),
    /**
     * A 2-component vector of unsigned integers.
     */
    UVEC2(2, GL11.GL_UNSIGNED_INT),
    /**
     * A 2-component vector of booleans.
     */
    BVEC2(2, GL20.GL_BOOL),
    /**
     * A 3-component vector of single-precision floating-point numbers.
     */
    VEC3(3, GL11.GL_FLOAT),
    /**
     * A 3-component vector of signed integers.
     */
    IVEC3(3, GL11.GL_INT),
    /**
     * A 2-component vector of unsigned integers.
     */
    UVEC3(3, GL11.GL_UNSIGNED_INT),
    /**
     * A 3-component vector of booleans.
     */
    BVEC3(3, GL20.GL_BOOL),
    /**
     * A 4-component vector of single-precision floating-point numbers.
     */
    VEC4(4, GL11.GL_FLOAT),
    /**
     * A 4-component vector of signed integers.
     */
    IVEC4(4, GL11.GL_INT),
    /**
     * A 2-component vector of unsigned integers.
     */
    UVEC4(4, GL11.GL_UNSIGNED_INT),
    /**
     * A 4-component vector of booleans.
     */
    BVEC4(4, GL20.GL_BOOL),
    /**
     * A square matrix with 2 columns and 2 rows.
     */
    MAT2(4, GL11.GL_FLOAT),
    /**
     * A square matrix with 3 columns and 3 rows.
     */
    MAT3(9, GL11.GL_FLOAT),
    /**
     * A square matrix with 4 columns and 4 rows.
     */
    MAT4(16, GL11.GL_FLOAT),
    /**
     * A matrix with 2 columns and 3 rows.
     */
    MAT2X3(6, GL11.GL_FLOAT),
    /**
     * A matrix with 3 columns and 2 rows.
     */
    MAT3X2(6, GL11.GL_FLOAT),
    /**
     * A matrix with 2 columns and 4 rows.
     */
    MAT2X4(8, GL11.GL_FLOAT),
    /**
     * A matrix with 4 columns and 2 rows.
     */
    MAT4X2(8, GL11.GL_FLOAT),
    /**
     * A matrix with 3 columns and 4 rows.
     */
    MAT3X4(12, GL11.GL_FLOAT),
    /**
     * A matrix with 4 columns and 3 rows.
     */
    MAT4X3(12, GL11.GL_FLOAT),;

    /**
     * The number of components in the data type.
     */
    private final int size;

    /**
     * The GLSL data type.
     */
    private final int type;

    /**
     * Creates a new {@code GLSLDataType} instance with the given size and type.
     *
     * @param size The number of components in the data type.
     * @param type The GLSL data type.
     */
    GLSLDataType(final int size, final int type) {
        this.size = size;
        this.type = type;
    }

    /**
     * Returns the number of components in the data type.
     *
     * @return The number of components in the data type.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the GLSL data type.
     *
     * @return The GLSL data type.
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the size of the data type in bytes.
     *
     * @return The size in bytes.
     */
    public int getByteSize() {
        return (type == GL11.GL_FLOAT ? Float.BYTES : Integer.BYTES) * size;
    }
}
