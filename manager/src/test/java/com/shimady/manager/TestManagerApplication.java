package com.shimady.manager;

import com.shimady.manager.config.TestcontainersConfig;
import org.springframework.boot.SpringApplication;

public class TestManagerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ManagerApplication::main).with(TestcontainersConfig.class).run(args);
	}

}
