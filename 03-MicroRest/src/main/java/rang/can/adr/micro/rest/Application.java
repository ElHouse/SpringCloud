package rang.can.adr.micro.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/books")
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	private List<Book> bookList = Arrays.asList(
	        new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
	        new Book(2L, "Baeldung goes to the park", "Slavisa")
	    );

    @GetMapping("/")
    public List<Book> findAllBooks( 
    		@RequestHeader("libro-request") String header,

    		@RequestHeader("Authorization") String Authorization
    		) {
    	
    	System.out.println(header);
    	System.out.println(Authorization);

        return bookList;
    }

    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookList.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
    }
    
    @Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
    
    @GetMapping(value = "/microservice1")
	public String method1() {
		log.info("Inside method1");
		String baseUrl = "http://MICRO2/books/microservice2";
		String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
		log.info("The response received by method1 is " + response);
		return response;
	}
}

