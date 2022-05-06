#if __VERSION__ >= 130
#define varying in
out vec4 mgl_FragColor;
#else
#define mgl_FragColor gl_FragColor
#endif
uniform float iGlobalTime;
varying vec4 frontColor;
float near = 0.1;
float far  = 100.0;

float LinearizeDepth(float depth)
{
    float z = depth * 2.0 - 1.0; // back to NDC
    return (2.0 * near * far) / (far + near - z * (far - near));
}

void main()
{
   // float depth = LinearizeDepth(gl_FragCoord.z) / far;
    mgl_FragColor = vec4(1,1,1, 1.0);
}