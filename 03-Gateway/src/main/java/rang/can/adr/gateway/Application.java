package rang.can.adr.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()

//				 .route(r -> r.path("/books/**")
//						 
//	                        .uri("lb://MICRO1")
//	                        
//	                        )
//				
				 .route("libros", r -> r
		                    .path("/libros/**")
		                    .filters(f->f.rewritePath("/libros/(?<segment>.*)","/books/${segment}"))
		                    .uri("lb://MICRO1")
		                  )
				.build();
	}
	
	
}
