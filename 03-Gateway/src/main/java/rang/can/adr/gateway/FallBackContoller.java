package rang.can.adr.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackContoller {
	
	@GetMapping("/MICRO1")
	public String test() {
		return "MICRO1 not available";
	}

}