package factory;

import filter.FlightFilter;
import filter.impl.DepartureToTheCurrentPointInTime;
import filter.impl.SegmentsWithAnArrivalDateEarlierThanTheDepartureDate;
import filter.impl.TimeOnEarthMoreThanTwoHours;


public class FiltrationFactory {
    /**
     * Creates a flight filter based on the provided filter type.
     * @param filterType The type of filter to create.
     * @return A new FlightFilter instance based on the provided filter type.
     */
    public static FlightFilter createFlightFilter(FilterType filterType) {
        if (FilterType.DEPARTURE_BEFORE_CURRENT_TIME.equals(filterType)) {
          return new DepartureToTheCurrentPointInTime();
        }else if (FilterType.SEGMENTS_WITH_AN_ARRIVAL_DATE_EARLIER_THAN_THE_DEPARTURE_DATE.equals(filterType)) {
            return new SegmentsWithAnArrivalDateEarlierThanTheDepartureDate();
        }else if (FilterType.TIME_ON_EARTH_MORE_THAN_TWO_HOURS.equals(filterType)) {
          new TimeOnEarthMoreThanTwoHours();
            return new TimeOnEarthMoreThanTwoHours(); // replace with your implementation of this filter type.
        }else {
            throw new IllegalArgumentException("Invalid filter type");
        }
    }
}
