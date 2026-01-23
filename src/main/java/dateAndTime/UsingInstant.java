package dateAndTime;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class UsingInstant {

    @Test
    public void testTimeInstant(){
        Instant instant = Instant.now(); // UTC time
        System.out.println("Simple instant:"+instant);
    }

    @Test
    public void testInstantUsingTimeZone(){
        Instant instant = Instant.now(); // UTC time
        System.out.println("Simple instant:"+instant);

        ZoneId utc = ZoneId.of("UTC");
        ZoneId indiaZoneId = ZoneId.of("Asia/Kolkata");
        ZoneId nyZoneId = ZoneId.of("America/New_York");

        ZonedDateTime indiaTime = instant.atZone(indiaZoneId);
        ZonedDateTime nyTime = instant.atZone(nyZoneId);

        System.out.println("indiaTime:"+indiaTime);
        System.out.println("nyTime:"+nyTime);
    }

    @Test
    public void testInstantUsingTimeZoneAndDateFormatter() {
        // best practise is to always follow this flow: Instant →ZonedDateTime →DateTimeFormatter
        Instant instant = Instant.now();
        System.out.println("Simple instant:"+instant);

        var standardDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        ZoneId indiaZone = ZoneId.of("Asia/Kolkata");
        String standardTimeFormatInIndiaTimeZone = standardDateTimeFormat.withZone(indiaZone).format(instant);
        System.out.println("standardTimeFormatInIndiaTimeZone:"+ standardTimeFormatInIndiaTimeZone);
    }
}
