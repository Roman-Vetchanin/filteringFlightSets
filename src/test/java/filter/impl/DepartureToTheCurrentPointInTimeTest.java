package filter.impl;


import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class DepartureToTheCurrentPointInTimeTest {
    private final DepartureToTheCurrentPointInTime test = new DepartureToTheCurrentPointInTime();
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final LocalDateTime currentTime = LocalDateTime.parse(LocalDateTime.now().format(dateFormat));
    private List<Flight> flights;
    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }
    @Test
    void filter() {
        List<Flight> expected = test.filter(flights);
        List<Flight> actual = flights.stream().filter(flight -> flight.getSegments()
                .stream().anyMatch(segment -> segment.getDepartureDate().isAfter(currentTime))).toList();
        int expectedSize = expected.size();
        int actualSize = actual.size();
        assertNotEquals(flights.size(), actualSize);
        assertEquals(expectedSize, actualSize);
        assertEquals(expected, actual);
    }
}