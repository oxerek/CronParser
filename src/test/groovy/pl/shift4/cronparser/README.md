# Cron expression parser application

Application is written using java 18, tests are written using Spock Framework.

Building and testing an application:

*./gradlew clean build*

Running an application: 

*java -cp cron-parser-0.0.1.jar pl.shift4.cronparser.CronParserApp "cron_string"*


Exemplary output for:

*java -cp cron-parser-0.0.1.jar pl.shift4.cronparser.CronParserApp "*/15 0 1,15 * 1-5 /usr/bin/find"*

**minute         0 15 30 45**

**hour           0**

**day of month   1 15**

**month          1 10 11 12 2 3 4 5 6 7 8 9**

**day of week    1 2 3 4 5**

**command        /usr/bin/find**
