package pl.shift4.cronparser;

import lombok.NoArgsConstructor;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static pl.shift4.cronparser.FieldParser.*;

@NoArgsConstructor
class CronExpressionParser {

    CronExpression parse(String cronExpression) {
        var fields = cronExpression.split(" ");
        return new CronExpression(
                parseField(fields[0], 0, 59),
                parseField(fields[1], 0, 23),
                parseField(fields[2], 1, 31),
                parseField(fields[3], 1, 12),
                parseField(fields[4], 0, 6),
                fields[5]);
    }

    private Set<String> parseField(String field, int min, int max) {
        return ASTERISK.parse(field, min, max)
                .or(() -> COMMA.parse(field, min, max))
                .or(() -> DASH.parse(field, min, max))
                .or(() -> SLASH.parse(field, min, max))
                .or(() -> NUMBER.parse(field, min, max))
                .orElseThrow()
                .stream()
                .map(String::valueOf)
                .collect(toSet());
    }
}
