package at.htl.movietheater.control;

import at.htl.movietheater.entity.Theater;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class TheaterRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Theater save(Theater theater) {
        Theater existingTheater = findByName(theater.getName());

        return existingTheater != null ? existingTheater : em.merge(theater);
    }

    public Theater findByName(String name) {
        try {
            TypedQuery<Theater> query = em
                    .createNamedQuery("Theater.findByName", Theater.class)
                    .setParameter("name", name);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
