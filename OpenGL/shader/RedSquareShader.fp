#if __VERSION__ >= 130
#define varying in
out vec4 mgl_FragColor;
#else
#define mgl_FragColor gl_FragColor
#endif
uniform float iGlobalTime;
varying vec4 frontColor;
uniform sampler2D equirectangularMap;

float rand(vec2 co){
    return fract(sin(dot(co, vec2(12.9898, 78.233))) * 43758.5453);
}
const vec2 invAtan = vec2(0.1591, 0.3183);
vec2 SampleSphericalMap(vec3 v)
{
    vec2 uv = vec2(atan(v.z, v.x), asin(v.y));
    uv *= invAtan;
    uv += 0.5;
    return uv;
}

void main (void)
{
    vec2 uv = SampleSphericalMap(normalize(localPos)); // make sure to normalize localPos
    vec3 color = texture(equirectangularMap, uv).rgb;
    FragColor = vec4(color, 1.0);
    mgl_FragColor = FragColor;
}