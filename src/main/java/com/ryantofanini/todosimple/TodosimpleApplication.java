package com.ryantofanini.todosimple;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TodosimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodosimpleApplication.class, args);
	}

}
