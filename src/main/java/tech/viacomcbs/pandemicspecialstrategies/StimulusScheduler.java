package tech.viacomcbs.pandemicspecialstrategies;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadLocalRandom;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class StimulusScheduler {

    private final PandemicService pandemicService;

    private final ApplicationContext context;

    private final MoneyBehavior moneyBehavior;

    @Scheduled(fixedRate = 500)//0.5s
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

    @EventListener(ApplicationReadyEvent.class)
    public void registerNewBehaviour() {
        AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
        BehaviorStrategy wjugBehaviour = new BehaviorStrategy() {
            @Override
            public void handle() {
                System.out.println("No excuses!");
            }

            @Override
            public String canHandle() {
                return "wjug";
            }
        };
        new Thread(
            () -> {
                sleep5s();
                autowireCapableBeanFactory.autowireBean(
                    wjugBehaviour
                );
                sleep5s();
                autowireCapableBeanFactory.destroyBean(
                    moneyBehavior
                );
            }).start();
    }

    private void sleep5s() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
