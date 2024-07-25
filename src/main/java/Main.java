import factory.FilterType;
import factory.FiltrationFactory;
import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import filter.FlightFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter departureBeforeCurrentTime = FiltrationFactory.createFlightFilter(FilterType.DEPARTURE_BEFORE_CURRENT_TIME);
        FlightFilter segmentsWithAnArrivalDateEarlierThanTheDeparture= FiltrationFactory.createFlightFilter(FilterType.SEGMENTS_WITH_AN_ARRIVAL_DATE_EARLIER_THAN_THE_DEPARTURE_DATE);
        FlightFilter timeOnEarthMoreThanTwoHours = FiltrationFactory.createFlightFilter(FilterType.TIME_ON_EARTH_MORE_THAN_TWO_HOURS);
        System.out.println("==========================0===================================");
        flights.forEach(System.out::println);
        System.out.println("==========================1===================================");
        departureBeforeCurrentTime.filter(flights).forEach(System.out::println);
        System.out.println("==========================2===================================");
        segmentsWithAnArrivalDateEarlierThanTheDeparture.filter(flights).forEach(System.out::println);
        System.out.println("==========================3===================================");
        timeOnEarthMoreThanTwoHours.filter(flights).forEach(System.out::println);
    }
}