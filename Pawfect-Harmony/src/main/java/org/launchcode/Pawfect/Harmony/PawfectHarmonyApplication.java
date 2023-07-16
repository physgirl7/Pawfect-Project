package org.launchcode.Pawfect.Harmony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PawfectHarmonyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawfectHarmonyApplication.class, args);
	}

}

//(exclude = {DataSourceAutoConfiguration.class })