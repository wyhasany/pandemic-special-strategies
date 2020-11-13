package tech.viacomcbs.pandemicspecialstrategies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
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

	private final Map<Class<?>, BehaviorStrategy<?>> strategies;

	public PandemicService(List<BehaviorStrategy<?>> strategies) {
		this.strategies = strategies.stream()
			.collect(Collectors.toMap(BehaviorStrategy::canHandle, Function.identity()));
	}

	void handle(Object stimulus) {
		BehaviorStrategy<?> strategy = strategies.get(stimulus.getClass());
		strategy.handle();
	}
}

interface BehaviorStrategy<T extends Stimulus> {

	void handle();

	@SuppressWarnings("unchecked")
	default Class<T> canHandle() {
		return (Class<T>)(((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]);
	}
}

@Component
class ParentsBehavior implements BehaviorStrategy<ParentStimulus> {

	@Override
	public void handle() {
		System.out.println("Social distance with parents!");
	}
}

@Component
class SchoolBehavior implements BehaviorStrategy<SchoolStimulus> {

	@Override
	public void handle() {
		System.out.println("Oh my internet sucks!");
	}
}

@Component
class MoneyBehavior implements BehaviorStrategy<MoneyStimulus> {

	@Override
	public void handle() {
		System.out.println("What about Pandemic Special?");
	}
}

@Component
class HealthBehavior implements BehaviorStrategy<HealthStimulus> {

	@Override
	public void handle() {
		System.out.println("Wear chin diaper!");
	}
}

interface Stimulus {}
class ParentStimulus implements Stimulus {}
class SchoolStimulus implements Stimulus {}
class MoneyStimulus implements Stimulus {}
class HealthStimulus implements Stimulus {}