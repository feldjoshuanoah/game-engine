package com.feldjoshuanoah.gameengine.math.vector;

import java.util.Arrays;

/**
 * A 2-component vector of single-precision floating-point numbers.
 */
public class Vector2f {

    /**
     * The x-component.
     */
    private float x;

    /**
     * The y-component.
     */
    private float y;

    /**
     * Creates a new {@code Vector2f} instance and sets it to zero.
     */
    public Vector2f() {
        this(0.0f, 0.0f);
    }

    /**
     * Creates a new {@code Vector2f} instance and sets all components to the given value.
     *
     * @param value The desired value of all components.
     */
    public Vector2f(final float value) {
        this(value, value);
    }

    /**
     * Creates a new {@code Vector2f} instance and makes it a copy of the given vector.
     *
     * @param vector The vector to copy the values from.
     */
    public Vector2f(final Vector2f vector) {
        this(vector.x, vector.y);
    }

    /**
     * Creates a new {@code Vector2f} instance using the supplied float values.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     */
    public Vector2f(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new {@code Vector2f} instance using the supplied float array. The given array is
     * truncated or padded with zeros ({@code 0.0f}) if it doesn't contain exactly two elements.
     *
     * @param components The desired values of the components.
     */
    public Vector2f(final float... components) {
        final float[] fitted = Arrays.copyOf(components, 2);
        x = fitted[0];
        y = fitted[1];
    }

    /**
     * Adds the given scalar value to each component of this vector.
     *
     * @param scalar The scalar value to add.
     */
    public void add(final float scalar) {
        set(x + scalar, y + scalar);
    }

    /**
     * Adds the given vector to this vector.
     *
     * @param vector The vector to add.
     */
    public void add(final Vector2f vector) {
        set(x + vector.x, y + vector.y);
    }

    /**
     * Subtracts the given scalar value from each component of this vector.
     *
     * @param scalar The scalar value to subtract.
     */
    public void subtract(final float scalar) {
        set(x - scalar, y - scalar);
    }

    /**
     * Subtracts the given vector from this vector.
     *
     * @param vector The vector to subtract.
     */
    public void subtract(final Vector2f vector) {
        set(x - vector.x, y - vector.y);
    }

    /**
     * Multiplies each component of this vector with the given scalar value.
     *
     * @param scalar The scalar value to multiply with.
     */
    public void multiply(final float scalar) {
        set(x * scalar, y * scalar);
    }

    /**
     * Multiplies this vector with the given vector.
     *
     * @param vector The vector to multiply with.
     */
    public void multiply(final Vector2f vector) {
        set(x * vector.x, y * vector.y);
    }

    /**
     * Divides each component of this vector by the given scalar value.
     *
     * @param scalar The scalar value to divide by.
     */
    public void divide(final float scalar) {
        set(x / scalar, y / scalar);
    }

    /**
     * Divides this vector by the given vector.
     *
     * @param vector The vector to divide by.
     */
    public void divide(final Vector2f vector) {
        set(x / vector.x, y / vector.y);
    }

    /**
     * Calculates the dot product of this vector and the given vector.
     *
     * @param  vector The vector to multiply with.
     * @return        The dot product.
     */
    public float dot(final Vector2f vector) {
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
        divide(length());
    }

    /**
     * Sets all components if this vector to zero.
     */
    public void zero() {
        set(0.0f, 0.0f);
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
     * Sets each component of this vector to its largest float value that is less than or equal to
     * the component and is equal to a mathematical integer.
     */
    public void floor() {
        set((float) Math.floor(x), (float) Math.floor(y));
    }

    /**
     * Sets each component of this vector to its smallest float value that is greater than or equal
     * to the component and is equal to a mathematical integer.
     */
    public void ceil() {
        set((float) Math.ceil(x), (float) Math.ceil(y));
    }

    /**
     * Sets each component of this vector to the closest float value to the component that is equal
     * to a mathematical integer, with ties rounding to positive infinity.
     */
    public void round() {
        set(Math.round(x), Math.round(y));
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
     * Sets the values of all components of this vector.
     *
     * @param x The desired value of the x-component.
     * @param y The desired value of the y-component.
     */
    public void set(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
}
