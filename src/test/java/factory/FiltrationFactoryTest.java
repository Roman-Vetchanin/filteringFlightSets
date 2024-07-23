package factory;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import filter.FlightFilter;
import filter.impl.DepartureToTheCurrentPointInTime;
import filter.impl.SegmentsWithAnArrivalDateEarlierThanTheDepartureDate;
import filter.impl.TimeOnEarthMoreThanTwoHours;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;


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
    void createFlightFilter() {
        List<Flight> instantDepartureToTheCurrentPointInTime = FiltrationFactory.createFlightFilter(FilterType.DEPARTURE_BEFORE_CURRENT_TIME,flights);
        assertEquals(departureToTheCurrentPointInTime.filter(flights), instantDepartureToTheCurrentPointInTime);
        Object expected = instantDepartureToTheCurrentPointInTime.getClass();
        Object actual = departureToTheCurrentPointInTime.getClass();

        List<Flight> instantSegmentsWithAnArrivalDateEarlierThanTheDepartureDate = FiltrationFactory.createFlightFilter(FilterType.SEGMENTS_WITH_AN_ARRIVAL_DATE_EARLIER_THAN_THE_DEPARTURE_DATE,flights);
        assertEquals(segmentsWithAnArrivalDate.filter(flights), instantSegmentsWithAnArrivalDateEarlierThanTheDepartureDate);

        List<Flight>flightFilter = FiltrationFactory.createFlightFilter(FilterType.TIME_ON_EARTH_MORE_THAN_TWO_HOURS,flights);
        assertEquals(timeOnEarthMoreThanTwoHours.filter(flights), flightFilter);

        assertThrows(IllegalArgumentException.class, () -> FiltrationFactory.createFlightFilter(FilterType.valueOf("INVALID_FILTER_TYPE"), flights));
    }
}