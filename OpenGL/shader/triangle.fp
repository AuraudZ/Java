
in vec2 TexCoord;
out vec4 out_color;
uniform sampler2D tex;
void main() {

    out_color = texture(tex, gl_FragCoord.xy);
}