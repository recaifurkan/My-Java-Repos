package com.byrfb.camerasystem;

import com.badlogic.gdx.Input;

public class GameSettings {

	public static final float CAMERA_FOV = 60;
	public static final float CAMERA_FAR = 100f;
	public static final float CAMERA_NEAR = 0.1f;
	public static final float CAMERA_MAX_PAN_VELOCITY = 50;
	public static final float CAMERA_MIN_ZOOM = 1;
	public static final float CAMERA_ZOOM_STEP = 4;
	public static final float CAMERA_MAX_ZOOM = 40;
	public static final float CAMERA_LERP_ALPHA = 5f;

	public static final float CAMERA_PICK_RAY_DST = 100;

	public static float MOUSE_SENSITIVITY = 0.1f;
	public static float MOUSE_DRAG_THRESHOLD = 10f;

	public static int KEY_PAUSE = Input.Keys.SPACE;
	public static int KEY_PAN_FORWARD = Input.Keys.W;
	public static int KEY_PAN_LEFT = Input.Keys.A;
	public static int KEY_PAN_BACKWARD = Input.Keys.S;
	public static int KEY_PAN_RIGHT = Input.Keys.D;
}