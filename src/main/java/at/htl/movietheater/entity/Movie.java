package at.htl.movietheater.entity;

import javax.persistence.*;

@Entity(name = "MT_MOVIE")
@NamedQueries(
    @NamedQuery(
        name = "Movie.findByTitle",
        query = "select m from MT_MOVIE m where m.title like :title"
    )
)
public class Movie {

    @Id
    @Column(name = "MO_ID")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MO_TITLE")
    private String title;

    @Column(name = "MO_DURATION")
    private double duration;

    @Enumerated
    @Column(name = "MO_GENRE")
    private Genre genre;

    @Column(name = "MO_AGE_LIMIT")
    private int ageLimit;

    public Movie() {
    }

    public Movie(String title, double duration, Genre genre, int ageLimit) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.ageLimit = ageLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", title, genre);
    }
}
