package repetition_schedule;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.val;

import javax.validation.constraints.NotNull;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public enum RepeatType {

    DAILY(
            (date, repetition) -> true,
            (date, repetition)  -> date.minusDays(repetition.getCycle())
    ),
    DAY_OF_WEEK(
            (date, repetition) -> {

                if(repetition.getNthOccurrenceOfMonth() != null){

                    if(repetition.getNthOccurrenceOfMonth().equals(-1))
                        return date.equals(date.with(TemporalAdjusters.lastInMonth(repetition.getDayOfWeek())));

                    else
                        return date.equals(date.with(TemporalAdjusters.dayOfWeekInMonth(repetition.getNthOccurrenceOfMonth(), repetition.getDayOfWeek())));
                }

                return date.getDayOfWeek() == repetition.getDayOfWeek();
            },
            (date, repetition) -> {

                val cycle = repetition.getCycle();

                if(repetition.getNthOccurrenceOfMonth() != null){

                    if(repetition.getNthOccurrenceOfMonth().equals(-1))
                        return date.minusMonths(cycle).with(TemporalAdjusters.lastInMonth(repetition.getDayOfWeek()));

                    else
                        return date.minusMonths(cycle).with(TemporalAdjusters.dayOfWeekInMonth(repetition.getNthOccurrenceOfMonth(), repetition.getDayOfWeek()));
                }

                return date.minusWeeks(cycle);

            }
    ),
    DAY_OF_MONTH(
            (date, repetition) -> {

                if(repetition.getDayOfMonth().equals(-1))
                    return date.equals(date.with(TemporalAdjusters.lastDayOfMonth()));

                else
                    return date.getDayOfMonth() == repetition.getDayOfMonth();
            },
            (date, repetition) -> {

                if(repetition.getDayOfMonth().equals(-1)){
                    return date.minusMonths(repetition.getCycle()).with(TemporalAdjusters.lastDayOfMonth());
                }

                LocalDate next = date.minusMonths(repetition.getCycle());

                try {
                    return LocalDate.of(next.getYear(), next.getMonth(), repetition.getDayOfMonth());
                } catch (DateTimeException e){
                    return next;
                }
            }

    );

    public BiFunction<LocalDate, RepetitionSchedule.Repetition, Boolean> filterFunction;
    private BiFunction<LocalDate, RepetitionSchedule.Repetition, LocalDate> nextDateFunction;

    RepeatType(final BiFunction<LocalDate, RepetitionSchedule.Repetition, Boolean> filterFunction,
               final BiFunction<LocalDate, RepetitionSchedule.Repetition, LocalDate> nextDateFunction) {

        this.filterFunction = filterFunction;
        this.nextDateFunction = nextDateFunction;

    }

    public static ArrayList<LocalDate> getDateList(List<LocalDate> candidates, RepetitionSchedule.Repetition repetition){

        ArrayList<LocalDate> repeatDate = setFirstDate(candidates);

        for(LocalDate dateTime : candidates){

            LocalDate last = Iterables.getLast(repeatDate);
            LocalDate next = dateTime;

            while(last.isBefore(next)) {

                next = repetition.getType().nextDateFunction.apply(next, repetition);

                if (next.equals(last))
                    repeatDate.add(dateTime);

            }

        }
        return repeatDate;
    }

    @NotNull
    private static ArrayList<LocalDate> setFirstDate(final List<LocalDate> candidates) {

        val list = Lists.newArrayList(candidates.get(0));
        candidates.remove(0);

        return list;
    }

}