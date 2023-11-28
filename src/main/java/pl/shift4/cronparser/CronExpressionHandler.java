package pl.shift4.cronparser;

import java.util.TreeSet;

@FunctionalInterface
public interface CronExpressionHandler {

    void handle(CronExpression cronExpression);

    CronExpressionHandler PRINTER = cronExpression -> {
        System.out.printf("%-14s %s%n", "minute", String.join(" ", new TreeSet<>(cronExpression.minutes())));
        System.out.printf("%-14s %s%n", "hour", String.join(" ", new TreeSet<>(cronExpression.hours())));
        System.out.printf("%-14s %s%n", "day of month", String.join(" ", new TreeSet<>(cronExpression.daysOfMonth())));
        System.out.printf("%-14s %s%n", "month", String.join(" ", new TreeSet<>(cronExpression.months())));
        System.out.printf("%-14s %s%n", "day of week", String.join(" ", new TreeSet<>(cronExpression.daysOfWeek())));
        System.out.printf("%-14s %s%n", "command", cronExpression.command());
    };
}
