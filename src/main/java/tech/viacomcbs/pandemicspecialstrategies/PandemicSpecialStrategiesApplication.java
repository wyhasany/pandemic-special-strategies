package tech.viacomcbs.pandemicspecialstrategies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class PandemicSpecialStrategiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandemicSpecialStrategiesApplication.class, args);
	}

}

@Service
class PandemicService {

	void handle(String stimulus) {
		switch (stimulus) {
			case "parents" -> System.out.println("Social distance with parents!");
			case "school" -> System.out.println("Oh my internet sucks!");
			case "money" -> System.out.println("What about Pandemic Special?");
			case "health" -> System.out.println("Wear chin diaper!");
			default -> System.out.println("Unexpected behavior. Segmentation fault!");
		}
	}
}
