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
	public int getScore() {
		return score;
	}

	@PostMapping("/score")
	public int updateScore(@RequestParam(defaultValue = "1") int increment) {
		score += increment;
		return score;
	}

	@DeleteMapping("/score")
	public int resetScore() {
		score = 0;
		return score;
	}

}
