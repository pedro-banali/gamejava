package jogo.recursosexternos;

public class Camera {

	private static Camera instance;
	private double xOffset, yOffset;
	private Camera() {
	}
	
	public static Camera getInstance() {
		if(instance == null)
			instance = new Camera();
		
		return instance;
	}

	public double getxOffset() {
		return xOffset;
	}

	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public double getyOffset() {
		return yOffset;
	}

	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}
}
