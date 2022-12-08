package com.feldjoshuanoah.gameengine.math.vector;

import java.util.Arrays;

/**
 * A 2-component vector of integer numbers.
 */
public class Vector2i {

    /**
     * The x-component.
     */
    private int x;

    /**
     * The y-component.
     */
    private int y;

    /**
     * Creates a new {@code Vector2i} instance and sets it to zero.
     */
    public Vector2i() {
        this(0, 0);
    }

    /**
     * Creates a new {@code Vector2i} instance and sets all components to the given value.
     *
     * @param value The desired value of all components.
     */
    public Vector2i(final int value) {
        this(value, value);
    }

    /**
     * Creates a new {@code Vector2i} instance and makes it a copy of the given vector.
     *
     * @param vector The vector to copy the values from.
     */
    public Vector2i(final Vector2i vector) {
        this(vector.x, vector.y);
    }

    /**
     * Creates a new {@code Vector2i} instance using the supplied integer values.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     */
    public Vector2i(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new {@code Vector2i} instance using the supplied integer array. The given array is
     * truncated or padded with zeros ({@code 0}) if it doesn't contain exactly two elements.
     *
     * @param components The desired values of the components.
     */
    public Vector2i(final int[] components) {
        int[] fitted = Arrays.copyOf(components, 2);
        x = fitted[0];
        y = fitted[1];
    }

    /**
     * Adds the given scalar value to each component of this vector.
     *
     * @param scalar The scalar value to add.
     */
    public void add(final int scalar) {
        set(x + scalar, y + scalar);
    }

    /**
     * Adds the given vector to this vector.
     *
     * @param vector The vector to add.
     */
    public void add(final Vector2i vector) {
        set(x + vector.x, y + vector.y);
    }

    /**
     * Subtracts the given scalar value from each component of this vector.
     *
     * @param scalar The scalar value to subtract.
     */
    public void subtract(final int scalar) {
        set(x - scalar, y - scalar);
    }

    /**
     * Subtracts the given vector from this vector.
     *
     * @param vector The vector to subtract.
     */
    public void subtract(final Vector2i vector) {
        set(x - vector.x, y - vector.y);
    }

    /**
     * Multiplies each component of this vector with the given scalar value.
     *
     * @param scalar The scalar value to multiply with.
     */
    public void multiply(final int scalar) {
        set(x * scalar, y * scalar);
    }

    /**
     * Multiplies this vector with the given vector.
     *
     * @param vector The vector to multiply with.
     */
    public void multiply(final Vector2i vector) {
        set(x * vector.x, y * vector.y);
    }

    /**
     * Divides each component of this vector by the given scalar value.
     *
     * @param scalar The scalar value to divide by.
     */
    public void divide(final int scalar) {
        set(x / scalar, y / scalar);
    }

    /**
     * Divides this vector by the given vector.
     *
     * @param vector The vector to divide by.
     */
    public void divide(final Vector2i vector) {
        set(x / vector.x, y / vector.y);
    }

    /**
     * Calculates the dot product of this vector and the given vector.
     *
     * @param  vector The vector to multiply with.
     * @return        The dot product.
     */
    public int dot(final Vector2i vector) {
        return x * vector.x + y * vector.y;
    }

    /**
     * Returns the length of this vector.
     *
     * @return The length.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
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
        set(0, 0);
    }

    /**
     * Negates this vector.
     */
    public void negate() {
        set(-x, -y);
    }

    /**
     * Returns the smallest component of this vector.
     *
     * @return The smallest component.
     */
    public float min() {
        return Math.min(x, y);
    }

    /**
     * Returns the largest component of this vector.
     *
     * @return The largest component.
     */
    public float max() {
        return Math.max(x, y);
    }

    /**
     * Sets each component of this vector to its absolute value.
     */
    public void abs() {
        set(Math.abs(x), Math.abs(y));
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
     * Sets the values of all components of this vector.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     */
    public void set(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
