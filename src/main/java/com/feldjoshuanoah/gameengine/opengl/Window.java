package com.feldjoshuanoah.gameengine.opengl;

import com.feldjoshuanoah.gameengine.event.CallbackEventAdapter;
import java.nio.IntBuffer;
import java.util.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

/**
 * A window.
 */
public final class Window {

    /**
     * A list of all created windows which haven't been destroyed yet.
     */
    private static final List<Window> WINDOWS = new ArrayList<>();

    /**
     * A list of window hints. This is used during the creation of the window. Initially, all the
     * window hints are set to their respective default values.
     */
    private final Map<WindowHint, Integer> hints;

    /**
     * The handle. If an error occurred during the creation of the window, this will hold the value
     * {@link MemoryUtil#NULL}.
     */
    private long handle;

    /**
     * Creates a {@code Window} instance.
     */
    public Window() {
        hints = new HashMap<>();
        Arrays.stream(WindowHint.values()).forEach(hint -> hints.put(hint, hint.getDefaultValue()));
        WINDOWS.add(this);
    }

    /**
     * Returns the {@code Window} instance with the given handle. If there exists no such instance
     * this method will return {@code null}.
     *
     * @param  handle The handle of the desired window.
     * @return        The associated {@code Window} instance or {@code null}.
     */
    public static Window getWindow(final long handle) {
        return WINDOWS.stream().filter(window -> window.handle == handle).findFirst().orElse(null);
    }

    /**
     * Creates a window and its associated OpenGL or OpenGL ES context. Most of the options
     * controlling how the window and its context should be created are specified with window hints.
     *
     * @param width   The desired width, in screen coordinates, of the window.
     * @param height  The desired height, in screen coordinates, of the window.
     * @param title   The initial, UTF-8 encoded window title.
     * @param monitor The monitor to use for fullscreen mode, or {@link MemoryUtil#NULL} for
     *                windowed mode.
     * @param share   The window whose context to share resources with, or {@link MemoryUtil#NULL}
     *                to not share resources.
     */
    public void create(final int width, final int height, final String title, final long monitor,
            final long share) {
        hints.forEach((hint, value) -> GLFW.glfwWindowHint(hint.getName(), value));
        handle = GLFW.glfwCreateWindow(width, height, title, monitor, share);
        if (handle == MemoryUtil.NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }
        GLFW.glfwMakeContextCurrent(handle);
        GLFW.glfwSetKeyCallback(handle, CallbackEventAdapter::keyCallback);
        GLFW.glfwSetMouseButtonCallback(handle, CallbackEventAdapter::mouseButtonCallback);
        GLFW.glfwSetCursorPosCallback(handle, CallbackEventAdapter::cursorPositionCallback);
        GLFW.glfwSetCursorEnterCallback(handle, CallbackEventAdapter::cursorEnterCallback);
        GLFW.glfwSetScrollCallback(handle, CallbackEventAdapter::scrollCallback);
    }

    /**
     * Sets hints for the next call to {@link #create(int, int, String, long, long)}. The hints,
     * once set, retain their values until changed by a call to this function or
     * {@link #defaultHints()}, or until the library is terminated. This function does not check
     * whether the specified hint values are valid. If you set hints to invalid values this will
     * instead be reported by the next call to {@link #create(int, int, String, long, long)}. Some
     * hints are platform specific. These may be set on any platform, but they will only affect
     * their specific platform. Other platforms will ignore them. Setting these hints requires no
     * platform specific headers or functions.
     *
     * @param hint  The window hint to set.
     * @param value The new value.
     */
    public void hint(final WindowHint hint, final int value) {
        hints.replace(hint, value);
        GLFW.glfwDefaultWindowHints();
    }

    /**
     * Resets all window hints to their default values.
     */
    public void defaultHints() {
        Arrays.stream(WindowHint.values()).forEach(hint -> hints.replace(hint, hint
                .getDefaultValue()));
    }

    /**
     * Sets the swap interval for the current OpenGL or OpenGL ES context, i.e. the number of screen
     * updates to wait from the time {@link #swapBuffers()} was called before swapping the buffers
     * and returning. This is sometimes called vertical synchronization, vertical retrace
     * synchronization or just vsync.
     *
     * @param interval The minimum number of screen updates to wait for until the buffers are
     *                 swapped by {@link #swapBuffers()}.
     */
    public void swapInterval(final int interval) {
        GLFW.glfwSwapInterval(interval);
    }

    /**
     * Swaps the front and back buffers of the specified window when rendering with OpenGL or OpenGL
     * ES. If the swap interval is greater than zero, the GPU driver waits the specified number of
     * screen updates before swapping the buffers.
     */
    public void swapBuffers() {
        GLFW.glfwSwapBuffers(handle);
    }

    /**
     * Hides this window, if it was previously visible. If the window is already hidden or is in
     * full screen mode, this function does nothing.
     */
    public void hide() {
        GLFW.glfwHideWindow(handle);
    }

    /**
     * Brings this window to front and sets input focus. The window should already be visible and
     * not iconified.
     */
    public void focus() {
        GLFW.glfwFocusWindow(handle);
    }

    /**
     * Requests user attention to this window. This function requests user attention to the window.
     * On platforms where this is not supported, attention is requested to the application as a
     * whole. Once the user has given attention, usually by focusing the window or application, the
     * system will end the request automatically.
     */
    public void requestAttention() {
        GLFW.glfwRequestWindowAttention(handle);
    }

    /**
     * Makes this window visible if it was previously hidden. If the window is already visible or is
     * in full screen mode, this function does nothing.
     */
    public void show() {
        GLFW.glfwShowWindow(handle);
    }

    /**
     * Iconifies (minimizes) this window if it was previously restored. If the window is already
     * iconified, this function does nothing. If the window is a full screen window, GLFW restores
     * the original video mode of the monitor. The window's desired video mode is set again when the
     * window is restored.
     */
    public void iconify() {
        GLFW.glfwIconifyWindow(handle);
    }

    /**
     * Maximizes this window if it was previously not maximized. If the window is already maximized,
     * this function does nothing. If the window is a full screen window, this function does
     * nothing.
     */
    public void maximize() {
        GLFW.glfwMaximizeWindow(handle);
    }

    /**
     * Restores this window if it was previously iconified (minimized) or maximized. If the window
     * is already restored, this function does nothing. If the window is an iconified full screen
     * window, its desired video mode is set again for its monitor when the window is restored.
     */
    public void restore() {
        GLFW.glfwRestoreWindow(handle);
    }

    /**
     * Destroys this window and its context. On calling this function, no further callbacks
     * will be called for the window. If the context of the window is current on the main thread, it
     * is detached before being destroyed.
     */
    public void destroy() {
        GLFW.glfwDestroyWindow(handle);
    }

    /**
     * Retrieves the size, in screen coordinates, of the content area of this window. If you wish to
     * retrieve the size of the framebuffer of the window in pixels, see
     * {@link #getFramebufferSize}.
     *
     * @return The size, in screen coordinates, of the content area.
     */
    public int[] getSize() {
        final IntBuffer width = BufferUtils.createIntBuffer(1);
        final IntBuffer height = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(handle, width, height);
        return new int[] { width.get(0), height.get(0) };
    }

    /**
     * Sets the size, in screen coordinates, of the content area of this window. For full screen
     * windows, this function updates the resolution of its desired video mode and switches to the
     * video mode closest to it, without affecting the window's context. As the context is
     * unaffected, the bit depths of the framebuffer remain unchanged. If you wish to update the
     * refresh rate of the desired video mode in addition to its resolution, see
     * {@link #setMonitor}. The window manager may put limits on what sizes are allowed. GLFW cannot
     * and should not override these limits.
     *
     * @param width  The desired width, in screen coordinates, of the window content area.
     * @param height The desired height, in screen coordinates, of the window content area.
     */
    public void setSize(final int width, final int height) {
        GLFW.glfwSetWindowSize(handle, width, height);
    }

    /**
     * Sets the size limits of the content area of this window. If the window is full screen, the
     * size limits only take effect if once it is made windowed. If the window is not resizable,
     * this function does nothing. The size limits are applied immediately to a windowed mode window
     * and may cause it to be resized. The maximum dimensions must be greater than or equal to the
     * minimum dimensions and all must be greater than or equal to zero. To specify only a minimum
     * size or only a maximum one, set the other pair to {@link GLFW#GLFW_DONT_CARE}. To disable
     * size limits for a window, set them all to {@link GLFW#GLFW_DONT_CARE}.
     *
     * @param minWidth  The minimum width, in screen coordinates, of the content area, or
     *                  {@link GLFW#GLFW_DONT_CARE}.
     * @param minHeight The minimum height, in screen coordinates, of the content area, or
     *                  {@link GLFW#GLFW_DONT_CARE}.
     * @param maxWidth  The maximum width, in screen coordinates, of the content area, or
     *                  {@link GLFW#GLFW_DONT_CARE}.
     * @param maxHeight The maximum height, in screen coordinates, of the content area, or
     *                  {@link GLFW#GLFW_DONT_CARE}.
     */
    public void setSizeLimits(final int minWidth, final int minHeight, final int maxWidth,
            final int maxHeight) {
        GLFW.glfwSetWindowSizeLimits(handle, minWidth, minHeight, maxWidth, maxHeight);
    }

    /**
     * Retrieves the size, in pixels, of the framebuffer of this window. If you wish to retrieve the
     * size of the window in screen coordinates, see {@link #getSize}.
     *
     * @return The size, in pixels, of the framebuffer.
     */
    public int[] getFramebufferSize() {
        final IntBuffer width = BufferUtils.createIntBuffer(1);
        final IntBuffer height = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetFramebufferSize(handle, width, height);
        return new int[] { width.get(0), height.get(0) };
    }

    /**
     * Retrieves the position, in screen coordinates, of the upper-left corner of the content area
     * of this window.
     *
     * @return The position, in screen coordinates, of the upper-left corner of the content area.
     */
    public int[] getPosition() {
        final IntBuffer x = BufferUtils.createIntBuffer(1);
        final IntBuffer y = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowPos(handle, x, y);
        return new int[] { x.get(0), y.get(0) };
    }

    /**
     * Sets the position, in screen coordinates, of the upper-left corner of the content area of
     * this windowed mode window. If the window is a full screen window, this function does nothing.
     * Do not use this function to move an already visible window unless you have very good reasons
     * for doing so, as it will confuse and annoy the user. The window manager may put limits on
     * what positions are allowed. GLFW cannot and should not override these limits.
     *
     * @param x The x-coordinate of the upper-left corner of the content area.
     * @param y The y-coordinate of the upper-left corner of the content area.
     */
    public void setPosition(final int x, final int y) {
        GLFW.glfwSetWindowPos(handle, x, y);
    }

    /**
     * Returns the handle of the monitor that this window is in full screen on.
     *
     * @return The monitor, or {@link MemoryUtil#NULL} if the window is in windowed mode or an
     *         error occurred.
     */
    public long getMonitor() {
        return GLFW.glfwGetWindowMonitor(handle);
    }

    /**
     * Sets the mode, monitor, video mode and placement of this window. This function sets the
     * monitor that the window uses for full screen mode or, if the monitor is
     * {@link MemoryUtil#NULL}, makes it windowed mode. When setting a monitor, this function
     * updates the width, height and refresh rate of the desired video mode and switches to the
     * video mode closest to it. The window position is ignored when setting a monitor. When the
     * monitor is {@link MemoryUtil#NULL}, the position, width and height are used to place the
     * window content area. The refresh rate is ignored when no monitor is specified. If you only
     * wish to update the resolution of a full screen window or the size of a windowed mode window,
     * see {@link #setSize}. When a window transitions from full screen to windowed mode, this
     * function restores any previous window settings such as whether it is decorated, floating,
     * resizable, has size or aspect ratio limits, etc.
     *
     * @param monitor     The desired monitor, or {@link MemoryUtil#NULL} to set windowed mode.
     * @param x           The desired x-coordinate of the upper-left corner of the content area.
     * @param y           The desired y-coordinate of the upper-left corner of the content area.
     * @param width       The desired with, in screen coordinates, of the content area or video
     *                    mode.
     * @param height      The desired height, in screen coordinates, of the content area or video
     *                    mode.
     * @param refreshRate The desired refresh rate, in Hz, of the video mode, or
     *                    {@link GLFW#GLFW_DONT_CARE}.
     */
    public void setMonitor(final long monitor, final int x, final int y, final int width,
            final int height, final int refreshRate) {
        GLFW.glfwSetWindowMonitor(handle, monitor, x, y, width, height, refreshRate);
    }

    /**
     * Sets the required aspect ratio of the content area of this window. If the window is full
     * screen, the aspect ratio only takes effect once it is made windowed. If the window is not
     * resizable, this function does nothing. The aspect ratio is specified as a numerator and a
     * denominator and both values must be greater than zero. For example, the common 16:9 aspect
     * ratio is specified as 16 and 9, respectively. If the numerator and denominator is set to
     * {@link GLFW#GLFW_DONT_CARE} then the aspect ratio limit is disabled. The aspect ratio is
     * applied immediately to a windowed mode window and may cause it to be resized.
     *
     * @param numerator   The numerator of the desired aspect ratio, or {@link GLFW#GLFW_DONT_CARE}.
     * @param denominator The denominator of the desired aspect ratio, or
     *                    {@link GLFW#GLFW_DONT_CARE}.
     */
    public void setAspectRatio(final int numerator, final int denominator) {
        GLFW.glfwSetWindowAspectRatio(handle, numerator, denominator);
    }

    /**
     * Returns the opacity of the whole window. This function returns the opacity of the window,
     * including any decorations. The opacity (or alpha) value is a positive finite number between
     * zero and one, where zero is fully transparent and one is fully opaque. If the system does not
     * support whole window transparency, this function always returns one. The initial opacity
     * value for newly created windows is one.
     *
     * @return The opacity.
     */
    public float getOpacity() {
        return GLFW.glfwGetWindowOpacity(handle);
    }

    /**
     * Sets the opacity of the whole window. This function sets the opacity of the window, including
     * any decorations. The opacity (or alpha) value is a positive finite number between zero and
     * one, where zero is fully transparent and one is fully opaque. The initial opacity value for
     * newly created windows is one. A window created with framebuffer transparency may not use
     * whole window transparency. The results of doing this are undefined.
     *
     * @param opacity The desired opacity.
     */
    public void setOpacity(final float opacity) {
        GLFW.glfwSetWindowOpacity(handle, opacity);
    }

    /**
     * Sets the window title, encoded as UTF-8, of this window.
     *
     * @param title The UTF-8 encoded window title.
     */
    public void setTitle(final String title) {
        GLFW.glfwSetWindowTitle(handle, title);
    }

    /**
     * Returns the value of the close flag of the specified window.
     *
     * @return The value of the close flag.
     */
    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(handle);
    }

    /**
     * Sets the value of the close flag of this window. This can be used to override the user's
     * attempt to close the window, or to signal that it should be closed.
     *
     * @param shouldClose The new value.
     */
    public void setShouldClose(final boolean shouldClose) {
        GLFW.glfwSetWindowShouldClose(handle, shouldClose);
    }

    /**
     * Returns the handle. If an error occurred during the creation of the window, this will return
     * {@link MemoryUtil#NULL}.
     *
     * @return The handle.
     */
    public long getHandle() {
        return handle;
    }
}
