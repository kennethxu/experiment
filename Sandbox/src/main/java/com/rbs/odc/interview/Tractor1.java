package com.rbs.odc.interview;

import java.awt.Point;
import java.awt.Rectangle;

public class Tractor1 implements Tractor {

	private Point position = new Point(0, 0);
	private Rectangle field = new Rectangle(-5, -5, 11, 11);
	private Orientation orientation = Orientation.NORTH;

	private static class Orientation {
		public static final Orientation NORTH = new Orientation(0, 1, "N", 1);
		public static final Orientation EAST = new Orientation(1, 0, "E", 2);
		public static final Orientation SOUTH = new Orientation(0, -1, "S", 3);
		public static final Orientation WEST = new Orientation(-1, 0, "W", 0);
		private static Orientation[] orientations = new Orientation[] { NORTH, EAST, SOUTH, WEST };
		final String name;
		final int xIncrement, yIncrement, clockwiseIndex;

		private Orientation(int xIncrement, int yIncrement, String name,
				int clockwiseIndex) {
			this.xIncrement = xIncrement;
			this.yIncrement = yIncrement;
			this.name = name;
			this.clockwiseIndex = clockwiseIndex;
		}

		public Orientation turnClockwise() {
			return orientations[clockwiseIndex];
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
		return orientation.name;
	}

}