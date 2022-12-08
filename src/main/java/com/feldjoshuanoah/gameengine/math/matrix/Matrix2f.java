package com.feldjoshuanoah.gameengine.math.matrix;

/**
 * A column-major 2x2 matrix of single-precision floating-point numbers.
 */
public class Matrix2f {

    /**
     * The component in the first column and first row.
     */
    private float m00;

    /**
     * The component in the first column and second row.
     */
    private float m01;

    /**
     * The component in the second column and first row.
     */
    private float m10;

    /**
     * The component in the second column and second row.
     */
    private float m11;

    /**
     * Creates a new {@code Matrix2f} instance and sets it to identity.
     */
    public Matrix2f() {
        this(1.0f, 0.0f, 0.0f, 1.0f);
    }

    /**
     * Creates a new {@code Matrix2f} instance and makes it a copy of the given matrix.
     *
     * @param matrix The matrix to copy the values from.
     */
    public Matrix2f(final Matrix2f matrix) {
        this(matrix.m00, matrix.m01, matrix.m10, matrix.m11);
    }

    /**
     * Creates a new {@code Matrix2f} instance using the supplied float values. The order of the
     * parameters is column-major, so the first two parameters specify the two elements of the first
     * column.
     *
     * @param m00 The desired value of the component in the first column and first row.
     * @param m01 The desired value of the component in the first column and second row.
     * @param m10 The desired value of the component in the second column and first row.
     * @param m11 The desired value of the component in the second column and second row.
     */
    public Matrix2f(final float m00, final float m01, final float m10, final float m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }

    /**
     * Adds the given scalar value to each component of this matrix.
     *
     * @param scalar The scalar value to add.
     */
    public void add(final float scalar) {
        set(m00 + scalar, m01 + scalar, m10 + scalar, m11 + scalar);
    }

    /**
     * Adds the given matrix to this matrix.
     *
     * @param matrix The matrix to add.
     */
    public void add(final Matrix2f matrix) {
        set(m00 + matrix.m00, m01 + matrix.m01, m10 + matrix.m10, m11 + matrix.m11);
    }

    /**
     * Subtracts the given scalar value from each component of this matrix.
     *
     * @param scalar The scalar value to subtract.
     */
    public void subtract(final float scalar) {
        set(m00 - scalar, m01 - scalar, m10 - scalar, m11 - scalar);
    }

    /**
     * Subtracts the given matrix from this matrix.
     *
     * @param matrix The matrix to subtract.
     */
    public void subtract(final Matrix2f matrix) {
        set(m00 - matrix.m00, m01 - matrix.m01, m10 - matrix.m10, m11 - matrix.m11);
    }

    /**
     * Multiplies each component of this matrix with the given scalar value.
     *
     * @param scalar The scalar value to multiply with.
     */
    public void multiply(final float scalar) {
        set(m00 * scalar, m01 * scalar, m10 * scalar, m11 * scalar);
    }

    /**
     * Multiplies this matrix with the given matrix.
     *
     * @param matrix The matrix to multiply with.
     */
    public void multiply(final Matrix2f matrix) {
        set(m00 * matrix.m00 + m10 * matrix.m01, m01 * matrix.m00 + m11 * matrix.m01,
                m00 * matrix.m10 + m10 * matrix.m11, m01 * matrix.m10 + m11 * matrix.m11);
    }

    /**
     * Divides each component of this matrix by the given scalar value.
     *
     * @param scalar The scalar value to divide by.
     */
    public void divide(final float scalar) {
        set(m00 / scalar, m01 / scalar, m10 / scalar, m11 / scalar);
    }

    /**
     * Returns the determinant of this matrix. The determinant is a scalar value that is a function
     * of the entries of a square matrix. It characterizes some properties of the matrix and the
     * linear map represented by the matrix. In particular, the determinant is nonzero if and only
     * if the matrix is invertible and the linear map represented by the matrix is an isomorphism.
     * The determinant of a product of matrices is the product of their determinants (the preceding
     * property is a corollary of this one).
     *
     * @return The determinant of this matrix.
     */
    public float determinant() {
        return m00 * m11 - m01 * m10;
    }

    /**
     * Inverts this matrix.
     */
    public void invert() {
        set(m11, -m01, -m10, m00);
        divide(determinant());
    }

    /**
     * Transposes this matrix.
     */
    public void transpose() {
        set(m00, m10, m01, m11);
    }

    /**
     * Sets all components of this matrix to zero.
     */
    public void zero() {
        set(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Negates this matrix.
     */
    public void negate() {
        set(-m00, -m01, -m10, -m11);
    }

    /**
     * Sets this matrix to identity.
     */
    public void identity() {
        set(1.0f, 0.0f, 0.0f, 1.0f);
    }

    /**
     * Scales this matrix in the x- and y-direction by the given scales.
     *
     * @param x The desired scale in x-direction.
     * @param y The desired scale in y-direction.
     */
    public void scale(final float x, final float y) {
        set(m00 * x, m01 * x, m10 * y, m11 * y);
    }

    /**
     * Sets this matrix to a uniform scaling matrix, which scales all axes by the given factor.
     *
     * @param scale The desired scale in x- and y-direction.
     */
    public void scaling(final float scale) {
        set(scale, 0, 0, scale);
    }

    /**
     * Sets this matrix to a scaling matrix, which scales the x- and y-axis by the given factors.
     *
     * @param x The desired scale in x-direction.
     * @param y The desired scale in y-direction.
     */
    public void scaling(final float x, final float y) {
        set(x, 0, 0, y);
    }

    /**
     * Rotates this matrix counter-clockwise by the given angle (in radians).
     *
     * @param angle The desired angle (in radians) to rotate by.
     */
    public void rotate(final float angle) {
        final float sin = (float) Math.sin(angle);
        final float cos = (float) Math.cos(angle);
        set(m00 * cos - m10 * sin, m01 * cos - m11 * sin, m10 * cos + m00 * sin,
                m11 * cos + m01 * sin);
    }

    /**
     * Sets this matrix to a rotation matrix, which rotates by the given angle (in radians).
     *
     * @param angle The desired angle (in radians) to rotate by.
     */
    public void rotation(final float angle) {
        final float sin = (float) Math.sin(angle);
        final float cos = (float) Math.cos(angle);
        set(cos, sin, -sin, cos);
    }

    /**
     * Returns the value of the component in the given column and row of this matrix.
     *
     * @param  column The column of the desired component.
     * @param  row    The row of the desired component.
     * @return        The value of the component.
     */
    public float get(final int column, final int row) {
        float value = 0;
        switch (column) {
            case 0 -> {
                switch (row) {
                    case 0 -> value = m00;
                    case 1 -> value = m01;
                }
            }
            case 1 -> {
                switch (row) {
                    case 0 -> value = m10;
                    case 1 -> value = m11;
                }
            }
        }
        return value;
    }

    /**
     * Sets the value of the component in the given row and column of this matrix.
     *
     * @param column The column of the component to modify.
     * @param row    The row of the component to modify.
     * @param value  The desired value of the component.
     */
    public void set(final int column, final int row, final float value) {
        switch (column) {
            case 0 -> {
                switch (row) {
                    case 0 -> m00 = value;
                    case 1 -> m01 = value;
                }
            }
            case 1 -> {
                switch (row) {
                    case 0 -> m10 = value;
                    case 1 -> m11 = value;
                }
            }
        }
    }

    /**
     * Sets the values of all components of this matrix. The order of the parameters is
     * column-major, so the first two parameters specify the two elements of the first column.
     *
     * @param m00 The desired value of the component in the first column and first row.
     * @param m01 The desired value of the component in the first column and second row.
     * @param m10 The desired value of the component in the second column and first row.
     * @param m11 The desired value of the component in the second column and second row.
     */
    public void set(final float m00, final float m01, final float m10, final float m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }

    /**
     * Returns an array containing all components in this matrix in column-major order. The returned
     * array will be "safe" in that no references to it are maintained by this matrix.
     *
     * @return An array containing all components in this matrix in column-major order.
     */
    public float[] toArray() {
        return new float[] { m00, m01, m10, m11 };
    }
}
