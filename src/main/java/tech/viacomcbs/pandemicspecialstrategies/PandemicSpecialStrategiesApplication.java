package tech.viacomcbs.pandemicspecialstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

	private final Map<String, BehaviorStrategy> strategies = new ConcurrentHashMap<>();

	void addStrategy(BehaviorStrategy strategy) {
		strategies.put(strategy.canHandle(), strategy);
	}

	void handle(String stimulus) {
		BehaviorStrategy strategy = strategies.get(stimulus);
		if (strategy == null) {
			System.out.println("Unexpected behavior. Segmentation fault!");
			return;
		}
		strategy.handle();
	}
}

interface BehaviorStrategy {

	void handle();

	String canHandle();

	@Autowired
	default void register(PandemicService pandemicService) {
		pandemicService.addStrategy(this);
	}
}

@Component
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

@Component
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

@Component
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

@Component
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