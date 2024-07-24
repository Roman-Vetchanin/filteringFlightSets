package filter.impl;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SegmentsWithAnArrivalDateEarlierThanTheDepartureDateTest {
    private final SegmentsWithAnArrivalDateEarlierThanTheDepartureDate test = new SegmentsWithAnArrivalDateEarlierThanTheDepartureDate();
    private List<Flight> flights;
    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void filter() {
        List<Flight> expected = test.filter(flights);
        List<Flight> actual = flights.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .toList();
        int expectedSize = expected.size();
        int actualSize = flights.size();
        assertNotEquals(expectedSize, actualSize);
        assertEquals(expected, actual);
    }
}