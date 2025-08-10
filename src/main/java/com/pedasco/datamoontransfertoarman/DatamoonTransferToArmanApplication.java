package com.pedasco.datamoontransfertoarman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatamoonTransferToArmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatamoonTransferToArmanApplication.class, args);
	}

}
