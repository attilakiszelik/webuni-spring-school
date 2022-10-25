package hu.kaev.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.kaev.school.service.InitDbService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	private final InitDbService initDbService;
	
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.deleteDb();
		initDbService.addInitData();
	}

}