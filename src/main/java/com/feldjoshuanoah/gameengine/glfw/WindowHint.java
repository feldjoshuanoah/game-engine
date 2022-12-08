package com.feldjoshuanoah.gameengine.glfw;

import org.lwjgl.glfw.GLFW;

/**
 * Window hints. There are a number of hints that can be set before the creation of a window and
 * context. Some affect the window itself, others affect the framebuffer or context. These hints are
 * set to their default values each time the library is initialized with glfwInit. Some hints are
 * platform specific. These are always valid to set on any platform, but they will only affect their
 * specific platform. Other platforms will ignore them. Setting these hints requires no platform
 * specific headers or calls. Some window hints are hard constraints. These must match the available
 * capabilities exactly for window and context creation to succeed. Hints that are not hard
 * constraints are matched as closely as possible, but the resulting context and framebuffer may
 * differ from what these hints requested.
 */
public enum WindowHint {

    /**
     * Specifies whether the windowed mode window will be resizable by the user. The window will
     * still be resizable using the {@link Window#setSize(int, int)} function. Possible values are
     * {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This hint is ignored for full screen and
     * undecorated windows.
     */
    RESIZABLE(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE),
    /**
     * Specifies whether the windowed mode window will be initially visible. Possible values are
     * {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This hint is ignored for full screen
     * windows.
     */
    VISIBLE(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE),
    /**
     * Specifies whether the windowed mode window will have window decorations such as a border, a
     * close widget, etc. An undecorated window will not be resizable by the user but will still
     * allow the user to generate close events on some platforms. Possible values are
     * {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This hint is ignored for full screen
     * windows.
     */
    DECORATED(GLFW.GLFW_DECORATED, GLFW.GLFW_TRUE),
    /**
     * Specifies whether the windowed mode window will be given input focus when created. Possible
     * values are {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This hint is ignored for full
     * screen and initially hidden windows.
     */
    FOCUSED(GLFW.GLFW_FOCUSED, GLFW.GLFW_TRUE),
    /**
     * Specifies whether the full screen window will automatically iconify and restore the previous
     * video mode on input focus loss. Possible values are {@link GLFW#GLFW_TRUE} and
     * {@link GLFW#GLFW_FALSE}. This hint is ignored for windowed mode windows.
     */
    AUTO_ICONIFY(GLFW.GLFW_AUTO_ICONIFY, GLFW.GLFW_TRUE),
    /**
     * Specifies whether the windowed mode window will be floating above other regular windows, also
     * called topmost or always-on-top. This is intended primarily for debugging purposes and cannot
     * be used to implement proper full screen windows. Possible values are {@link GLFW#GLFW_TRUE}
     * and {@link GLFW#GLFW_FALSE}. This hint is ignored for full screen windows.
     */
    FLOATING(GLFW.GLFW_FLOATING, GLFW.GLFW_FALSE),
    /**
     * Specifies whether the windowed mode window will be maximized when created. Possible values
     * are {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This hint is ignored for full screen
     * windows.
     */
    MAXIMIZED(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_FALSE),
    /**
     * Specifies whether the cursor should be centered over newly created full screen windows.
     * Possible values are {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This hint is ignored
     * for windowed mode windows.
     */
    CENTER_CURSOR(GLFW.GLFW_CENTER_CURSOR, GLFW.GLFW_TRUE),
    /**
     * Specifies whether the window framebuffer will be transparent. If enabled and supported by the
     * system, the window framebuffer alpha channel will be used to combine the framebuffer with the
     * background. This does not affect window decorations. Possible values are
     * {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}.
     */
    TRANSPARENT_FRAMEBUFFER(GLFW.GLFW_TRANSPARENT_FRAMEBUFFER, GLFW.GLFW_FALSE),
    /**
     * Specifies whether the window will be given input focus when {@link Window#show()} is called.
     * Possible values are {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}.
     */
    FOCUS_ON_SHOW(GLFW.GLFW_FOCUS_ON_SHOW, GLFW.GLFW_TRUE),
    /**
     * Specifies whether the window content area should be resized based on the monitor content
     * scale of any monitor it is placed on. This includes the initial placement when the window is
     * created. Possible values are {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This hint
     * only has an effect on platforms where screen coordinates and pixels always map 1:1 such as
     * Windows and X11. On platforms like macOS the resolution of the framebuffer is changed
     * independently of the window size.
     */
    SCALE_TO_MONITOR(GLFW.GLFW_SCALE_TO_MONITOR, GLFW.GLFW_FALSE),
    /**
     * Specifies the desired bit depth of the red component of the default framebuffer. A value of
     * {@link GLFW#GLFW_DONT_CARE} means the application has no preference.
     */
    RED_BITS(GLFW.GLFW_RED_BITS, 8),
    /**
     * Specifies the desired bit depth of the green component of the default framebuffer. A value of
     * {@link GLFW#GLFW_DONT_CARE} means the application has no preference.
     */
    GREEN_BITS(GLFW.GLFW_GREEN_BITS, 8),
    /**
     * Specifies the desired bit depth of the blue component of the default framebuffer. A value of
     * {@link GLFW#GLFW_DONT_CARE} means the application has no preference.
     */
    BLUE_BITS(GLFW.GLFW_BLUE_BITS, 8),
    /**
     * Specifies the desired bit depth of the alpha component of the default framebuffer. A value of
     * {@link GLFW#GLFW_DONT_CARE} means the application has no preference.
     */
    ALPHA_BITS(GLFW.GLFW_ALPHA_BITS, 8),
    /**
     * Specifies the desired bit depth of the depth component of the default framebuffer. A value of
     * {@link GLFW#GLFW_DONT_CARE} means the application has no preference.
     */
    DEPTH_BITS(GLFW.GLFW_DEPTH_BITS, 24),
    /**
     * Specifies the desired bit depth of the stencil component of the default framebuffer. A value
     * of {@link GLFW#GLFW_DONT_CARE} means the application has no preference.
     */
    STENCIL_BITS(GLFW.GLFW_STENCIL_BITS, 8),
    /**
     * Specifies the desired bit depth of the red component of the accumulation framebuffer. A value
     * of {@link GLFW#GLFW_DONT_CARE} means the application has no preference. Accumulation buffers
     * are a legacy OpenGL feature and should not be used in new code.
     */
    ACCUM_RED_BITS(GLFW.GLFW_ACCUM_RED_BITS, 0),
    /**
     * Specifies the desired bit depth of the green component of the accumulation framebuffer. A
     * value of {@link GLFW#GLFW_DONT_CARE} means the application has no preference. Accumulation
     * buffers are a legacy OpenGL feature and should not be used in new code.
     */
    ACCUM_GREEN_BITS(GLFW.GLFW_ACCUM_GREEN_BITS, 0),
    /**
     * Specifies the desired bit depth of the blue component of the accumulation framebuffer. A
     * value of {@link GLFW#GLFW_DONT_CARE} means the application has no preference. Accumulation
     * buffers are a legacy OpenGL feature and should not be used in new code.
     */
    ACCUM_BLUE_BITS(GLFW.GLFW_ACCUM_BLUE_BITS, 0),
    /**
     * Specifies the desired bit depth of the alpha component of the accumulation framebuffer. A
     * value of {@link GLFW#GLFW_DONT_CARE} means the application has no preference. Accumulation
     * buffers are a legacy OpenGL feature and should not be used in new code.
     */
    ACCUM_ALPHA_BITS(GLFW.GLFW_ACCUM_ALPHA_BITS, 0),
    /**
     * Specifies the desired number of auxiliary buffers. A value of {@link GLFW#GLFW_DONT_CARE}
     * means the application has no preference. Auxiliary buffers are a legacy OpenGL feature and
     * should not be used in new code.
     */
    AUX_BUFFERS(GLFW.GLFW_AUX_BUFFERS, 0),
    /**
     * Specifies the desired number of samples to use for multisampling. Zero disables
     * multisampling. A value of {@link GLFW#GLFW_DONT_CARE} means the application has no
     * preference.
     */
    SAMPLES(GLFW.GLFW_SAMPLES, 0),
    /**
     * Specifies the desired refresh rate for full screen windows. A value of
     * {@link GLFW#GLFW_DONT_CARE} means the highest available refresh rate will be used. This hint
     * is ignored for windowed mode windows.
     */
    REFRESH_RATE(GLFW.GLFW_REFRESH_RATE, 0),
    /**
     * Specifies whether to use OpenGL stereoscopic rendering. Possible values are
     * {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. This is a hard constraint.
     */
    STEREO(GLFW.GLFW_STEREO, GLFW.GLFW_FALSE),
    /**
     * Specifies whether the framebuffer should be sRGB capable. Possible values are
     * {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}.
     */
    SRGB_CAPABLE(GLFW.GLFW_SRGB_CAPABLE, GLFW.GLFW_FALSE),
    /**
     * Specifies whether the framebuffer should be double buffered. You nearly always want to use
     * double buffering. This is a hard constraint. Possible values are {@link GLFW#GLFW_TRUE} and
     * {@link GLFW#GLFW_FALSE}.
     */
    DOUBLEBUFFER(GLFW.GLFW_DOUBLEBUFFER, GLFW.GLFW_TRUE),
    /**
     * Specifies which client API to create the context for. Possible values are
     * {@link GLFW#GLFW_OPENGL_API}, {@link GLFW#GLFW_OPENGL_ES_API}, and {@link GLFW#GLFW_NO_API}.
     * This is a hard constraint.
     */
    CLIENT_API(GLFW.GLFW_CLIENT_API, GLFW.GLFW_OPENGL_API),
    /**
     * Specifies which context creation API to use to create the context. Possible values are
     * {@link GLFW#GLFW_NATIVE_CONTEXT_API}, {@link GLFW#GLFW_EGL_CONTEXT_API}, and
     * {@link GLFW#GLFW_OSMESA_CONTEXT_API}. This is a hard constraint. If no client API is
     * requested, this hint is ignored.
     */
    CONTEXT_CREATION_API(GLFW.GLFW_CONTEXT_CREATION_API, GLFW.GLFW_NATIVE_CONTEXT_API),
    /**
     * Specifies the client API major version that the created context must be compatible with. The
     * exact behavior of this hint depend on the requested client API.
     */
    CONTEXT_VERSION_MAJOR(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 1),
    /**
     * Specifies the client API minor version that the created context must be compatible with. The
     * exact behavior of this hint depend on the requested client API.
     */
    CONTEXT_VERSION_MINOR(GLFW.GLFW_CONTEXT_VERSION_MINOR, 0),
    /**
     * Specifies the robustness strategy to be used by the context. This can be one of
     * {@link GLFW#GLFW_NO_RESET_NOTIFICATION}, {@link GLFW#GLFW_LOSE_CONTEXT_ON_RESET}, or
     * {@link GLFW#GLFW_NO_ROBUSTNESS} to not request a robustness strategy.
     */
    CONTEXT_ROBUSTNESS(GLFW.GLFW_CONTEXT_ROBUSTNESS, GLFW.GLFW_NO_ROBUSTNESS),
    /**
     * Specifies the release behavior to be used by the context. Possible values are one of
     * {@link GLFW#GLFW_ANY_RELEASE_BEHAVIOR}, {@link GLFW#GLFW_RELEASE_BEHAVIOR_FLUSH}, or
     * {@link GLFW#GLFW_RELEASE_BEHAVIOR_NONE}. If the behavior is
     * {@link GLFW#GLFW_ANY_RELEASE_BEHAVIOR}, the default behavior of the context creation API will
     * be used. If the behavior is {@link GLFW#GLFW_RELEASE_BEHAVIOR_FLUSH}, the pipeline will be
     * flushed whenever the context is released from being the current one. If the behavior is
     * {@link GLFW#GLFW_RELEASE_BEHAVIOR_NONE}, the pipeline will not be flushed on release.
     */
    CONTEXT_RELEASE_BEHAVIOR(GLFW.GLFW_CONTEXT_RELEASE_BEHAVIOR, GLFW.GLFW_ANY_RELEASE_BEHAVIOR),
    /**
     * Specifies whether the OpenGL context should be forward-compatible, i.e. one where all
     * functionality deprecated in the requested version of OpenGL is removed. This must only be
     * used if the requested OpenGL version is 3.0 or above. Possible values are
     * {@link GLFW#GLFW_TRUE} and {@link GLFW#GLFW_FALSE}. If OpenGL ES is requested, this hint is
     * ignored.
     */
    OPENGL_FORWARD_COMPAT(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_FALSE),
    /**
     * Specifies whether the context should be created in debug mode, which may provide additional
     * error and diagnostic reporting functionality. Possible values are {@link GLFW#GLFW_TRUE} and
     * {@link GLFW#GLFW_FALSE}.
     */
    OPENGL_DEBUG_CONTEXT(GLFW.GLFW_OPENGL_DEBUG_CONTEXT, GLFW.GLFW_FALSE),
    /**
     * Specifies which OpenGL profile to create the context for. Possible values are one of
     * {@link GLFW#GLFW_OPENGL_CORE_PROFILE}, {@link GLFW#GLFW_OPENGL_COMPAT_PROFILE}, or
     * {@link GLFW#GLFW_OPENGL_ANY_PROFILE} to not request a specific profile. If requesting an
     * OpenGL version below 3.2, {@link GLFW#GLFW_OPENGL_ANY_PROFILE} must be used. If another
     * client API is requested, this hint is ignored.
     */
    OPENGL_PROFILE(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_ANY_PROFILE);

    /**
     * The name.
     */
    private final int name;

    /**
     * The default value.
     */
    private final int defaultValue;

    /**
     * Creates a new {@code WindowHint} instance with the given name and default value.
     *
     * @param name         The name.
     * @param defaultValue The default value.
     */
    WindowHint(final int name, final int defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the name of this window hint.
     *
     * @return The name.
     */
    public int getName() {
        return name;
    }

    /**
     * Returns the default value of this window hint.
     *
     * @return The default value.
     */
    public int getDefaultValue() {
        return defaultValue;
    }
}
