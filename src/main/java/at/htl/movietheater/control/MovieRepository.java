package at.htl.movietheater.control;

import at.htl.movietheater.entity.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class MovieRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Movie save(Movie movie) {
        return em.merge(movie);
    }

    public Movie findByTitle(String title) {
        try {
            TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTitle", Movie.class)
                .setParameter("title", title);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
