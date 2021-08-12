package rang.can.adr.micro.rest;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;

@Configuration
public class Resilence4Config {

	CircuitBreakerConfig config = CircuitBreakerConfig.custom()
			.slidingWindowType(SlidingWindowType.COUNT_BASED)
			.slidingWindowSize(6)
			.failureRateThreshold(50)
			.waitDurationInOpenState(Duration.ofMillis(40000))
			.build();
	
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration(){
		return factory -> factory.configureDefault(id -> new  Resilience4JConfigBuilder(id)
															  .circuitBreakerConfig(config)
															  .build());
		
		
	}

}
