package service;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import repetition_schedule.RepeatType;
import repetition_schedule.RepetitionSchedule;

import javax.validation.constraints.Min;
import java.util.List;

public class RepetitionService {

    public List<Long> getRepetetionSchedule(Request request){

        return RepetitionSchedule.choose(RepetitionSchedule.Repetition.of(request));
    }

    @Value
    public static class Request {

        @NotNull RepeatType type;
        @NotNull Long beginDate;
        @NotNull Long endDate;
        @NotNull Long at;

        @Min(value = 1, message = "cycle should not be less than 1")
        Integer cycle;

        @Nullable Integer dayOfWeek;
        @Nullable Integer nthOccurrenceOfMonth;
        @Nullable Integer dayOfMonth;

    }
}
