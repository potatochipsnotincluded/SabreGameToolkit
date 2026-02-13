package com.sabre.entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	
	private Vector3f position;
	private Vector3f colour;
	private float ambient;
	
	public Light(Vector3f position, Vector3f colour, float ambient) {
		this.position = position;
		this.colour = colour;
		this.ambient = ambient;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getColour() {
		return colour;
	}

	public void setColour(Vector3f colour) {
		this.colour = colour;
	}

	public float getAmbient() {
		return ambient;
	}

	public void setAmbient(float ambient) {
		this.ambient = ambient;
	}
	
}
