package com.sabre.toolbox;

public class Vector3f {
    public float x, y, z;

    // Constructors
    public Vector3f() {
        this(0, 0, 0);
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f other) {
        this(other.x, other.y, other.z);
    }

    // Set components
    public Vector3f set(float x, float y, float z) {
        this.x = x; this.y = y; this.z = z;
        return this;
    }

    public Vector3f set(Vector3f other) {
        return set(other.x, other.y, other.z);
    }

    // Add
    public Vector3f add(Vector3f other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector3f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    // Subtract
    public Vector3f subtract(Vector3f other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public Vector3f subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    // Multiply component-wise
    public Vector3f multiply(Vector3f other) {
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;
        return this;
    }

    // Multiply by scalar
    public Vector3f scale(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    // Divide component-wise
    public Vector3f divide(Vector3f other) {
        this.x /= other.x;
        this.y /= other.y;
        this.z /= other.z;
        return this;
    }

    // Divide by scalar
    public Vector3f divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    // Length / Magnitude
    public float length() {
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

    // Normalize
    public Vector3f normalize() {
        float len = length();
        if (len != 0) scale(1.0f / len);
        return this;
    }

    // Dot product
    public float dot(Vector3f other) {
        return x*other.x + y*other.y + z*other.z;
    }

    // Cross product
    public Vector3f cross(Vector3f other) {
        float cx = y*other.z - z*other.y;
        float cy = z*other.x - x*other.z;
        float cz = x*other.y - y*other.x;
        return set(cx, cy, cz);
    }

    @Override
    public String toString() {
        return "Vector3f(" + x + ", " + y + ", " + z + ")";
    }
}