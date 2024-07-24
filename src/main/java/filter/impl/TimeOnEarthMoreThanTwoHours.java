package filter.impl;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import filter.FlightFilter;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class TimeOnEarthMoreThanTwoHours implements FlightFilter {

    private static final Long FLIGHT_INTERVAL = 7200L;

    /**
     * Checks if a flight stays on land for more than 2 hours.
     * @param flights List of flights to check
     * @return List of flights that stay on land for more than 2 hours
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream().filter(flight -> staysOnLandForMoreThanTwoHours(flight.getSegments()))
                .collect(Collectors.toList());
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
