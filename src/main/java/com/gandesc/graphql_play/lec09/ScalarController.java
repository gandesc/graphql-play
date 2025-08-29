package com.gandesc.graphql_play.lec09;

import com.gandesc.graphql_play.lec09.dto.AllTypes;
import com.gandesc.graphql_play.lec09.dto.Car;
import com.gandesc.graphql_play.lec09.dto.Product;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Map;
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

  @QueryMapping
  public Flux<Product> products() {
    return Flux.just(Product.builder()
            .name("banana")
            .attributes(Map.of("expiration", "01/01/2025"))
            .build(),
        Product.builder()
            .name("CPU")
            .attributes(Map.of("cache", "256Kb"))
            .build()
    );
  }
}
