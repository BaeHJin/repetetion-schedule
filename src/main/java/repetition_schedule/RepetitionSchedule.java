package repetition_schedule;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import service.RepetitionService;

import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;

public class RepetitionSchedule {

    public static List<Long> choose(Repetition repetition) {

        val candidates = IntStream
                .range(0, (int) DAYS.between(repetition.getBeginDate(), repetition.getEndDate().plusDays(1)))
                .mapToObj(repetition.getBeginDate()::plusDays)
                .filter(date -> !repetition.getEndDate().isBefore(date))
                .filter(date -> repetition.getType().filterFunction.apply(date, repetition))
                .collect(Collectors.toList());

        val scheduleDate = repetition.getType().getDateList(candidates, repetition);
        return convertDateToLong(scheduleDate, repetition.getAt());

    }

    private static List<Long> convertDateToLong(List<LocalDate> scheduleDate, LocalTime time){

        return scheduleDate.stream().map(
                date -> Timestamp.valueOf(LocalDateTime.of(date, time)).getTime()
        ).collect(Collectors.toList());

    }


    @Getter
    @Builder
    @FieldDefaults(level= AccessLevel.PRIVATE)
    public static class Repetition {

        @NotNull RepeatType type;
        @NotNull LocalDate beginDate;
        @NotNull LocalDate endDate;
        @NotNull LocalTime at;

        @Min(value = 1, message = "cycle should not be less than 1")
        Integer cycle;

        @Nullable DayOfWeek dayOfWeek;
        @Nullable Integer nthOccurrenceOfMonth;
        @Nullable Integer dayOfMonth;

        public static Repetition of(RepetitionService.Request request){
            return Repetition.builder()
                    .type(request.getType())
                    .beginDate(Instant.ofEpochMilli(request.getBeginDate()).atZone(ZoneId.systemDefault()).toLocalDate())
                    .endDate(Instant.ofEpochMilli(request.getEndDate()).atZone(ZoneId.systemDefault()).toLocalDate())
                    .at(Instant.ofEpochMilli(request.getAt()).atZone(ZoneId.systemDefault()).toLocalTime())
                    .cycle(request.getCycle())
                    .dayOfWeek(request.getDayOfWeek() != null ?
                            DayOfWeek.of(request.getDayOfWeek()) : null)
                    .nthOccurrenceOfMonth(request.getNthOccurrenceOfMonth())
                    .dayOfMonth(request.getDayOfMonth())
                    .build();
        }
    }



}