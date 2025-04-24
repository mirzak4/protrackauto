package ba.unsa.etf.nbp.VehicleTrackPlatform.bootstrapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Comparator;

@Component
public class SeedRunner implements ApplicationRunner {

    @Value("${app.resources.database}")
    private String dbResourceUrl;
    private final DataSource dataSource;

    private String getSeedScriptsUrl() {
        return dbResourceUrl + "/scripts";
    }

    public SeedRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String seedScriptsUrl = this.getSeedScriptsUrl();

        // Pattern to match all .sql files in the folder
        Resource[] resources = resolver.getResources("classpath*:" + seedScriptsUrl + "/*.sql");

        if (resources.length == 0) {
            throw new RuntimeException("No SQL scripts found in classpath:" + seedScriptsUrl);
        }

        try (Connection conn = dataSource.getConnection()) {
            Arrays.stream(resources)
                    .sorted(Comparator.comparing(Resource::getFilename)) // sort alphabetically
                    .forEach(resource -> {
                        try {
                            String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                            try (CallableStatement stmt = conn.prepareCall(sql)) {
                                stmt.execute();
                                System.out.println("Executed: " + resource.getFilename());
                            }
                        } catch (Exception e) {
                            System.err.println("Error executing " + resource.getFilename() + ": " + e.getMessage());
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}

