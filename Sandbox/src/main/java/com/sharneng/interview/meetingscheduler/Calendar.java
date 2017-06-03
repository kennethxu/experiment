package com.sharneng.interview.meetingscheduler;

import java.time.Instant;
import java.util.List;

public class Calendar {
	private List<Meeting> meetings;
	// TODO: we should also have timezone and working hour for this calendar

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
	
	public Iterable<? extends TimeSlot> getOcupiedTimeSlots(Instant timeFrom, Instant timeTo) {
		// TODO: this should have included none working hours and weekends as well. Plus filter out
		// meetings out of the [timeFrom, timeTo) range.
		return meetings; 	
	}
}
