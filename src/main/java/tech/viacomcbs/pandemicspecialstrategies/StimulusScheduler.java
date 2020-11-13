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
        int nextInt = random.nextInt(5);
        String stimulus = switch (nextInt) {
            case 0 -> "parents";
            case 1 -> "school";
            case 2 -> "money";
            case 3 -> "health";
            case 4 -> "wjug";
            default -> "";
        };
        pandemicService.handle(stimulus);
    }
}
