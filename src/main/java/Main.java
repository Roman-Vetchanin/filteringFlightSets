import factory.FilterType;
import factory.FiltrationFactory;
import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List <Flight> list = FiltrationFactory.createFlightFilter(FilterType.DEPARTURE_BEFORE_CURRENT_TIME, flights);
        List<Flight> list1 = FiltrationFactory.createFlightFilter(FilterType.SEGMENTS_WITH_AN_ARRIVAL_DATE_EARLIER_THAN_THE_DEPARTURE_DATE, flights);
        List <Flight> list2 = FiltrationFactory.createFlightFilter(FilterType.TIME_ON_EARTH_MORE_THAN_TWO_HOURS, flights);
        System.out.println("==========================0===================================");
        flights.forEach(System.out::println);
        System.out.println("==========================1===================================");
        list.forEach(System.out::println);
        System.out.println("==========================2===================================");
        list1.forEach(System.out::println);
        System.out.println("==========================3===================================");
        list2.forEach(System.out::println);
    }
}