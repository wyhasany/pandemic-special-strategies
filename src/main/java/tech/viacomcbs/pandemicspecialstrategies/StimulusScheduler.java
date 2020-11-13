package tech.viacomcbs.pandemicspecialstrategies;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadLocalRandom;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class StimulusScheduler {

    private final PandemicService pandemicService;

    @Scheduled(fixedRate = 1000) //1s
    void stimulate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int nextInt = random.nextInt(4);
        Object stimulus = switch (nextInt) {
            case 0 -> new ParentStimulus();
            case 1 -> new SchoolStimulus();
            case 2 -> new MoneyStimulus();
            case 3 -> new HealthStimulus();
            default -> "";
        };
        pandemicService.handle(stimulus);
    }
}
