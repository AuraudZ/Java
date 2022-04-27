import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

import static com.jogamp.opengl.GL2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2.GL_VERTEX_SHADER;

public class ShaderData {
    public int theProgram;

    public int modelToCameraMatrixUnif;

    public int lightIntensityUnif;
    public int ambientIntensityUnif;

    public int normalModelToCameraMatrixUnif;
    public int cameraSpaceLightPosUnif;

    public int lightAttenuationUnif;
    public int shininessFactorUnif;
    public int baseDiffuseColorUnif;

    public ShaderData(GL2 gl3, String shaderRoot, String vertSrc, String fragSrc) {

        ShaderProgram shaderProgram = new ShaderProgram();

        ShaderCode vertShaderCode = ShaderCode.create(gl3, GL_VERTEX_SHADER, this.getClass(), shaderRoot, null,
                vertSrc, "vert", null, true);
        ShaderCode fragShaderCode = ShaderCode.create(gl3, GL_FRAGMENT_SHADER, this.getClass(), shaderRoot, null,
                fragSrc, "frag", null, true);

        shaderProgram.add(vertShaderCode);
        shaderProgram.add(fragShaderCode);

        shaderProgram.link(gl3, System.out);

        theProgram = shaderProgram.program();


        vertShaderCode.destroy(gl3);
        fragShaderCode.destroy(gl3);

        modelToCameraMatrixUnif = gl3.glGetUniformLocation(theProgram, "modelToCameraMatrix");
        lightIntensityUnif = gl3.glGetUniformLocation(theProgram, "lightIntensity");
        ambientIntensityUnif = gl3.glGetUniformLocation(theProgram, "ambientIntensity");

        normalModelToCameraMatrixUnif = gl3.glGetUniformLocation(theProgram, "normalModelToCameraMatrix");
        cameraSpaceLightPosUnif = gl3.glGetUniformLocation(theProgram, "cameraSpaceLightPos");

        lightAttenuationUnif = gl3.glGetUniformLocation(theProgram, "lightAttenuation");
        shininessFactorUnif = gl3.glGetUniformLocation(theProgram, "shininessFactor");
        baseDiffuseColorUnif = gl3.glGetUniformLocation(theProgram, "baseDiffuseColor");

    }
}
