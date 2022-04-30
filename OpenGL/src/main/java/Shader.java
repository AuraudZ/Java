public class Shader {
    // This class compiles vertex and fragment shaders, and links them into a glProgram and returns it.
    // It also has a method to set uniforms.

    // The shader source code.
    private String vertexSource;
    private String fragmentSource;

    // The compiled shader program.
    private int glProgram;

    // The uniform locations.
    private int[] uniformLocations;

    // The attribute locations.
    private int[] attributeLocations;

    // The number of attributes.
    private int attributeCount;

    // The number of uniforms.
    private int uniformCount;

    public Shader(String vertexSource, String fragmentSource) {
        // Compiles the vertex and fragment shaders.
        this.vertexSource = vertexSource;
        this.fragmentSource = fragmentSource;
        compile();
    }
    public void compile() {
        // Compiles the vertex and fragment shaders.

    }
}
