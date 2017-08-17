package com.pl.drools.wrapper;

import org.kie.api.time.Calendar;
import org.quartz.impl.calendar.WeeklyCalendar;

public class CalendarWrapper implements Calendar {
    private WeeklyCalendar cal;

    public CalendarWrapper(WeeklyCalendar cal) {
        this.cal = cal;
    }

    public WeeklyCalendar getCal() {
        return cal;
    }

    public void setCal(WeeklyCalendar cal) {
        this.cal = cal;
    }

    @Override
    public boolean isTimeIncluded(long l) {
        return cal.isTimeIncluded(l);
    }
}
