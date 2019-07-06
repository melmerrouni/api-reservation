package com.example.reservation;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@SpringBootApplication
@RefreshScope
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(ReservationRepository repository) {
		return strings -> {
			Stream.of("marouane", "khaoula", "chaima", "ayoub", "yassine")
			.forEach(name -> repository.save(new Reservation(name)));
		};
		
	}	

}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	@RestResource(path = "by-name")
	List<Reservation> findByName(@Param("name") String name);
}


@Entity
class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private ZonedDateTime creationDate = ZonedDateTime.now();
	
	
	public Reservation() {
	}

	public Reservation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}
	
	
}
