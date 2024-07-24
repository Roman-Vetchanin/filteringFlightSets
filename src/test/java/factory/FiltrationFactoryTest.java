package factory;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import filter.FlightFilter;
import filter.impl.DepartureToTheCurrentPointInTime;
import filter.impl.SegmentsWithAnArrivalDateEarlierThanTheDepartureDate;
import filter.impl.TimeOnEarthMoreThanTwoHours;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FiltrationFactoryTest {
    private final DepartureToTheCurrentPointInTime departureToTheCurrentPointInTime = new DepartureToTheCurrentPointInTime();
    private final SegmentsWithAnArrivalDateEarlierThanTheDepartureDate segmentsWithAnArrivalDate = new SegmentsWithAnArrivalDateEarlierThanTheDepartureDate();
    private final TimeOnEarthMoreThanTwoHours timeOnEarthMoreThanTwoHours = new TimeOnEarthMoreThanTwoHours();
    List<Flight> flights;
    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void createFlightFilterTest() {
        FlightFilter instantDepartureToTheCurrentPointInTime = FiltrationFactory.createFlightFilter(FilterType.DEPARTURE_BEFORE_CURRENT_TIME);
        assertEquals(departureToTheCurrentPointInTime.filter(flights), instantDepartureToTheCurrentPointInTime.filter(flights));
        Object expected = FiltrationFactory.createFlightFilter(FilterType.DEPARTURE_BEFORE_CURRENT_TIME).getClass();
        Object actual = departureToTheCurrentPointInTime.getClass();
        assertEquals(expected, actual);


        FlightFilter instantSegmentsWithAnArrivalDateEarlierThanTheDepartureDate = FiltrationFactory.createFlightFilter(FilterType.SEGMENTS_WITH_AN_ARRIVAL_DATE_EARLIER_THAN_THE_DEPARTURE_DATE);
        assertEquals(segmentsWithAnArrivalDate.filter(flights), instantSegmentsWithAnArrivalDateEarlierThanTheDepartureDate.filter(flights));
        expected = FiltrationFactory.createFlightFilter(FilterType.SEGMENTS_WITH_AN_ARRIVAL_DATE_EARLIER_THAN_THE_DEPARTURE_DATE).getClass();
        actual = instantSegmentsWithAnArrivalDateEarlierThanTheDepartureDate.getClass();
        assertEquals(expected, actual);

        FlightFilter instantTimeOnEarthMoreThanTwoHours = FiltrationFactory.createFlightFilter(FilterType.TIME_ON_EARTH_MORE_THAN_TWO_HOURS);
        assertEquals(timeOnEarthMoreThanTwoHours.filter(flights), instantTimeOnEarthMoreThanTwoHours.filter(flights));
        expected = FiltrationFactory.createFlightFilter(FilterType.TIME_ON_EARTH_MORE_THAN_TWO_HOURS).getClass();
        actual = instantTimeOnEarthMoreThanTwoHours.getClass();
        assertEquals(expected, actual);

        assertThrows(IllegalArgumentException.class, () -> FiltrationFactory.createFlightFilter(FilterType.valueOf("INVALID_FILTER_TYPE")));
    }
}