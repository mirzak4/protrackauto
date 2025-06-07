package ba.unsa.etf.nbp.VehicleTrackPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VehicleTrackPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleTrackPlatformApplication.class, args);
	}

}
