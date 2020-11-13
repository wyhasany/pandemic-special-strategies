package tech.viacomcbs.pandemicspecialstrategies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class PandemicSpecialStrategiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandemicSpecialStrategiesApplication.class, args);
	}

}

@Service
class PandemicService {

	private final Map<String, BehaviorStrategy> strategies;

	public PandemicService(List<BehaviorStrategy> strategies) {
		this.strategies = strategies.stream()
			.collect(Collectors.toMap(BehaviorStrategy::canHandle, Function.identity()));
	}

	void handle(String stimulus) {
		BehaviorStrategy strategy = strategies.get(stimulus);
		strategy.handle();
	}
}

interface BehaviorStrategy {

	void handle();

	String canHandle();
}

@Component(value = "parents")
class ParentsBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("Social distance with parents!");
	}

	@Override
	public String canHandle() {
		return "parents";
	}
}

@Component(value = "school")
class SchoolBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("Oh my internet sucks!");
	}

	@Override
	public String canHandle() {
		return "school";
	}
}

@Component(value = "money")
class MoneyBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("What about Pandemic Special?");
	}

	@Override
	public String canHandle() {
		return "money";
	}
}

@Component(value = "health")
class HealthBehavior implements BehaviorStrategy {

	@Override
	public void handle() {
		System.out.println("Wear chin diaper!");
	}

	@Override
	public String canHandle() {
		return "health";
	}
}