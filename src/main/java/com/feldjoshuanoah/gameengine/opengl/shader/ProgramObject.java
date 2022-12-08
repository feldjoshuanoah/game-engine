package com.feldjoshuanoah.gameengine.opengl.shader;

import com.feldjoshuanoah.gameengine.math.matrix.Matrix2f;
import com.feldjoshuanoah.gameengine.math.matrix.Matrix3f;
import com.feldjoshuanoah.gameengine.math.matrix.Matrix4f;
import com.feldjoshuanoah.gameengine.math.vector.*;
import com.feldjoshuanoah.gameengine.opengl.GLSLObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * A program object is a GLSL object to which shader objects can be attached. This provides a
 * mechanism to specify the shader objects that will be linked to create a program. It also provides
 * a means for checking the compatibility of the shaders that will be used to create a program (for
 * instance, checking the compatibility between a vertex shader and a fragment shader). When no
 * longer need as part of a program object, shader objects can be detached. One or more executables
 * are created in a program object by successfully attaching shader objects with
 * {@link #attach(ShaderObject)}, successfully compiling the shader objects with
 * {@link ShaderObject#compile()}, and successfully linking the program object with {@link #link()}.
 * The executables are made part of current state when {@link #use()} is called. Program objects can
 * be deleted by calling {@link #delete()}. The memory associated with the program object will be
 * deleted when it is no longer part of current rendering state for any context.
 */
public class ProgramObject implements GLSLObject {

    /**
     * The program object name.
     */
    private int name;

    /**
     * Creates an empty program object and sets the name by which it can be referenced.
     */
    @Override
    public void create() {
        name = GL20.glCreateProgram();
    }

    /**
     * Frees the memory and invalidates the name associated with this program object. This
     * effectively undoes the effects of a call to {@link #create()}. If a program object is in use
     * as part of current rendering state, it will be flagged for deletion, but it will not be
     * deleted until it is no longer part of current state for any rendering context. If a program
     * object to be deleted has shader objects attached to it, those shader objects will be
     * automatically detached but not deleted unless they have already been flagged for deletion by
     * a previous call to {@link ShaderObject#delete()}. To determine whether a program object has
     * been flagged for deletion, call {@link #getDeleteStatus()}.
     */
    @Override
    public void delete() {
        GL20.glDeleteProgram(name);
    }

    /**
     * Attaches the given shader object to this program object. Shaders that are to be linked
     * together in a program object must first be attached to that program object. This indicates
     * that the shader will be included in link operations that will be performed on the program.
     * All operations that can be performed on a shader object are valid whether or not the shader
     * object is attached to a program object. It is permissible to attach a shader object to a
     * program object before source code has been loaded into the shader object or before the shader
     * object has been compiled. It is permissible to attach multiple shader objects of the same
     * type because each may contain a portion of the complete shader. It is also permissible to
     * attach a shader object to more than one program object. If a shader object is deleted while
     * it is attached to a program object, it will be flagged for deletion, and deletion will not
     * occur until {@link #detach(ShaderObject)} is called to detach it from all program objects to
     * which it is attached.
     *
     * @param shader The shader object to attach.
     */
    public void attach(final ShaderObject shader) {
        GL20.glAttachShader(name, shader.getName());
    }

    /**
     * Detaches the given shader object from this program object. This can be used to undo the
     * effect of {@link #attach(ShaderObject)}.
     *
     * @param shader The shader object to detach.
     */
    public void detach(final ShaderObject shader) {
        GL20.glDetachShader(name, shader.getName());
    }

    /**
     * Links this program object. A shader object of type {@link GL20#GL_VERTEX_SHADER} attached to
     * the program is used to create an executable that will run on the programmable vertex
     * processor. A shader object of type {@link GL20#GL_FRAGMENT_SHADER} attached to the program is
     * used to create an executable that will run on the programmable fragment processor. The status
     * of the link operation will be stored as part of the program object's state. This value will
     * be set to {@code true} if the program object was linked without errors and is ready to use,
     * and {@code false} otherwise. It can be queried using {@link #getLinkStatus()}.
     * As a
     * result of a successful link operation, all active user-defined uniform variables belonging to
     * the program will be initialized to {@code 0}, and each of the program object's active uniform
     * variables will be assigned a location that can be queried by calling
     * {@link #getUniformLocation(String)}. Linking a program object can fail for a number of
     * reasons as specified in the OpenGL Shading Language Specification. The following lists some
     * of the conditions that will cause a link error.
     * <ul>
     *     <li>The storage limit for uniform variables has been exceeded.</li>
     *     <li>The {@code main} function is missing for the vertex or fragment shader.</li>
     *     <li>A varying variable actually used in the fragment shader is not declared in the same
     *     way (or not declared at all) in the vertex shader.</li>
     *     <li>A reference to a function or variable name is unresolved.</li>
     *     <li>A shared global is declared with two different types or two different initial
     *     values.</li>
     *     <li>One or more of the attached shader objects have not been successfully compiled.</li>
     *     <li>The program object contains objects to form a fragment shader but does not contain
     *     objects to form a vertex shader.</li>
     *     <li>More than one varying out variable is bound to the same number and index.</li>
     * </ul>
     * When a program object has been successfully linked, the program object can be made part of
     * current state by calling {@link #use()}. Whether or not the link operation was successful,
     * the program object's information log will be overwritten. The information log can be
     * retrieved by calling {@link #getInfoLog()}. This method will also install the generated
     * executables as part of the current rendering state if the link operation was successful and
     * the program object is already currently used as a result of a previous {@link #use()}. If the
     * program object currently in use is relinked unsuccessfully, its link status will be set to
     * {@code false}, but the executables and associated state will remain part of the current state
     * until a subsequent call to {@link #use()} removes it from use. After it is removed from use,
     * it cannot be made part of current state until it has been successfully relinked. The program
     * object's information log is updated and the program is generated at the time of the link
     * operation. After the link operation, applications are free to modify attached shader objects,
     * compile attached shader objects, detach shader objects, delete shader objects, and attach
     * additional shader objects. None of these operations affect the information log or the program
     * that is part of the program object.
     */
    public void link() {
        GL20.glLinkProgram(name);
    }

    /**
     * Installs this program object as part of the current rendering state. One or more executables
     * are created in a program object by successfully attaching shader objects to it with
     * {@link #attach(ShaderObject)}, successfully compiling the shader objects with
     * {@link ShaderObject#compile()}, and successfully linking the program object with
     * {@link #link()}. A program object will contain an executable that will run on the vertex
     * processor if it contains one or more shader objects of type {@link GL20#GL_VERTEX_SHADER}
     * that have been successfully compiled and linked. Similarly, a program object will contain an
     * executable that will run on the fragment processor if it contains one or more shader objects
     * of type {@link GL20#GL_FRAGMENT_SHADER} that have been successfully compiled and linked.
     * While a program object is in use, applications are free to modify attached shader objects,
     * compile attached shader objects, attach additional shader objects, and detach or delete
     * shader objects. None of these operations will affect the executables that are part of the
     * current state. However, relinking the program object that is currently in use will install
     * the program object as part of the current rendering state if the link operation was
     * successful (see {@link #link()}). If the program object currently in use is relinked
     * unsuccessfully, its link status will be set to {@code false}, but the executables and
     * associated state will remain part of the current state until a subsequent call to
     * {@code use()} removes it from use. After it is removed from use, it cannot be made part of
     * current state until it has been successfully relinked.
     */
    public void use() {
        GL20.glUseProgram(name);
    }

    /**
     * Checks to see whether the executables contained in this program object can execute given the
     * current OpenGL state. The information generated by the validation process will be stored in
     * the program object's information log. The validation information may consist of an empty
     * string, or it may be a string containing information about how the current program object
     * interacts with the rest of current OpenGL state. This provides a way for OpenGL implementers
     * to convey more information about why the current program is inefficient, suboptimal, failing
     * to execute, and so on.
     *
     * <p>The status of the validation operation will be stored as part of the program object's
     * state. This value will be set to {@code true} if the validation succeeded, and {@code false}
     * otherwise. It can be queried by calling {@link #getValidateStatus()}. If validation is
     * successful, the program object is guaranteed to execute given the current state. Otherwise,
     * the program object is guaranteed to not execute.
     *
     * <p>This function is typically useful only during application development. The informational
     * string stored in the information log is completely implementation dependent; therefore, an
     * application should not expect different OpenGL implementations to produce identical
     * information strings.
     */
    public void validate() {
        GL20.glValidateProgram(name);
    }

    /**
     * Returns the location of a specific uniform variable within this program object. This function
     * returns {@code -1} if the given name does not correspond to an active uniform variable in the
     * program object, if it starts with the reserved prefix {@code gl_}, or if the name is
     * associated with an atomic counter or a named uniform block. Uniform variables that are
     * structures or arrays of structures may be queried by calling
     * {@code getUniformLocation(String)} for each field within the structure. The array element
     * operator {@code []} and the structure field operator {@code .} may be used in the name in
     * order to select elements within an array or fields within a structure. The result of using
     * these operators is not allowed to be another structure, an array of structures, or a
     * subcomponent of a vector or a matrix. Except if the last part of the name indicates a uniform
     * variable array, the location of the first element of an array can be retrieved by using the
     * name of the array, or by using the name appended by {@code [0]}. The actual locations
     * assigned to uniform variables are not known until the program object is linked successfully.
     * After linking has occurred, the function {@code getUniformLocation(String)} can be used to
     * obtain the location of a uniform variable. After a program object has been linked
     * successfully, the index values for uniform variables remain fixed until the next link command
     * occurs. Uniform variable locations and values can only be queried after a link if the link
     * was successful.
     *
     * @param  name The name of the uniform variable whose location to query. It must be an active
     *              uniform variable name in the program that is not a structure, an array of
     *              structures, or a subcomponent of a vector or a matrix.
     * @return      The location of the uniform variable.
     */
    public int getUniformLocation(final String name) {
        return GL20.glGetUniformLocation(this.name, name);
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform1f(final String name, final float value) {
        GL20.glUniform1f(getUniformLocation(name), value);
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform1i(final String name, final int value) {
        GL20.glUniform1i(getUniformLocation(name), value);
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform1ui(final String name, final int value) {
        GL30.glUniform1ui(getUniformLocation(name), value);
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform2f(final String name, final Vector2f value) {
        GL20.glUniform2f(getUniformLocation(name), value.getX(), value.getY());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform2i(final String name, final Vector2i value) {
        GL20.glUniform2i(getUniformLocation(name), value.getX(), value.getY());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform2ui(final String name, final Vector2i value) {
        GL30.glUniform2ui(getUniformLocation(name), value.getX(), value.getY());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform3f(final String name, final Vector3f value) {
        GL20.glUniform3f(getUniformLocation(name), value.getX(), value.getY(), value.getZ());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform3i(final String name, final Vector3i value) {
        GL20.glUniform3i(getUniformLocation(name), value.getX(), value.getY(), value.getZ());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform3ui(final String name, final Vector3i value) {
        GL30.glUniform3ui(getUniformLocation(name), value.getX(), value.getY(), value.getZ());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform4f(final String name, final Vector4f value) {
        GL20.glUniform4f(getUniformLocation(name), value.getX(), value.getY(), value.getZ(),
                value.getW());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform4i(final String name, final Vector4i value) {
        GL20.glUniform4i(getUniformLocation(name), value.getX(), value.getY(), value.getZ(),
                value.getW());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniform4ui(final String name, final Vector4i value) {
        GL30.glUniform4ui(getUniformLocation(name), value.getX(), value.getY(), value.getZ(),
                value.getW());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniformMatrix2f(final String name, final Matrix2f value) {
        GL20.glUniformMatrix2fv(getUniformLocation(name), false, value.toArray());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniformMatrix3f(final String name, final Matrix3f value) {
        GL30.glUniformMatrix3fv(getUniformLocation(name), false, value.toArray());
    }

    /**
     * Modifies the value of a uniform variable or a uniform variable array. The name of the
     * uniform variable to be modified is specified by the given name. It operates on the program
     * object that was made part of the current state by calling {@link #use()}.
     *
     * <p>The commands {@code uniform{1|2|3|4}{f|i|ui}} are used to change the value of the uniform
     * variable specified by the given name using the values passed as arguments. The number
     * specified in the command should match the number of components in the data type of the
     * specified uniform variable (e.g., {@code 1} for {@code float}, {@code int},
     * {@code unsigned int}, {@code bool}; {@code 2} for {@code vec2}, {@code ivec2}, {@code uvec2},
     * {@code bvec2}, etc.). The suffix {@code f} indicates that floating-point values are being
     * passed; the suffix {@code i} indicates that integer values are being passed; the suffix
     * {@code ui} indicates that unsigned integer values are being passed, and this type should also
     * match the data type of the specified uniform variable. The {@code i} variants of this
     * function should be used to provide values for uniform variables defined as {@code int},
     * {@code ivec2}, {@code ivec3}, {@code ivec4}, or arrays of these. The {@code ui} variants of
     * this function should be used to provide values for uniform variables defined as
     * {@code unsigned int}, {@code uvec2}, {@code uvec3}, {@code uvec4}, or arrays of these. The
     * {@code f} variants should be used to provide values for uniform variables of type
     * {@code float}, {@code vec2}, {@code vec3}, {@code vec4}, or arrays of these. Either the
     * {@code i}, {@code ui}, or {@code f} variants may be used to provide values for uniform
     * variables of type {@code bool}, {@code bvec2}, {@code bvec3}, {@code bvec4}, or arrays of
     * these. The uniform variable will be set to {@code false} if the input variable is {@code 0}
     * or {@code 0.0f}, and it will be set to {@code true} otherwise.
     *
     * <p>All active uniform variables defined in a program object are initialized to {@code 0} when
     * the program object is linked successfully. They retain the values assigned to them by a call
     * to {@code uniform} until the next successful link operation occurs on the program object,
     * when they are once again initialized to {@code 0}.
     *
     * <p>The commands {@code uniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}f} are used to modify a
     * matrix or an array of matrices. The numbers in the command name are interpreted as the
     * dimensionality of the matrix. The number {@code 2} indicates a 2x2 matrix (i.e., 4 values),
     * the number {@code 3} indicates a 3x3 matrix (i.e., 9 values), and the number {@code 4}
     * indicates a 4x4 matrix (i.e., 16 values). Non-square matrix dimensionality is explicit, with
     * the first number representing the number of columns and the second number representing the
     * number of rows. For example, {@code 2x4} indicates a 2x4 matrix with 2 columns and 4 rows
     * (i.e., 8 values).
     *
     * @param name  The name of the uniform variable to modify.
     * @param value The desired value to use for the specified uniform variable.
     */
    public void uniformMatrix4f(final String name, final Matrix4f value) {
        GL30.glUniformMatrix4fv(getUniformLocation(name), false, value.toArray());
    }

    /**
     * Returns the information log for this program object. The information log for a program object
     * is modified when the program object is linked or validated. The information log for a program
     * object is either a empty string, or a string containing information about the last link
     * operation, or a string containing information about the last validation operation. It may
     * contain diagnostic messages, warning messages, and other information. When a program object
     * is created, its information log will be a string of length {@code 0}.
     *
     * @return The information log.
     */
    public String getInfoLog() {
        return GL20.glGetProgramInfoLog(name);
    }

    /**
     * Returns {@code true} if this program object is currently flagged for deletion, and
     * {@code false} otherwise.
     *
     * @return {@code true} if this program object is currently flagged for deletion.
     */
    public boolean getDeleteStatus() {
        return GL20.glGetProgrami(name, GL20.GL_DELETE_STATUS) == GL11.GL_TRUE;
    }

    /**
     * Returns {@code true} if the last link operation on this program object was successful, and
     * {@code false} otherwise.
     *
     * @return {@code true} if the last link operation was successful.
     */
    public boolean getLinkStatus() {
        return GL20.glGetProgrami(name, GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
    }

    /**
     * Returns {@code true} if the last validation operation on this program object was successful,
     * and {@code false} otherwise.
     *
     * @return {@code true} if the last validation operation was successful.
     */
    public boolean getValidateStatus() {
        return GL20.glGetProgrami(name, GL20.GL_VALIDATE_STATUS) == GL11.GL_TRUE;
    }

    /**
     * Returns the number of characters in the information log for this program object including the
     * null termination character (i.e., the size of the character buffer required to store the
     * information log). If the program object has no information log, a value of {@code 0} is
     * returned.
     *
     * @return The number of characters in the information log.
     */
    public int getInfoLogLength() {
        return GL20.glGetProgrami(name, GL20.GL_INFO_LOG_LENGTH);
    }

    /**
     * Returns the number of shader objects attached to the program object.
     *
     * @return The number of attached shader objects.
     */
    public int getAttachedShaders() {
        return GL20.glGetProgrami(name, GL20.GL_ATTACHED_SHADERS);
    }
}
