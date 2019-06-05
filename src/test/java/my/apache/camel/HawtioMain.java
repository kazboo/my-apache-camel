package my.apache.camel;

import org.springframework.stereotype.Component;

import io.hawt.embedded.Main;

@Component
public class HawtioMain {

    public HawtioMain() throws Exception {
        Main main = new Main();
        System.setProperty("hawtio.authenticationEnabled", "false");
        main.setPort(2525);
        main.setContextPath("/hawtio");
        main.setWarLocation("./");
        main.run();
    }
}
