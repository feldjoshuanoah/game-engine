package com.feldjoshuanoah.gameengine.math.vector;

import java.util.Arrays;

/**
 * A 4-component vector of single-precision floating-point numbers.
 */
public class Vector4f {

    /**
     * The x-component.
     */
    private float x;

    /**
     * The y-component.
     */
    private float y;

    /**
     * The z-component.
     */
    private float z;

    /**
     * The w-component.
     */
    private float w;


    /**
     * Creates a new {@code Vector4f} instance and sets it to zero.
     */
    public Vector4f() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Creates a new {@code Vector4f} instance and sets all components to the given value.
     *
     * @param value The desired value of all components.
     */
    public Vector4f(final float value) {
        this(value, value, value, value);
    }

    /**
     * Creates a new {@code Vector4f} instance and makes it a copy of the given vector.
     *
     * @param vector The vector to copy the values from.
     */
    public Vector4f(final Vector4f vector) {
        this(vector.x, vector.y, vector.z, vector.w);
    }

    /**
     * Creates a new {@code Vector4f} instance using the supplied float values.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     * @param z The desired value of the z-component.
     * @param w The desired value of the w-component.
     */
    public Vector4f(final float x, final float y, final float z, final float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Creates a new {@code Vector4f} instance using the supplied float array. The given array is
     * truncated or padded with zeros ({@code 0.0f}) if it doesn't contain exactly four elements.
     *
     * @param components The desired values of the components.
     */
    public Vector4f(final float... components) {
        final float[] fitted = Arrays.copyOf(components, 4);
        x = fitted[0];
        y = fitted[1];
        z = fitted[2];
        w = fitted[3];
    }

    /**
     * Adds the given scalar value to each component of this vector.
     *
     * @param scalar The scalar value to add.
     */
    public void add(final float scalar) {
        set(x + scalar, y + scalar, z + scalar, w + scalar);
    }

    /**
     * Adds the given vector to this vector.
     *
     * @param vector The vector to add.
     */
    public void add(final Vector4f vector) {
        set(x + vector.x, y + vector.y, y + vector.z, w + vector.w);
    }

    /**
     * Subtracts the given scalar value from each component of this vector.
     *
     * @param scalar The scalar value to subtract.
     */
    public void subtract(final float scalar) {
        set(x - scalar, y - scalar, z - scalar, w - scalar);
    }

    /**
     * Subtracts the given vector from this vector.
     *
     * @param vector The vector to subtract.
     */
    public void subtract(final Vector4f vector) {
        set(x - vector.x, y - vector.y, z - vector.z, w - vector.w);
    }

    /**
     * Multiplies each component of this vector with the given scalar value.
     *
     * @param scalar The scalar value to multiply with.
     */
    public void multiply(final float scalar) {
        set(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    /**
     * Multiplies this vector with the given vector.
     *
     * @param vector The vector to multiply with.
     */
    public void multiply(final Vector4f vector) {
        set(x * vector.x, y * vector.y, z * vector.z, w * vector.w);
    }

    /**
     * Divides each component of this vector by the given scalar value.
     *
     * @param scalar The scalar value to divide by.
     */
    public void divide(final float scalar) {
        set(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    /**
     * Divides this vector by the given vector.
     *
     * @param vector The vector to divide by.
     */
    public void divide(final Vector4f vector) {
        set(x / vector.x, y / vector.y, z / vector.z, w / vector.w);
    }

    /**
     * Calculates the dot product of this vector and the given vector.
     *
     * @param  vector The vector to multiply with.
     * @return        The dot product.
     */
    public float dot(final Vector4f vector) {
        return x * vector.x + y * vector.y + z * vector.z + w * vector.w;
    }

    /**
     * Returns the length of this vector.
     *
     * @return The length.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * Normalizes this vector.
     */
    public void normalize() {
        divide(length());
    }

    /**
     * Sets all components if this vector to zero.
     */
    public void zero() {
        set(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Negates this vector.
     */
    public void negate() {
        set(-x, -y, -z, -w);
    }

    /**
     * Returns the smallest component of this vector.
     *
     * @return The smallest component.
     */
    public float min() {
        return Math.min(x, Math.min(y, Math.min(z, w)));
    }

    /**
     * Returns the largest component of this vector.
     *
     * @return The largest component.
     */
    public float max() {
        return Math.max(x, Math.max(y, Math.max(z, w)));
    }

    /**
     * Sets each component of this vector to its largest float value that is less than or equal to
     * the component and is equal to a mathematical integer.
     */
    public void floor() {
        set((float) Math.floor(x), (float) Math.floor(y), (float) Math.floor(z),
                (float) Math.floor(w));
    }

    /**
     * Sets each component of this vector to its smallest float value that is greater than or equal
     * to the component and is equal to a mathematical integer.
     */
    public void ceil() {
        set((float) Math.ceil(x), (float) Math.ceil(y), (float) Math.ceil(z), (float) Math.ceil(w));
    }

    /**
     * Sets each component of this vector to the closest float value to the component that is equal
     * to a mathematical integer, with ties rounding to positive infinity.
     */
    public void round() {
        set(Math.round(x), Math.round(y), Math.round(z), Math.round(w));
    }

    /**
     * Sets each component of this vector to its absolute value.
     */
    public void abs() {
        set(Math.abs(x), Math.abs(y), Math.abs(z), Math.abs(w));
    }

    /**
     * Returns the x-component of this vector.
     *
     * @return The x-component.
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x-component of this vector.
     *
     * @param x The desired value of the x-component.
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Returns the y-component of this vector.
     *
     * @return The y-component.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y-component of this vector.
     *
     * @param y The desired value of the y-component.
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * Returns the z-component of this vector.
     *
     * @return The z-component.
     */
    public float getZ() {
        return z;
    }

    /**
     * Sets the z-component of this vector.
     *
     * @param z The desired value of the z-component.
     */
    public void setZ(final float z) {
        this.z = z;
    }

    /**
     * Returns the w-component of this vector.
     *
     * @return The w-component.
     */
    public float getW() {
        return w;
    }

    /**
     * Sets the w-component of this vector.
     *
     * @param w The desired value of the w-component.
     */
    public void setW(final float w) {
        this.w = w;
    }

    /**
     * Sets the values of all components of this vector.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     * @param z The desired value of the z-component.
     * @param w The desired value of the w-component.
     */
    public void set(final float x, final float y, final float z, final float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
