package com.zzj.solrapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.zzj.solrapi")
public class SolrapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolrapiApplication.class, args);
	}

}
