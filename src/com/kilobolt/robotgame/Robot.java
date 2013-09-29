package com.kilobolt.robotgame;


import java.util.ArrayList;

import android.graphics.Rect;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final double MAXSPEED = 10.0;

	private int centerX = 100;
	private int centerY = 377;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;

	private double speedX = 0;
	private double speedY = 0;
	
	private double accX = 0;
	private double accY = 0;
	
	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect rect3 = new Rect(0, 0, 0, 0);
	public static Rect rect4 = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);
	
	public static Rect footleft = new Rect(0,0,0,0);
	public static Rect footright = new Rect(0,0,0,0);
	
	
	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {
		// Moves Character or Scrolls Background accordingly.
		
		int delTime = 1; 
		speedX += accX * delTime;
		speedY += accY * delTime;
		

		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);

		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		else if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX((int) -speedX + 1);
			bg2.setSpeedX((int) -speedX + 1);
		}
		else {
			centerX += speedX;
		}

		// Updates Y Position
		centerY += speedY;

		// Handles Jumping
		speedY += 1;

		if (speedY > 3){
			jumped = true;
		}

		// Prevents going beyond X coordinate of 0
//		if (centerX + speedX <= 60) {
//			centerX = 61;
//		}

		rect.set(centerX - 34, centerY - 63, centerX + 34, centerY);
		rect2.set(rect.left, rect.top + 63, rect.left+68, rect.top + 128);
		rect3.set(rect.left - 26, rect.top+32, rect.left, rect.top+52);
		rect4.set(rect.left + 68, rect.top+32, rect.left+94, rect.top+52);
		yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);
		footleft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
		footright.set(centerX, centerY + 20, centerX+50, centerY+35);
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		checkDirs();
	}

	public void stopLeft() {
		setMovingLeft(false);
		checkDirs();
	}
	public void stop(char axis) {
		if (axis == 'x'){
			setAccX(0);
			setSpeedX(0);
		}
		else if (axis == 'y'){
			setAccY(0);
			setSpeedY(0);
		}
		
	}
	
	private void checkDirs() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}
	
	public double getAccX() {
		return accX;
	}

	public double getAccY() {
		return accY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = (double) speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = (double) speedY;
	}

	public void setAccX(double acceleration) {
		this.accX = acceleration;
	}

	public void setAccY(double acceleration) {
		this.accY = acceleration;
	}

	
	public void incrementSpeedX(double speedX) {
		this.speedX += speedX;
		if (speedX > MAXSPEED)
			speedX = MAXSPEED;
		if (speedX < -1 * MAXSPEED)
			speedX = -1 * MAXSPEED;
	}

	public void incrementSpeedY(double speedY) {
		this.speedY += speedY;
		if (speedY > MAXSPEED)
			speedY = MAXSPEED;
		if (speedY < -1 * MAXSPEED)
			speedY = -1 * MAXSPEED;
	}
	
	
	

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

}
