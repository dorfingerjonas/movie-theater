package at.htl.movietheater.control;

import at.htl.movietheater.entity.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class MovieRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Movie save(Movie movie) {
        return em.merge(movie);
    }
}
