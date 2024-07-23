package factory;

import filter.impl.DepartureToTheCurrentPointInTime;
import filter.impl.SegmentsWithAnArrivalDateEarlierThanTheDepartureDate;
import filter.impl.TimeOnEarthMoreThanTwoHours;
import com.gridnine.testing.Flight;
import filter.FlightFilter;


import java.util.List;


public class FiltrationFactory {
    /**
     * Factory method to create a FlightFilter based on the provided filter type and flights list.
     * @param filterType The type of filter to create.
     * @param flights The list of flights to filter.
     */
    public static List<Flight> createFlightFilter(FilterType filterType, List<Flight> flights) {
        if (FilterType.DEPARTURE_BEFORE_CURRENT_TIME.equals(filterType)) {
          FlightFilter departureToTheCurrentPointInTime = new DepartureToTheCurrentPointInTime();
          return departureToTheCurrentPointInTime.filter(flights);
        }else if (FilterType.SEGMENTS_WITH_AN_ARRIVAL_DATE_EARLIER_THAN_THE_DEPARTURE_DATE.equals(filterType)) {
            FlightFilter segmentWithAnArrivalDateEarlierThanTheDepartureDate = new SegmentsWithAnArrivalDateEarlierThanTheDepartureDate();
            return segmentWithAnArrivalDateEarlierThanTheDepartureDate.filter(flights);
        }else if (FilterType.TIME_ON_EARTH_MORE_THAN_TWO_HOURS.equals(filterType)) {
            FlightFilter timeOnEarthMoreThanTwoHours = new TimeOnEarthMoreThanTwoHours();
            return timeOnEarthMoreThanTwoHours.filter(flights);  // replace with your implementation of this filter type.
        }else {
            throw new IllegalArgumentException("Invalid filter type");
        }
    }
}
