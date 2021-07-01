package rang.can.adr.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public GlobalFilter globalFilter() {
		return (exchange, chain) -> {
			System.out.println("First Global filter");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				System.out.println("Second Global filter");
			}));
		};
	}
}
