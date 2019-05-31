package com.byrfb.lwjgl;

import com.byrfb.lwjgl.interfaces.IGame;

import static org.lwjgl.opengl.GL11.*;

public class Game implements IGame {

	public static int x = 0;

	@Override
	public void create() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL_MODELVIEW);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0f, 1f,0f, 1f);
		// set the color of the quad (R,G,B,A)
		glColor3f(1.0f, 1.0f, 1.0f);

		// draw quad
		glBegin(GL_QUADS);
		glColor3f(1.0f, 0.0f, 0.0f);
		glVertex2f(x, x + 20);
		glVertex2f(x + 200, x + 20);
		glVertex2f(x + 200, x + 200);
		glVertex2f(x, x + 200);

		glEnd();
//		System.out.println(x);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
