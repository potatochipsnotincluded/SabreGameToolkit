package com.sabre.toolbox;

public class Matrix4f {

    public float m00, m01, m02, m03;
    public float m10, m11, m12, m13;
    public float m20, m21, m22, m23;
    public float m30, m31, m32, m33;

    public Matrix4f() { setIdentity(); }

    public void setIdentity() {
        m00 = 1; m01 = 0; m02 = 0; m03 = 0;
        m10 = 0; m11 = 1; m12 = 0; m13 = 0;
        m20 = 0; m21 = 0; m22 = 1; m23 = 0;
        m30 = 0; m31 = 0; m32 = 0; m33 = 1;
    }

    // --------------------
    // Static operations
    // --------------------

    public static void translate(Vector3f vec, Matrix4f src, Matrix4f dest) {
        dest.set(src);
        dest.m30 += vec.x;
        dest.m31 += vec.y;
        dest.m32 += vec.z;
    }

    public static void scale(Vector3f vec, Matrix4f src, Matrix4f dest) {
        dest.set(src);
        dest.m00 *= vec.x;
        dest.m11 *= vec.y;
        dest.m22 *= vec.z;
    }

    public static void rotate(float angleRad, Vector3f axis, Matrix4f src, Matrix4f dest) {
        float c = (float)Math.cos(angleRad);
        float s = (float)Math.sin(angleRad);
        float t = 1 - c;
        axis = new Vector3f(axis).normalize();

        float x = axis.x, y = axis.y, z = axis.z;

        // rotation matrix components
        float rm00 = t*x*x + c;
        float rm01 = t*x*y + s*z;
        float rm02 = t*x*z - s*y;
        float rm10 = t*x*y - s*z;
        float rm11 = t*y*y + c;
        float rm12 = t*y*z + s*x;
        float rm20 = t*x*z + s*y;
        float rm21 = t*y*z - s*x;
        float rm22 = t*z*z + c;

        // multiply src by rotation matrix, store in dest
        dest.m00 = src.m00*rm00 + src.m10*rm01 + src.m20*rm02;
        dest.m01 = src.m01*rm00 + src.m11*rm01 + src.m21*rm02;
        dest.m02 = src.m02*rm00 + src.m12*rm01 + src.m22*rm02;
        dest.m03 = src.m03*rm00 + src.m13*rm01 + src.m23*rm02;

        dest.m10 = src.m00*rm10 + src.m10*rm11 + src.m20*rm12;
        dest.m11 = src.m01*rm10 + src.m11*rm11 + src.m21*rm12;
        dest.m12 = src.m02*rm10 + src.m12*rm11 + src.m22*rm12;
        dest.m13 = src.m03*rm10 + src.m13*rm11 + src.m23*rm12;

        dest.m20 = src.m00*rm20 + src.m10*rm21 + src.m20*rm22;
        dest.m21 = src.m01*rm20 + src.m11*rm21 + src.m21*rm22;
        dest.m22 = src.m02*rm20 + src.m12*rm21 + src.m22*rm22;
        dest.m23 = src.m03*rm20 + src.m13*rm21 + src.m23*rm22;

        // copy last row
        dest.m30 = src.m30; dest.m31 = src.m31; dest.m32 = src.m32; dest.m33 = src.m33;
    }

    // --------------------
    // Helper
    // --------------------
    public void set(Matrix4f other) {
        this.m00=other.m00; this.m01=other.m01; this.m02=other.m02; this.m03=other.m03;
        this.m10=other.m10; this.m11=other.m11; this.m12=other.m12; this.m13=other.m13;
        this.m20=other.m20; this.m21=other.m21; this.m22=other.m22; this.m23=other.m23;
        this.m30=other.m30; this.m31=other.m31; this.m32=other.m32; this.m33=other.m33;
    }
}