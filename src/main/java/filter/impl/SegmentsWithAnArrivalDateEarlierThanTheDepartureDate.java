package filter.impl;

import com.gridnine.testing.Flight;
import filter.FlightFilter;

import java.util.List;

public class SegmentsWithAnArrivalDateEarlierThanTheDepartureDate implements FlightFilter {

    /**
     * Filters flights by checking if any segment has an arrival date earlier than the departure date.
     * @param flights The list of flights to filter.
     * @return A list of flights that meet the filter criteria.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
             // .filter(flight -> flight.getSegments().stream()
                // .noneMatch(segment ->segment.getDepartureDate().toLocalTime().equals(segment.getArrivalDate().toLocalTime()))
                // в задании не указано, нужна ли фильтрация по равенству даты отправления и прибытия
                .toList();
    }
}
