// 顶点着色器

attribute vec4 a_Position;  // vec4包含4个分量的向量 x y z w ---> 0 0 0 1

void main(){
    gl_Position = a_Position;
}