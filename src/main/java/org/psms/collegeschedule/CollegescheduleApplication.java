package org.psms.collegeschedule;

import org.psms.collegeschedule.component.DataEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CollegescheduleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ca = SpringApplication.run(CollegescheduleApplication.class, args);
//        ca.getBean(DataEntry.class).subEntry();

    }

}
