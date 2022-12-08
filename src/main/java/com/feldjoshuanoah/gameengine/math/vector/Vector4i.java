package com.feldjoshuanoah.gameengine.math.vector;

import java.util.Arrays;

/**
 * A 4-component vector of integer numbers.
 */
public class Vector4i {

    /**
     * The x-component.
     */
    private int x;

    /**
     * The y-component.
     */
    private int y;

    /**
     * The z-component.
     */
    private int z;

    /**
     * The w-component.
     */
    private int w;


    /**
     * Creates a new {@code Vector3f} instance and sets it to zero.
     */
    public Vector4i() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a new {@code Vector4i} instance and sets all components to the given value.
     *
     * @param value The desired value of all components.
     */
    public Vector4i(final int value) {
        this(value, value, value, value);
    }

    /**
     * Creates a new {@code Vector4i} instance and makes it a copy of the given vector.
     *
     * @param vector The vector to copy the values from.
     */
    public Vector4i(final Vector4i vector) {
        this(vector.x, vector.y, vector.z, vector.w);
    }

    /**
     * Creates a new {@code Vector4i} instance using the supplied integer values.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     * @param z The desired value of the z-component.
     * @param w The desired value of the w-component.
     */
    public Vector4i(final int x, final int y, final int z, final int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Creates a new {@code Vector4i} instance using the supplied integer array. The given array is
     * truncated or padded with zeros ({@code 0}) if it doesn't contain exactly four elements.
     *
     * @param components The desired values of the components.
     */
    public Vector4i(final int... components) {
        final int[] fitted = Arrays.copyOf(components, 4);
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
    public void add(final int scalar) {
        set(x + scalar, y + scalar, z + scalar, w + scalar);
    }

    /**
     * Adds the given vector to this vector.
     *
     * @param vector The vector to add.
     */
    public void add(final Vector4i vector) {
        set(x + vector.x, y + vector.y, y + vector.z, w + vector.w);
    }

    /**
     * Subtracts the given scalar value from each component of this vector.
     *
     * @param scalar The scalar value to subtract.
     */
    public void subtract(final int scalar) {
        set(x - scalar, y - scalar, z - scalar, w - scalar);
    }

    /**
     * Subtracts the given vector from this vector.
     *
     * @param vector The vector to subtract.
     */
    public void subtract(final Vector4i vector) {
        set(x - vector.x, y - vector.y, z - vector.z, w - vector.w);
    }

    /**
     * Multiplies each component of this vector with the given scalar value.
     *
     * @param scalar The scalar value to multiply with.
     */
    public void multiply(final int scalar) {
        set(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    /**
     * Multiplies this vector with the given vector.
     *
     * @param vector The vector to multiply with.
     */
    public void multiply(final Vector4i vector) {
        set(x * vector.x, y * vector.y, z * vector.z, w * vector.w);
    }

    /**
     * Divides each component of this vector by the given scalar value.
     *
     * @param scalar The scalar value to divide by.
     */
    public void divide(final int scalar) {
        set(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    /**
     * Divides this vector by the given vector.
     *
     * @param vector The vector to divide by.
     */
    public void divide(final Vector4i vector) {
        set(x / vector.x, y / vector.y, z / vector.z, w / vector.w);
    }

    /**
     * Calculates the dot product of this vector and the given vector.
     *
     * @param  vector The vector to multiply with.
     * @return        The dot product.
     */
    public int dot(final Vector4i vector) {
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
        divide((int) length());
    }

    /**
     * Sets all components if this vector to zero.
     */
    public void zero() {
        set(0, 0, 0, 0);
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
    public int min() {
        return Math.min(x, Math.min(y, Math.min(z, w)));
    }

    /**
     * Returns the largest component of this vector.
     *
     * @return The largest component.
     */
    public int max() {
        return Math.max(x, Math.max(y, Math.max(z, w)));
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
    public int getX() {
        return x;
    }

    /**
     * Sets the x-component of this vector.
     *
     * @param x The desired value of the x-component.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Returns the y-component of this vector.
     *
     * @return The y-component.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-component of this vector.
     *
     * @param y The desired value of the y-component.
     */
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Returns the z-component of this vector.
     *
     * @return The z-component.
     */
    public int getZ() {
        return z;
    }

    /**
     * Sets the z-component of this vector.
     *
     * @param z The desired value of the z-component.
     */
    public void setZ(final int z) {
        this.z = z;
    }

    /**
     * Returns the w-component of this vector.
     *
     * @return The w-component.
     */
    public int getW() {
        return w;
    }

    /**
     * Sets the w-component of this vector.
     *
     * @param w The desired value of the w-component.
     */
    public void setW(final int w) {
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
    public void set(final int x, final int y, final int z, final int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
