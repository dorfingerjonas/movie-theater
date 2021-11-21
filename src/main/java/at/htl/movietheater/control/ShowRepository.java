package at.htl.movietheater.control;

import at.htl.movietheater.entity.Show;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class ShowRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Show save(Show show) {
        show = em.merge(show);

        if (show.getPrevShow() == null) {
            show.setPrevShow(findLastShow());
        }

        if (show.getPrevShow() != null) {
            show.getPrevShow().setNextShow(show);
        }

        return show;
    }

    public Show findLastShow() {
        try {
            TypedQuery<Show> query = em
                .createNamedQuery("Show.findLastShow", Show.class);

            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Show findById(long id) {
        return em.find(Show.class, id);
    }
}
