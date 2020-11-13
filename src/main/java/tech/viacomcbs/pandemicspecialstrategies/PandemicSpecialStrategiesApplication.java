package tech.viacomcbs.pandemicspecialstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

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
		System.out.println("Register strategy: " + strategy);
		strategies.put(strategy.canHandle(), strategy);
	}

	void removeStrategy(String strategyKey) {
		System.out.println("Unregister strategy: " + strategyKey);
		strategies.remove(strategyKey);
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

	AtomicReference<PandemicService> pandemicServiceReference = new AtomicReference<>();

	void handle();

	String canHandle();

	@Autowired
	default void register(PandemicService pandemicService) {
		BehaviorStrategy.pandemicServiceReference.set(pandemicService);
		pandemicService.addStrategy(this);
	}

	@PreDestroy
	default void unregister() {
		PandemicService pandemicService = BehaviorStrategy.pandemicServiceReference.get();
		if (pandemicService != null) {
			pandemicService.removeStrategy(canHandle());
		}
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