package repetition_schedule;

import lombok.val;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import service.RepetitionService;

import java.sql.Timestamp;
import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

class RepetitionScheduleTest {

    @Test
    void test_daily() {

        // given
        val givenRepetition = RepetitionSchedule.Repetition.builder()
                .cycle(3)
                .beginDate(LocalDate.of(2020, 2, 7))
                .endDate(LocalDate.of(2021, 2, 7))
                .at(LocalTime.of(10, 0, 0))
                .type(RepeatType.DAILY)
                .build();

        // when
        val actual = RepetitionSchedule.choose(givenRepetition);

        //then
        assertThat(actual)
                .hasSize(123)
                .startsWith(Timestamp.valueOf(LocalDateTime.of(2020, 2, 7, 10, 0, 0)).getTime())
                .endsWith(Timestamp.valueOf(LocalDateTime.of(2021, 2, 7, 10, 0, 0)).getTime());

        assertThat(Sets.newHashSet(actual))
                .hasSize(123);

    }

    @Test
    void test_day_of_week() {

        // given
        val givenRequest = RepetitionSchedule.Repetition.builder()
                .cycle(3)
                .beginDate(LocalDate.of(2020, 2, 7))
                .endDate(LocalDate.of(2021, 2, 7))
                .at(LocalTime.of(10, 0, 0))
                .type(RepeatType.DAY_OF_WEEK)
                .dayOfWeek(DayOfWeek.MONDAY)
                .build();

        // when
        val actual = RepetitionSchedule.choose(givenRequest);

        //then
        assertThat(actual)
                .hasSize(18)
                .startsWith(Timestamp.valueOf(LocalDateTime.of(2020, 2, 10, 10, 0, 0)).getTime())
                .endsWith(Timestamp.valueOf(LocalDateTime.of(2021, 2, 1, 10, 0, 0)).getTime());

        assertThat(Sets.newHashSet(actual))
                .hasSize(18);

    }


    @Test
    void test_day_of_week_nth_occurrence_of_month() {

        // given
        val givenRequest = RepetitionSchedule.Repetition.builder()
                .cycle(2)
                .beginDate(LocalDate.of(2020, 2, 7))
                .endDate(LocalDate.of(2021, 2, 7))
                .at(LocalTime.of(10, 0, 0))
                .type(RepeatType.DAY_OF_WEEK)
                .nthOccurrenceOfMonth(2)
                .dayOfWeek(DayOfWeek.MONDAY)
                .build();

        // when
        val actual = RepetitionSchedule.choose(givenRequest);

        //then
        assertThat(actual)
                .hasSize(6)
                .startsWith(Timestamp.valueOf(LocalDateTime.of(2020, 2, 10, 10, 0, 0)).getTime())
                .endsWith(Timestamp.valueOf(LocalDateTime.of(2020, 12, 14, 10, 0, 0)).getTime());

        assertThat(Sets.newHashSet(actual))
                .hasSize(6);

    }

    @Test
    void test_day_of_week_last_occurrence_of_month() {

        // given
        val givenRequest = RepetitionSchedule.Repetition.builder()
                .cycle(2)
                .beginDate(LocalDate.of(2020, 2, 7))
                .endDate(LocalDate.of(2021, 2, 7))
                .at(LocalTime.of(10, 0, 0))
                .type(RepeatType.DAY_OF_WEEK)
                .nthOccurrenceOfMonth(-1)
                .dayOfWeek(DayOfWeek.MONDAY)
                .build();

        // when
        val actual = RepetitionSchedule.choose(givenRequest);

        //then
        assertThat(actual)
                .hasSize(6)
                .startsWith(Timestamp.valueOf(LocalDateTime.of(2020, 2, 24, 10, 0, 0)).getTime())
                .endsWith(Timestamp.valueOf(LocalDateTime.of(2020, 12, 28, 10, 0, 0)).getTime());

        assertThat(Sets.newHashSet(actual))
                .hasSize(6);

    }

    @Test
    void test_day_of_month() {

        // given
        val givenRequest = RepetitionSchedule.Repetition.builder()
                .cycle(2)
                .beginDate(LocalDate.of(2020, 2, 7))
                .endDate(LocalDate.of(2021, 2, 7))
                .at(LocalTime.of(10, 0, 0))
                .type(RepeatType.DAY_OF_MONTH)
                .dayOfMonth(10)
                .build();

        // when
        val actual = RepetitionSchedule.choose(givenRequest);

        //then
        assertThat(actual)
                .hasSize(6)
                .startsWith(Timestamp.valueOf(LocalDateTime.of(2020, 2, 10, 10, 0, 0)).getTime())
                .endsWith(Timestamp.valueOf(LocalDateTime.of(2020, 12, 10, 10, 0, 0)).getTime());

        assertThat(Sets.newHashSet(actual))
                .hasSize(6);

    }

    @Test
    void test_day_of_month_case_31_days() {

        // given
        val givenRequest = RepetitionSchedule.Repetition.builder()
                .cycle(2)
                .beginDate(LocalDate.of(2020, 2, 7))
                .endDate(LocalDate.of(2021, 2, 7))
                .at(LocalTime.of(10, 0, 0))
                .type(RepeatType.DAY_OF_MONTH)
                .dayOfMonth(31)
                .build();

        // when
        val actual = RepetitionSchedule.choose(givenRequest);

        //then
        assertThat(actual)
                .hasSize(4)
                .startsWith(Timestamp.valueOf(LocalDateTime.of(2020, 3, 31, 10, 0, 0)).getTime())
                .endsWith(Timestamp.valueOf(LocalDateTime.of(2021, 1, 31, 10, 0, 0)).getTime());

        assertThat(Sets.newHashSet(actual))
                .hasSize(4);

    }

    @Test
    void test_day_of_month_last_day() {

        // given
        val givenRequest = RepetitionSchedule.Repetition.builder()
                .cycle(2)
                .beginDate(LocalDate.of(2020, 2, 7))
                .endDate(LocalDate.of(2021, 2, 7))
                .at(LocalTime.of(10, 0, 0))
                .type(RepeatType.DAY_OF_MONTH)
                .dayOfMonth(-1)
                .build();

        // when
        val actual = RepetitionSchedule.choose(givenRequest);

        //then
        assertThat(actual)
                .hasSize(6)
                .startsWith(Timestamp.valueOf(LocalDateTime.of(2020, 2, 29, 10, 0, 0)).getTime())
                .endsWith(Timestamp.valueOf(LocalDateTime.of(2020, 12, 31, 10, 0, 0)).getTime());

        assertThat(Sets.newHashSet(actual))
                .hasSize(6);

    }


    @Test
    void test_repetition_of_dayOfWeek_is_null() {

        // given
        val givenRequest = new RepetitionService.Request(
                RepeatType.DAILY,
                1586156563000L,
                1586156563000L,
                1586156563000L,
                3,
                null,
                null,
                null
        );

        val expected = RepetitionSchedule.Repetition.builder()
                .cycle(3)
                .beginDate(LocalDate.of(2020, 4, 6))
                .beginDate(Instant.ofEpochMilli(1586156563000L).atZone(ZoneId.systemDefault()).toLocalDate())
                .endDate(Instant.ofEpochMilli(1586156563000L).atZone(ZoneId.systemDefault()).toLocalDate())
                .at(Instant.ofEpochMilli(1586156563000L).atZone(ZoneId.systemDefault()).toLocalTime())
                .type(RepeatType.DAILY)
                .build();
        // when
        val actual = RepetitionSchedule.Repetition.of(givenRequest);

        //then
        assertThat(expected).isEqualToComparingFieldByField(actual);

    }

    @Test
    void test_repetition_of_dayOfWeek_is_NotNull() {

        // given
        val givenRequest = new RepetitionService.Request(
                RepeatType.DAILY,
                1586156563000L,
                1586156563000L,
                1586156563000L,
                3,
                2,
                null,
                null
        );

        val expected = RepetitionSchedule.Repetition.builder()
                .cycle(3)
                .beginDate(Instant.ofEpochMilli(1586156563000L).atZone(ZoneId.systemDefault()).toLocalDate())
                .endDate(Instant.ofEpochMilli(1586156563000L).atZone(ZoneId.systemDefault()).toLocalDate())
                .at(Instant.ofEpochMilli(1586156563000L).atZone(ZoneId.systemDefault()).toLocalTime())
                .type(RepeatType.DAILY)
                .dayOfWeek(DayOfWeek.TUESDAY)
                .build();
        // when
        val actual = RepetitionSchedule.Repetition.of(givenRequest);

        //then
        assertThat(expected).isEqualToComparingFieldByField(actual);

    }

}