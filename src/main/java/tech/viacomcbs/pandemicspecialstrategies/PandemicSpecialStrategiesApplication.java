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
			case "parents":
				System.out.println("Social distance with parents!");
				break;
			case "school":
				System.out.println("Oh my internet sucks!");
				break;
			case "money":
				System.out.println("What about Pandemic Special?");
				break;
			case "health":
				System.out.println("Wear chin diaper!");
				break;
			default:
				System.out.println("Unexpected behavior. Segmentation fault!");
				break;
		}
	}
}
