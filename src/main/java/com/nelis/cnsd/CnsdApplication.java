package com.nelis.cnsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CnsdApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnsdApplication.class, args);
	}

//	request DTOs kan ik gewoon Request benoemen.
//	DTOs wil ik ook in mijn controllerlaag stoppen,
//	en ze mappen naar een domeinobejct voordat ze naar de service gaan
}
