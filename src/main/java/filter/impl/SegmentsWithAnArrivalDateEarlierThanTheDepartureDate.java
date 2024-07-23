package filter.impl;

import com.gridnine.testing.Flight;
import filter.FlightFilter;

import java.util.List;
import java.util.stream.Collectors;

public class SegmentsWithAnArrivalDateEarlierThanTheDepartureDate implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().toLocalDate()
                                .isBefore(segment.getDepartureDate().toLocalDate())))
                .collect(Collectors.toList());
    }
}
