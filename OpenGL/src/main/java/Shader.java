import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.GL4bc;
import com.jogamp.opengl.util.glsl.ShaderProgram;

import java.io.File;

public class Shader {
    public static int ID;

    GL4bc gl;
    public Shader(String vertexShader, String fragmentShader, GL4bc gl) {
        this.gl = gl;
        ShaderCode vertexShaderCode =   ShaderCode.create(
                gl,
                GL2ES2.GL_FRAGMENT_SHADER,
                this.getClass(),
                "shader",
                "shader/bin",
                vertexShader,
                true);
        ShaderCode fragmentShaderCode = ShaderCode.create(
                gl,
                GL2ES2.GL_FRAGMENT_SHADER,
                this.getClass(),
                "shader",
                "shader/bin",
                fragmentShader,
                true);
        vertexShaderCode.defaultShaderCustomization(gl, true, true);
        fragmentShaderCode.defaultShaderCustomization(gl, true, true);
        ShaderProgram shaderProgram = new ShaderProgram();
        shaderProgram.add(vertexShaderCode);
        shaderProgram.add(fragmentShaderCode);
        shaderProgram.link(gl, System.out);
        System.out.println(shaderProgram.inUse());
        ID = shaderProgram.program();
    }

    public void use() {
        gl.glUseProgram(ID);
    }

    public void setInt(String name, int value) {
        gl.glUniform1i(gl.glGetUniformLocation(ID, name), value);
    }

    public void setFloat(String name, float value) {
        gl.glUniform1f(gl.glGetUniformLocation(ID, name), value);
    }

    public void setVec3(String name, float x, float y, float z) {
        gl.glUniform3f(gl.glGetUniformLocation(ID, name), x, y, z);
    }

    public void setVec3(String name, float[] value) {
        gl.glUniform3f(gl.glGetUniformLocation(ID, name), value[0], value[1], value[2]);
    }

    public void setVec4(String name, float x, float y, float z, float w) {
        gl.glUniform4f(gl.glGetUniformLocation(ID, name), x, y, z, w);
    }

    public int getProgram() {
        return ID;
    }
}

