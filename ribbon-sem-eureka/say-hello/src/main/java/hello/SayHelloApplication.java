package hello;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SayHelloApplication {

	private static Logger log = LoggerFactory.getLogger(SayHelloApplication.class);
	
	@Value("${server.port}")
	private int port;

	@RequestMapping(value = "/greeting")
	public ResponseEntity<String> greet() {
		
		
		log.info("Access /greeting");

		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("port", ""+ port);
		
		return new ResponseEntity<String>(greetings.get(randomNum), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/")
	public String home() {
		log.info("Access /");
		return "Hi!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SayHelloApplication.class, args);
	}
}