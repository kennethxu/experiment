package com.sharneng.interview.meetingscheduler;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Optional;
import java.util.PriorityQueue;

public class Scheduler {
	public static Optional<Instant> findMeetingTime(Duration duration, Instant lookahead, Calendar... calendars) {
		// TODO parameter check: none of the parameters should be null, including each calendar.
		Instant now = Instant.now();

		// Using a heap to merge meetings from all the calendars in the order of start time.
		PriorityQueue<Pair> merger = new PriorityQueue<>(
				(x, y) -> x.current.getStartTime().compareTo(y.current.getStartTime()));
		for (Calendar c : calendars) {
			Pair pair = new Pair(c.getOcupiedTimeSlots(now, lookahead).iterator());
			if (pair.moveNext()) merger.add(pair);
		}

		Instant startTime = now, endTime = startTime.plus(duration);
		while (!merger.isEmpty()) {
			Pair pair = merger.poll();
			if (!endTime.isAfter(pair.current.getStartTime())) {
				// next meeting starts after our planned end time, we found our meeting time.
				return Optional.of(startTime);
			}
			if (startTime.isBefore(pair.current.getEndTime())) {
				// next attempt should try to start our meeting right after next meeting.
				startTime = pair.current.getEndTime();
				endTime = startTime.plus(duration);
			}
			if (pair.moveNext()) merger.add(pair);
		}
		return endTime.isAfter(lookahead) ? Optional.empty() : Optional.of(startTime);
	}

	// A pair to carry the time slot iterator and its last returned value.
	private static class Pair {
		private TimeSlot current;
		private Iterator<? extends TimeSlot> timeSlots;

		private Pair(Iterator<? extends TimeSlot> timeSlots) {
			this.timeSlots = timeSlots;
		}

		private boolean moveNext() {
			boolean hasNext = timeSlots.hasNext();
			if (hasNext) current = timeSlots.next();
			return hasNext;
		}
	}
}
