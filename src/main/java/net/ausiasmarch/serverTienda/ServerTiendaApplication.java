package net.ausiasmarch.serverTienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ServerTiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTiendaApplication.class, args);
	}

}
