package com.sabre.toolbox;

import org.joml.Vector3f;
import org.joml.Matrix4f;

import com.sabre.entities.Camera;

public class Maths {
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale) {
	    return new Matrix4f()
	            .identity()
	            .translate(translation)
	            .rotateX((float) Math.toRadians(rotation.x))
	            .rotateY((float) Math.toRadians(rotation.y))
	            .rotateZ((float) Math.toRadians(rotation.z))
	            .scale(scale);
	}

	public static Matrix4f createViewMatrix(Camera camera) {
	    Vector3f cameraPos = camera.getPosition();
	    Vector3f rotation = camera.getRotation();

	    return new Matrix4f()
	            .identity()
	            .rotateX((float) Math.toRadians(rotation.x))
	            .rotateY((float) Math.toRadians(rotation.y))
	            .translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
	}
	
	public static Vector3f addVectors(Vector3f a, Vector3f b) {
		return new Vector3f(a.x+b.x,a.y+b.y,a.z+b.z);
	}

}
