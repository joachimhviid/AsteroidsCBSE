package sdu.score;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoreApplication {
	private int score = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoreApplication.class, args);
	}

	@GetMapping("/score")
	public String getScore() {
		return "Microservice score: " + score;
	}

	@PostMapping("/score")
	public String updateScore(@RequestParam(defaultValue = "1") int increment) {
		score += increment;
		return "Microservice score: " + score;
	}

}
