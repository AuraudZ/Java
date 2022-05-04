#if __VERSION__ >= 130
#define varying in
out vec4 mgl_FragColor;
#else
#define mgl_FragColor gl_FragColor
#endif
uniform float iGlobalTime;
varying vec4 frontColor;

void main (void)
{
    vec4 color = sin(iGlobalTime) * frontColor;
    mgl_FragColor = color;
}