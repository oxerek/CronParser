package pl.shift4.cronparser

import spock.lang.Specification

class CronExpressionParserSpec extends Specification {

    def "should parse field expression with asterisk correctly"() {

        given:
        def parser = FieldParser.ASTERISK

        when:
        def result = parser.parse("*", 0, 5)

        then:
        result.get() == [0, 1, 2, 3, 4, 5].toSet()
    }

    def "should parse field expression with comma correctly"() {

        given:
        def parser = FieldParser.COMMA

        when:
        def result = parser.parse("1,15", -1, -1)

        then:
        result.get() == [1, 15].toSet()
    }

    def "should parse field expression with dash correctly"() {

        given:
        def parser = FieldParser.DASH

        when:
        def result = parser.parse("1-5", -1, -1)

        then:
        result.get() == [1, 2, 3, 4, 5].toSet()
    }

    def "should parse field expression with slash correctly"() {

        given:
        def parser = FieldParser.SLASH

        when:
        def result = parser.parse("*/15", 0, 59)

        then:
        result.get() == [0, 15, 30, 45].toSet()
    }

    def "should parse field expression with number correctly"() {

        given:
        def parser = FieldParser.NUMBER

        when:
        def result = parser.parse("9", -1, -1)

        then:
        result.get() == [9].toSet()
    }

    def "should parse whole cron expression correctly"() {

        given:
        def parser = new CronExpressionParser()

        when:
        def result = parser.parse("*/15 0 1,15 * 1-5 /usr/bin/find")

        then:
        result != null
        result.minutes().size() == 4
        result.hours().size() == 1
        result.daysOfMonth().size() == 2
        result.months().size() == 12
        result.daysOfWeek().size() == 5
        result.command() == "/usr/bin/find"
    }
}
