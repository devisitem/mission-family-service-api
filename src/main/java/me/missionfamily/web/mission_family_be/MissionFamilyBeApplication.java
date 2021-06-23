package me.missionfamily.web.mission_family_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MissionFamilyBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissionFamilyBeApplication.class, args);
	}

}
