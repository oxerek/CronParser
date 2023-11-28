package pl.shift4.cronparser;

import io.vavr.control.Try;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Optional.*;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.rangeClosed;

@FunctionalInterface
interface FieldParser {

    Optional<Set<Integer>> parse(String field, int min, int max);

    FieldParser ASTERISK = (field, min, max) -> field.equals("*") ? of(rangeClosed(min, max)
            .boxed()
            .collect(toSet())) : empty();

    FieldParser COMMA = (field, min, max) -> field.contains(",") ? of(stream(field.split(","))
            .map(Integer::parseInt)
            .collect(toSet())) : empty();

    FieldParser DASH = (field, min, max) -> field.contains("-") ? dash(field) : empty();

    FieldParser SLASH = (field, min, max) -> field.contains("/") ? slash(field, min, max) : empty();

    FieldParser NUMBER = (field, min, max) -> ofNullable(Try.of(() -> Set.of(parseInt(field))).getOrNull());

    private static Optional<Set<Integer>> dash(String field) {
        var parts = field.split("-");
        return of(rangeClosed(parseInt(parts[0]), parseInt(parts[1])).boxed().collect(toSet()));
    }

    private static Optional<Set<Integer>> slash(String field, int min, int max) {
        var parts = field.split("/");
        var values = new HashSet<Integer>();
        for (int i = min; i <= max; i += parseInt(parts[1])) {
            values.add(i);
        }
        return of(values);
    }
}
