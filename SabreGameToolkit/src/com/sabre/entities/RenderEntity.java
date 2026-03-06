package com.sabre.entities;

import org.joml.Vector3f;

import com.sabre.models.TexturedModel;

public class RenderEntity {
	
	private TexturedModel model;
	public Vector3f position;
	public Vector3f rotation;
	public Vector3f scale;
	
	public RenderEntity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x+= dx;
		this.position.y+=dy;
		this.position.z+=dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotation.x+= dx;
		this.rotation.y+=dy;
		this.rotation.z+=dz;
	}
	
}
