package com.sharneng.interview.meetingscheduler;

import java.time.Instant;

public class Meeting extends TimeSlot {
	private String subject;
	private String location;
	
	public Meeting(Instant startTime, Instant endTime) {
		super(startTime, endTime);
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
