package com.gandesc.graphql_play.lec09;

import com.gandesc.graphql_play.lec09.dto.AllTypes;
import com.gandesc.graphql_play.lec09.dto.Car;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Controller
public class ScalarController {
  private AllTypes allTypes = AllTypes.builder()
      .id(UUID.randomUUID())
      .height(10)
      .temperature(10.2F)
      .city("Atlanta")
      .isValid(false)

      .distance(120000000000L)
      .ageInYears(Byte.valueOf("12"))
      .ageInMonths(Short.valueOf("100"))
      .bigDecimal(BigDecimal.valueOf(12345678.12345))
      .bigInteger(BigInteger.valueOf(123456789))
      .date(LocalDate.now())
      .time(LocalTime.now())
      .dateTime(OffsetDateTime.now())
      .car(Car.BMW)
      .build();

  @QueryMapping
  public Mono<AllTypes> get() {
    return Mono.just(allTypes);
  }
}
