package com.sharneng.interview.meetingscheduler;

import java.time.Instant;

public class TimeSlot {
	private Instant startTime;
	private Instant endTime;
	
	public TimeSlot(Instant startTime, Instant endTime) {
		// TODO null check
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		// TODO null check
		this.startTime = startTime;
	}
	
	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		// TODO null check
		this.endTime = endTime;
	}
}
