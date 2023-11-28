package pl.shift4.cronparser;

import java.util.Set;

record CronExpression(
        Set<String> minutes,
        Set<String> hours,
        Set<String> daysOfMonth,
        Set<String> months,
        Set<String> daysOfWeek,
        String command) {}
