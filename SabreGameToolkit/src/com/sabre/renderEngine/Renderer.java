package com.sabre.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.sabre.entities.Entity;
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
	
	public void render(Entity entity, StaticShader shader) {
		TexturedModel texturedModel = entity.getModel();
		RawModel model = texturedModel.getRawModel();
		
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		ModelTexture texture = texturedModel.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);

		GL30.glBindVertexArray(0);
	}
	
	private void createProjectionMatrix() {
		float aspectRatio = (float) DisplayManager.getWidth() / (float) DisplayManager.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = far_plane - near_plane;


		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	
}
