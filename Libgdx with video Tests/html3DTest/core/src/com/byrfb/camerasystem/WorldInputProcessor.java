package com.byrfb.camerasystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WorldInputProcessor implements InputProcessor {

		private final Ray dragCurrentRay = new Ray();
		private final Ray lastDragProcessedRay = new Ray();
		private final Ray touchDownRay = new Ray();
		private final Ray touchUpRay = new Ray();
		private final Ray movedRay = new Ray();

		private final Vector2 lastDragProcessed = new Vector2();
		private final Vector2 cursorDelta = new Vector2();
		private final Vector2 dragCurrent = new Vector2();
		private final Vector2 keyPanDirection = new Vector2();
		private final Vector2 lastTouchDown = new Vector2();

		private final Vector3 tmp = new Vector3();
		private final Vector3 mouseNavMeshPos = new Vector3();
		private final Vector2 mouseScreenPos = new Vector2();


		private final IntIntMap keys = new IntIntMap();

		private boolean isDragging = false;

		private CameraController cameraController;

	public WorldInputProcessor(CameraController cameraController, Viewport viewport) {
		this.cameraController = cameraController;
		this.viewport = viewport;
	}

	private Viewport viewport;

		@Override
		public boolean keyDown(int keycode) {
			keys.put(keycode, keycode);

			return true;
		}

		@Override
		public boolean keyUp(int keycode) {
			keys.remove(keycode, 0);
			return true;
		}

		@Override
		public boolean keyTyped(char keyChar) {

			return true;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			lastTouchDown.set(screenX, screenY);
			lastDragProcessed.set(screenX, screenY);
			if (button == Input.Buttons.LEFT) {
				touchDownRay.set(viewport.getPickRay(screenX, screenY));
				cameraController.processTouchDownLeft(touchDownRay);

			} else if (button == Input.Buttons.RIGHT) {
				cameraController.processTouchDownRight();
			}
			return true;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			if (!isDragging) {
				touchUpRay.set(viewport.getPickRay(screenX, screenY));


			}
			isDragging = false;
			dragCurrent.setZero();
			lastDragProcessed.setZero();
			return true;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			dragCurrent.set(screenX, screenY);
			if (dragCurrent.dst2(lastTouchDown) > GameSettings.MOUSE_DRAG_THRESHOLD) {
				isDragging = true;
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					dragCurrentRay.set(viewport.getPickRay(dragCurrent.x, dragCurrent.y));
					lastDragProcessedRay.set(viewport.getPickRay(lastDragProcessed.x, lastDragProcessed.y));
					cameraController.processDragPan(dragCurrentRay, lastDragProcessedRay);

				} else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
					cursorDelta.set(lastDragProcessed).sub(screenX, screenY).scl(GameSettings.MOUSE_SENSITIVITY);

					cameraController.processDragRotation(cursorDelta);
				}
				lastDragProcessed.set(screenX, screenY);
			}
			mouseMoved(screenX, screenY);
			return true;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {

			mouseScreenPos.set(screenX, screenY);
			return true;
		}

		@Override
		public boolean scrolled(int amount) {
			cameraController.processZoom(amount);
			return true;
		}

		public void update(float deltaTime) {
			keyPanDirection.setZero();
			if (keys.containsKey(GameSettings.KEY_PAN_FORWARD)) {
				keyPanDirection.y++;
			}
			if (keys.containsKey(GameSettings.KEY_PAN_BACKWARD)) {
				keyPanDirection.y--;
			}
			if (keys.containsKey(GameSettings.KEY_PAN_LEFT)) {
				keyPanDirection.x--;
			}
			if (keys.containsKey(GameSettings.KEY_PAN_RIGHT)) {
				keyPanDirection.x++;
			}
			if (!keyPanDirection.isZero()) {
				keyPanDirection.nor();
				cameraController.processKeyboardPan(keyPanDirection, deltaTime);
			}
		}
	}
