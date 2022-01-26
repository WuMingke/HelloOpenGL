// 片段着色器

precision mediump float; // mediump 精度：lowp mediump highp
uniform vec4 u_Color;

void main(){
    gl_FragColor = u_Color;
}