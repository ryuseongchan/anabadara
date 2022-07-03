package kr.co.anabadara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AnabadaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnabadaraApplication.class, args);
	}

}
