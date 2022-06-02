
in vec2 TexCoord;
out vec4 out_color;
uniform sampler2D tex;
uniform sampler2D face;


void main() {

    out_color = mix(texture(tex, TexCoord), texture(face, TexCoord), 0.2);
}