package filter.impl;

import com.gridnine.testing.Flight;
import filter.FlightFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureToTheCurrentPointInTime implements FlightFilter {

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final LocalDateTime currentTime = LocalDateTime.parse(LocalDateTime.now().format(dateFormat));

    /**
     * Filters flights that departed before the current time.
     * @param flights The list of flights to filter.
     * @return A list of flights that departed before the current time.
     */
    @Override
    public List<Flight> filter (List<Flight> flights) {
        return flights.stream().filter(flight -> flight.getSegments()
                .stream().anyMatch(segment -> segment.getDepartureDate().isAfter(currentTime))).collect(Collectors.toList());
    }
}
