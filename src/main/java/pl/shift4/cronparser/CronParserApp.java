package pl.shift4.cronparser;

import static pl.shift4.cronparser.CronExpressionHandler.PRINTER;

public class CronParserApp {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java CronParserApp \"cron_string\"");
            return;
        }

        var cronExpression = new CronExpressionParser().parse(args[0]);

        PRINTER.handle(cronExpression);
    }
}
