package application;

import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		
		Button btn = new Button();
		
		root.getChildren().add(btn);
		
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				x = 200;
				
			}
		});
		
		
		
		
		

		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
				Platform.exit();

			}
		});
		
		
	}

	static int x = 100;


	private synchronized static void displayIsle(Canvas canvas) {
		try {
			Display.setParent(canvas);
			// Display.setDisplayMode(new DisplayMode(800,600));

			Display.create();
			 // init OpenGL
		    GL11.glMatrixMode(GL11.GL_PROJECTION);
		    GL11.glLoadIdentity();
		    GL11.glOrtho(0, 800, 0, 600, 1, -1);
		    GL11.glMatrixMode(GL11.GL_MODELVIEW);
		  
		    while (!Display.isCloseRequested()) {
		        // Clear the screen and depth buffer
		        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
		         
		        // set the color of the quad (R,G,B,A)
		        GL11.glColor3f(0.5f,0.5f,1.0f);
		             
		        // draw quad
		        GL11.glBegin(GL11.GL_QUADS);
		            GL11.glVertex2f(x,x);
		        GL11.glVertex2f(x+200,x);
		        GL11.glVertex2f(x+200,x+200);
		        GL11.glVertex2f(x,x+200);
		        GL11.glEnd();
		  
		        Display.update();
		    }

		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		

	}

	public static void main(String[] args) {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(800, 600);
				Panel panel = new Panel();
				frame.add(panel);
				
				
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						displayIsle(panel.getCanvas());
						
					}
				});
				
			}
		});
		th.start();
	
		
		
		
		
	
		
		

		launch(args);
	}
}
