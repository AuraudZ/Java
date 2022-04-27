#version 120

in vec3 position;
in vec3 normal;
in vec2 uv;

out vec3 vNormal;
out vec2 vUv;

void main() {
    gl_Position = vec4(position, 1.0);
    vNormal = normal;
    vUv = uv;
}
