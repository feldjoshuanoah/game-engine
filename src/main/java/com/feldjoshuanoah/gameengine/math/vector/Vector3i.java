package com.feldjoshuanoah.gameengine.math.vector;

import java.util.Arrays;

/**
 * A 3-component vector of integer numbers.
 */
public class Vector3i {

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
     * Creates a new {@code Vector3i} instance and sets it to zero.
     */
    public Vector3i() {
        this(0, 0, 0);
    }

    /**
     * Creates a new {@code Vector3i} instance and sets all components to the given value.
     *
     * @param value The desired value of all components.
     */
    public Vector3i(final int value) {
        this(value, value, value);
    }

    /**
     * Creates a new {@code Vector3i} instance and makes it a copy of the given vector.
     *
     * @param vector The vector to copy the values from.
     */
    public Vector3i(final Vector3i vector) {
        this(vector.x, vector.y, vector.z);
    }

    /**
     * Creates a new {@code Vector3i} instance using the supplied integer values.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     * @param z The desired value of the z-component.
     */
    public Vector3i(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new {@code Vector3i} instance using the supplied integer array. The given array is
     * truncated or padded with zeros ({@code 0}) if it doesn't contain exactly three elements.
     *
     * @param components The desired values of the components.
     */
    public Vector3i(final int[] components) {
        final int[] fitted = Arrays.copyOf(components, 3);
        x = fitted[0];
        y = fitted[1];
        z = fitted[2];
    }

    /**
     * Adds the given scalar value to each component of this vector.
     *
     * @param scalar The scalar value to add.
     */
    public void add(final int scalar) {
        set(x + scalar, y + scalar, z + scalar);
    }

    /**
     * Adds the given vector to this vector.
     *
     * @param vector The vector to add.
     */
    public void add(final Vector3i vector) {
        set(x + vector.x, y + vector.y, y + vector.z);
    }

    /**
     * Subtracts the given scalar value from each component of this vector.
     *
     * @param scalar The scalar value to subtract.
     */
    public void subtract(final int scalar) {
        set(x - scalar, y - scalar, z - scalar);
    }

    /**
     * Subtracts the given vector from this vector.
     *
     * @param vector The vector to subtract.
     */
    public void subtract(final Vector3i vector) {
        set(x - vector.x, y - vector.y, z - vector.z);
    }

    /**
     * Multiplies each component of this vector with the given scalar value.
     *
     * @param scalar The scalar value to multiply with.
     */
    public void multiply(final int scalar) {
        set(x * scalar, y * scalar, z * scalar);
    }

    /**
     * Multiplies this vector with the given vector.
     *
     * @param vector The vector to multiply with.
     */
    public void multiply(final Vector3i vector) {
        set(x * vector.x, y * vector.y, z * vector.z);
    }

    /**
     * Divides each component of this vector by the given scalar value.
     *
     * @param scalar The scalar value to divide by.
     */
    public void divide(final int scalar) {
        set(x / scalar, y / scalar, z / scalar);
    }

    /**
     * Divides this vector by the given vector.
     *
     * @param vector The vector to divide by.
     */
    public void divide(final Vector3i vector) {
        set(x / vector.x, y / vector.y, z / vector.z);
    }

    /**
     * Calculates the cross product of this vector and the given vector.
     *
     * @param vector The vector to multiply with.
     */
    public void cross(final Vector3i vector) {
        set(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    /**
     * Calculates the dot product of this vector and the given vector.
     *
     * @param  vector The vector to multiply with.
     * @return        The dot product.
     */
    public int dot(final Vector3i vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    /**
     * Returns the length of this vector.
     *
     * @return The length.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
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
        set(0, 0, 0);
    }

    /**
     * Negates this vector.
     */
    public void negate() {
        set(-x, -y, -z);
    }

    /**
     * Returns the smallest component of this vector.
     *
     * @return The smallest component.
     */
    public int min() {
        return Math.min(x, Math.min(y, z));
    }

    /**
     * Returns the largest component of this vector.
     *
     * @return The largest component.
     */
    public int max() {
        return Math.max(x, Math.max(y, z));
    }

    /**
     * Sets each component of this vector to its absolute value.
     */
    public void abs() {
        set(Math.abs(x), Math.abs(y), Math.abs(z));
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
     * Sets the values of all components of this vector.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     * @param z The desired value of the z-component.
     */
    public void set(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
