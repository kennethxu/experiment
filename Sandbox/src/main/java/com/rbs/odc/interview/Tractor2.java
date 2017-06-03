package com.rbs.odc.interview;

import java.awt.Point;
import java.awt.Rectangle;

public class Tractor2 implements Tractor {

	private Point position = new Point(0, 0);
	private Rectangle field = new Rectangle(-5, -5, 11, 11);
	private Orientation orientation = Orientation.North;

	private static enum Orientation {
		North(0, 1, "N"), East(1, 0, "E"), South(0, -1, "S"), West(-1, 0, "W");

		static {
			North.clockwiseDirection = East;
			East.clockwiseDirection = South;
			South.clockwiseDirection = West;
			West.clockwiseDirection = North;
		}

		private Orientation clockwiseDirection;
		final int xIncrement;
		final int yIncrement;
		final String shortName;

		private Orientation(int xIncrement, int yIncrement, String shortName) {
			this.xIncrement = xIncrement;
			this.yIncrement = yIncrement;
			this.shortName = shortName;
		}

		Orientation turnClockwise() {
			return clockwiseDirection;
		}
	}

	public void move(String command) {
		if ("F".equals(command)) {
			moveForwards();
		} else if ("T".equals(command)) {
			turnClockwise();
		}
	}

	private void moveForwards() {
		position.translate(orientation.xIncrement, orientation.yIncrement);
		if (!field.contains(position)) {
			position.translate(-orientation.xIncrement, -orientation.yIncrement);
			throw new TractorInDitchException();
		}
	}

	private void turnClockwise() {
		orientation = orientation.turnClockwise();
	}

	public int getPositionX() {
		return position.x;
	}

	public int getPositionY() {
		return position.y;
	}

	public String getOrientation() {
		return orientation.shortName;
	}

}