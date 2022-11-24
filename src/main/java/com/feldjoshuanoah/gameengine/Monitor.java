package com.feldjoshuanoah.gameengine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Objects;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWGammaRamp;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWVidMode.Buffer;

/**
 * A monitor.
 */
public class Monitor {

    /**
     * The handle.
     */
    private final long handle;

    /**
     * Creates a new {@code Monitor} instance with the given handle.
     *
     * @param handle The desired monitor handle.
     */
    public Monitor(final long handle) {
        this.handle = handle;
    }

    /**
     * Returns the primary monitor. This is usually the monitor where elements like the task bar or
     * global menu bar are located. The primary monitor is always first in the array returned by
     * {@link #getMonitors()}.
     *
     * @return The primary monitor.
     */
    public static Monitor getPrimaryMonitor() {
        return new Monitor(GLFW.glfwGetPrimaryMonitor());
    }

    /**
     * Returns an array of all currently connected monitors. The primary monitor is always first in
     * the returned array.
     *
     * @return An array of monitors.
     */
    public static Monitor[] getMonitors() {
        final PointerBuffer buffer = GLFW.glfwGetMonitors();
        final Monitor[] monitors = new Monitor[Objects.requireNonNull(buffer).limit()];
        Arrays.setAll(monitors, i -> new Monitor(buffer.get(i)));
        return monitors;
    }

    /**
     * Returns an array of all video modes supported by this monitor. The returned array is sorted
     * in ascending order, first by color bit depth (the sum of all channel depths), then by
     * resolution area (the product of width and height), then resolution width and finally by
     * refresh rate.
     *
     * @return An array of video modes.
     */
    public GLFWVidMode[] getVideoModes() {
        final Buffer buffer = GLFW.glfwGetVideoModes(handle);
        final GLFWVidMode[] vidModes = new GLFWVidMode[Objects.requireNonNull(buffer).limit()];
        Arrays.setAll(vidModes, buffer::get);
        return vidModes;
    }

    /**
     * Returns the current video mode of this monitor. If you have created a full screen window for
     * that monitor, the return value will depend on whether that window is iconified.
     *
     * @return The current mode of the monitor.
     */
    public GLFWVidMode getVideoMode() {
        return GLFW.glfwGetVideoMode(handle);
    }

    /**
     * Returns the size, in millimetres, of the display area of this monitor. Some platforms do not
     * provide accurate monitor size information, either because the monitor EDID data is incorrect
     * or because the driver does not report it accurately.
     *
     * @return The size, in millimetres, of the monitor's display area.
     */
    public int[] getPhysicalSize() {
        final IntBuffer width = BufferUtils.createIntBuffer(1);
        final IntBuffer height = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetMonitorPhysicalSize(handle, width, height);
        return new int[] { width.get(0), height.get(0) };
    }

    /**
     * Retrieves the content scale for this monitor. This function retrieves the content scale for
     * the monitor. The content scale is the ratio between the current DPI and the platform's
     * default DPI. This is especially important for text and any UI elements. If the pixel
     * dimensions of your UI scaled by this look appropriate on your machine then it should appear
     * at a reasonable size on other machines regardless of their DPI and scaling settings. This
     * relies on the system DPI and scaling settings being somewhat correct. The content scale may
     * depend on both the monitor resolution and pixel density and on user settings. It may be very
     * different from the raw DPI calculated from the physical size and current resolution.
     *
     * @return The content scale for this monitor.
     */
    public float[] getContentScale() {
        final FloatBuffer xScale = BufferUtils.createFloatBuffer(1);
        final FloatBuffer yScale = BufferUtils.createFloatBuffer(1);
        GLFW.glfwGetMonitorContentScale(handle, xScale, yScale);
        return new float[] { xScale.get(0), yScale.get(0) };
    }

    /**
     * Returns the position, in screen coordinates, of the upper-left corner of this monitor.
     *
     * @return The position, in screen coordinates, of the upper-left corner.
     */
    public int[] getPosition() {
        final IntBuffer x = BufferUtils.createIntBuffer(1);
        final IntBuffer y = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetMonitorPos(handle, x, y);
        return new int[] { x.get(0), y.get(0) };
    }

    /**
     * Returns a human-readable name, encoded as UTF-8, of the specified monitor. The name typically
     * reflects the make and model of the monitor and is not guaranteed to be unique among the
     * connected monitors.
     *
     * @return The UTF-8 encoded name of the monitor.
     */
    public String getName() {
        return GLFW.glfwGetMonitorName(handle);
    }

    /**
     * Returns the current gamma ramp of this monitor.
     *
     * @return The current gamma ramp.
     */
    public GLFWGammaRamp getGammaRamp() {
        return GLFW.glfwGetGammaRamp(handle);
    }

    /**
     * Sets the current gamma ramp for this monitor. This function sets the current gamma ramp for
     * the specified monitor. The original gamma ramp for that monitor is saved by GLFW the first
     * time this function is called and is restored by {@link GLFW#glfwTerminate}.
     *
     * @param gammaRamp The desired gamma ramp.
     */
    public void setGammaRamp(final GLFWGammaRamp gammaRamp) {
        GLFW.glfwSetGammaRamp(handle, gammaRamp);
    }

    /**
     * Generates a gamma ramp and sets it for this monitor. This function generates an appropriately
     * sized gamma ramp from the specified exponent and then calls
     * {@link #setGammaRamp(GLFWGammaRamp)} with it. The value must be a finite number greater than
     * zero. The software controlled gamma ramp is applied in addition to the hardware gamma
     * correction, which today is usually an approximation of sRGB gamma. This means that setting a
     * perfectly linear ramp, or gamma 1.0, will produce the default (usually sRGB-like) behavior.
     * For gamma correct rendering with OpenGL or OpenGL ES, see the {@link WindowHint#SRGB_CAPABLE}
     * hint.
     *
     * @param gamma The desired exponent.
     */
    public void setGamma(final float gamma) {
        GLFW.glfwSetGamma(handle, gamma);
    }

    /**
     * Returns the handle of this monitor.
     *
     * @return The handle.
     */
    public long getHandle() {
        return handle;
    }
}
