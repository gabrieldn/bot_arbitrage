package co.codingnomads.bot.arbitrage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author Kevin Neag
 */

/**
 * Application Class for starting the entire application
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    @Autowired
    Controller controller;

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    /**
     * CommandLineRunner method that starts the controller runBot method from the command line
     *
     * @throws Exception
     */
    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            controller.runBot();
        };
    }

//    @Scheduled(fixedDelay = 5000)
//    public void bosta() {
//        try {
//            controller.runBot();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}