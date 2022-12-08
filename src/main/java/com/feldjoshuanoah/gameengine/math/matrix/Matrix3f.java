package com.feldjoshuanoah.gameengine.math.matrix;

/**
 * A column-major 3x3 matrix of single-precision floating-point numbers.
 */
public class Matrix3f {

    /**
     * The component in the first column and first row.
     */
    private float m00;

    /**
     * The component in the first column and second row.
     */
    private float m01;

    /**
     * The component in the first column and third row.
     */
    private float m02;

    /**
     * The component in the second column and first row.
     */
    private float m10;

    /**
     * The component in the second column and second row.
     */
    private float m11;

    /**
     * The component in the second column and third row.
     */
    private float m12;

    /**
     * The component in the third column and first row.
     */
    private float m20;

    /**
     * The component in the third column and second row.
     */
    private float m21;

    /**
     * The component in the third column and third row.
     */
    private float m22;

    /**
     * Creates a new {@code Matrix3f} instance and sets it to identity.
     */
    public Matrix3f() {
        this(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }

    /**
     * Creates a new {@code Matrix3f} instance and makes it a copy of the given matrix.
     *
     * @param matrix The matrix to copy the values from.
     */
    public Matrix3f(final Matrix3f matrix) {
        this(matrix.m00, matrix.m01, matrix.m02,
                matrix.m10, matrix.m11, matrix.m12,
                matrix.m20, matrix.m21, matrix.m22);
    }

    /**
     * Creates a new {@code Matrix3f} instance using the supplied float values. The order of the
     * parameters is column-major, so the first three parameters specify the three elements of the
     * first column.
     *
     * @param m00 The desired value of the component in the first column and first row.
     * @param m01 The desired value of the component in the first column and second row.
     * @param m02 The desired value of the component in the first column and third row.
     * @param m10 The desired value of the component in the second column and first row.
     * @param m11 The desired value of the component in the second column and second row.
     * @param m12 The desired value of the component in the second column and third row.
     * @param m20 The desired value of the component in the third column and first row.
     * @param m21 The desired value of the component in the third column and second row.
     * @param m22 The desired value of the component in the third column and third row.
     */
    public Matrix3f(final float m00, final float m01, final float m02,
            final float m10, final float m11, final float m12,
            final float m20, final float m21, final float m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }

    /**
     * Adds the given scalar value to each component of this matrix.
     *
     * @param scalar The scalar value to add.
     */
    public void add(final float scalar) {
        set(m00 + scalar, m01 + scalar, m02 + scalar,
                m10 + scalar, m11 + scalar, m12 + scalar,
                m20 + scalar, m21 + scalar, m22 + scalar);
    }

    /**
     * Adds the given matrix to this matrix.
     *
     * @param matrix The matrix to add.
     */
    public void add(final Matrix3f matrix) {
        set(m00 + matrix.m00, m01 + matrix.m01, m02 + matrix.m02,
                m10 + matrix.m10, m11 + matrix.m11, m12 + matrix.m12,
                m20 + matrix.m20, m21 + matrix.m21, m22 + matrix.m22);
    }

    /**
     * Subtracts the given scalar value from each component of this matrix.
     *
     * @param scalar The scalar value to subtract.
     */
    public void subtract(final float scalar) {
        set(m00 - scalar, m01 - scalar, m02 - scalar,
                m10 - scalar, m11 - scalar, m12 - scalar,
                m20 - scalar, m21 - scalar, m22 - scalar);
    }

    /**
     * Subtracts the given matrix from this matrix.
     *
     * @param matrix The matrix to subtract.
     */
    public void subtract(final Matrix3f matrix) {
        set(m00 - matrix.m00, m01 - matrix.m01, m02 - matrix.m02,
                m10 - matrix.m10, m11 - matrix.m11, m12 - matrix.m12,
                m20 - matrix.m20, m21 - matrix.m21, m22 - matrix.m22);
    }

    /**
     * Multiplies each component of this matrix with the given scalar value.
     *
     * @param scalar The scalar value to multiply with.
     */
    public void multiply(final float scalar) {
        set(m00 * scalar, m01 * scalar, m02 * scalar,
                m10 * scalar, m11 * scalar, m12 * scalar,
                m20 * scalar, m21 * scalar, m22 * scalar);
    }

    /**
     * Multiplies this matrix with the given matrix.
     *
     * @param matrix The matrix to multiply with.
     */
    public void multiply(final Matrix3f matrix) {
        set(m00 * matrix.m00 + m10 * matrix.m01 + m20 * matrix.m02,
                m01 * matrix.m00 + m11 * matrix.m01 + m21 * matrix.m02,
                m02 * matrix.m00 + m12 * matrix.m01 + m22 * matrix.m02,
                m00 * matrix.m10 + m10 * matrix.m11 + m20 * matrix.m12,
                m01 * matrix.m10 + m11 * matrix.m11 + m21 * matrix.m12,
                m02 * matrix.m10 + m12 * matrix.m11 + m22 * matrix.m12,
                m00 * matrix.m20 + m10 * matrix.m21 + m20 * matrix.m22,
                m01 * matrix.m20 + m11 * matrix.m21 + m21 * matrix.m22,
                m02 * matrix.m20 + m12 * matrix.m21 + m22 * matrix.m22);
    }

    /**
     * Divides each component of this matrix by the given scalar value.
     *
     * @param scalar The scalar value to divide by.
     */
    public void divide(final float scalar) {
        set(m00 / scalar, m01 / scalar, m02 / scalar,
                m10 / scalar, m11 / scalar, m12 / scalar,
                m20 / scalar, m21 / scalar, m22 / scalar);
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
        return m00 * m11 * m22 + m10 * m21 * m02 + m20 * m01 * m12
                - m02 * m11 * m20 - m21 * m12 * m00 - m22 * m01 * m10;
    }

    /**
     * Inverts this matrix.
     */
    public void invert() {
        set(m11 * m22 - m21 * m12, m20 * m12 - m10 * m22, m10 * m21 - m20 * m11,
                m21 * m02 - m01 * m22, m00 * m22 - m20 * m02, m20 * m01 - m00 * m21,
                m01 * m12 - m11 * m02, m10 * m02 - m00 * m12, m00 * m11 - m10 * m01);
        divide(determinant());
    }

    /**
     * Transposes this matrix.
     */
    public void transpose() {
        set(m00, m10, m20, m01, m11, m21, m02, m12, m22);
    }

    /**
     * Sets all components of this matrix to zero.
     */
    public void zero() {
        set(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Negates this matrix.
     */
    public void negate() {
        set(-m00, -m01, -m02, -m10, -m11, -m12, -m20, -m21, -m22);
    }

    /**
     * Sets this matrix to identity.
     */
    public void identity() {
        set(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
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
                    case 2 -> value = m02;
                }
            }
            case 1 -> {
                switch (row) {
                    case 0 -> value = m10;
                    case 1 -> value = m11;
                    case 2 -> value = m12;
                }
            }
            case 2 -> {
                switch (row) {
                    case 0 -> value = m20;
                    case 1 -> value = m21;
                    case 2 -> value = m22;
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
                    case 2 -> m02 = value;
                }
            }
            case 1 -> {
                switch (row) {
                    case 0 -> m10 = value;
                    case 1 -> m11 = value;
                    case 2 -> m12 = value;
                }
            }
            case 2 -> {
                switch (row) {
                    case 0 -> m20 = value;
                    case 1 -> m21 = value;
                    case 2 -> m22 = value;
                }
            }
        }
    }

    /**
     * Sets the values of all components of this matrix. The order of the parameters is
     * column-major, so the first three parameters specify the three elements of the first column.
     *
     * @param m00 The desired value of the component in the first column and first row.
     * @param m01 The desired value of the component in the first column and second row.
     * @param m02 The desired value of the component in the first column and third row.
     * @param m10 The desired value of the component in the second column and first row.
     * @param m11 The desired value of the component in the second column and second row.
     * @param m12 The desired value of the component in the second column and third row.
     * @param m20 The desired value of the component in the third column and first row.
     * @param m21 The desired value of the component in the third column and second row.
     * @param m22 The desired value of the component in the third column and third row.
     */
    public void set(final float m00, final float m01, final float m02,
            final float m10, final float m11, final float m12,
            final float m20, final float m21, final float m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }

    /**
     * Returns an array containing all components in this matrix in column-major order. The returned
     * array will be "safe" in that no references to it are maintained by this matrix.
     *
     * @return An array containing all components in this matrix in column-major order.
     */
    public float[] toArray() {
        return new float[] { m00, m01, m02, m10, m11, m12, m20, m21, m22 };
    }
}
