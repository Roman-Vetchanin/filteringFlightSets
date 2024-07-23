package filter.impl;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TimeOnEarthMoreThanTwoHoursTest {
    private final TimeOnEarthMoreThanTwoHours test = new TimeOnEarthMoreThanTwoHours();
    private static final Long FLIGHT_INTERVAL = 7200L;
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void filter() {
        List<Flight> expected = test.filter(flights);
        List<Flight> actual = flights.stream().filter(flight -> staysOnLandForMoreThanTwoHours(flight.getSegments()))
                .toList();
        int expectedSize = expected.size();
        int actualSize = actual.size();
        assertNotEquals(flights.size(), actualSize);
        assertEquals(expectedSize, actualSize);
        assertEquals(expected, actual);
    }
    private boolean staysOnLandForMoreThanTwoHours(List<Segment> segmentList) {
        long secondsOnLand = 0;
        for (int i = 1; i < segmentList.size(); i++) {
            secondsOnLand += Duration.between(segmentList.get(i-1).getArrivalDate(),
                    segmentList.get(i).getDepartureDate()).getSeconds();
        }
        return secondsOnLand <= FLIGHT_INTERVAL;
    }
}