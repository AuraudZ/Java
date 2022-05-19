
in vec2 TexCoord;
out vec4 out_color;
uniform sampler2D tex;
uniform sampler2D face;


float near = 0.1;
float far  = 100.0;


float LinearizeDepth(float depth)
{
    float z = depth * 2.0 - 1.0; // back to NDC
    return (2.0 * near * far) / (far + near - z * (far - near));
}

void main() {

   float depth =  LinearizeDepth(gl_FragCoord.z) / far;


    //out_color = vec4(vec3(depth), 1.0);
    out_color = mix(texture(tex, TexCoord), texture(face, TexCoord), 0.2);
}