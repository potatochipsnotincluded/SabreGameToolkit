package com.sabre.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.sabre.entities.RenderEntity;
import com.sabre.models.RawModel;
import com.sabre.models.TexturedModel;
import com.sabre.shaders.StaticShader;
import com.sabre.textures.ModelTexture;
import com.sabre.toolbox.Maths;

public class Renderer {
	
	private static float fov;
	private static float near_plane;
	private static float far_plane;
	
	private Matrix4f projectionMatrix;
	
	public Renderer(StaticShader shader, float fov, float near_plane, float far_plane) {
		this.fov = fov;
		this.near_plane = near_plane;
		this.far_plane = far_plane;
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare(float r, float g, float b) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		GL11.glClearColor(r,g,b,1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void render(RenderEntity RenderEntity, StaticShader shader) {
		TexturedModel texturedModel = RenderEntity.getModel();
		RawModel model = texturedModel.getRawModel();
		
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(RenderEntity.getPosition(), RenderEntity.getRotation(), RenderEntity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		ModelTexture texture = texturedModel.getTexture();
		shader.loadMaterialVariables(texture.getMetallic(), texture.getSmoothness());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);

		GL30.glBindVertexArray(0);
	}
	
	private void createProjectionMatrix() {
	    float aspectRatio = (float) DisplayManager.getWidth() / DisplayManager.getHeight();

	    projectionMatrix = new Matrix4f().perspective(
	            (float) Math.toRadians(fov),
	            aspectRatio,
	            near_plane,
	            far_plane
	    );
	}
	
}
