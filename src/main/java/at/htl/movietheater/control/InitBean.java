package at.htl.movietheater.control;

import at.htl.movietheater.entity.Genre;
import at.htl.movietheater.entity.Movie;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitBean {

    @Inject
    MovieRepository movieRepository;

    public void onStart(@Observes StartupEvent event) {
        init();
    }

    private void init() {
        List<String> lines = readFile("src/main/resources/movies.csv");

        lines
            .stream()
            .skip(1)
            .forEach(line -> {
                String[] parts = line.split(";");

                movieRepository.save(
                    new Movie(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Genre.valueOf(parts[2].toUpperCase()),
                        Integer.parseInt(parts[3]))
                );
            });
    }

    private List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
