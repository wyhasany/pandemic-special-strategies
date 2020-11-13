package tech.viacomcbs.pandemicspecialstrategies;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@SpringBootApplication
public class PandemicSpecialStrategiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandemicSpecialStrategiesApplication.class, args);
	}

}

@Service
@RequiredArgsConstructor
class PandemicService {

	private final Map<String, BehaviorStrategy> strategies;

	void handle(String stimulus) {
		BehaviorStrategy strategy = strategies.get(stimulus);
		strategy.handle();
	}
}

interface BehaviorStrategy {

	void handle();
}

@Component(value = "parents")
class ParentsBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("Social distance with parents!");
	}
}

@Component(value = "school")
class SchoolBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("Oh my internet sucks!");
	}
}

@Component(value = "money")
class MoneyBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("What about Pandemic Special?");
	}
}

@Component(value = "health")
class HealthBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("Wear chin diaper!");
	}
}