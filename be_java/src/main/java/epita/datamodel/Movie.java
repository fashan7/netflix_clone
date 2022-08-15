package epita.datamodel;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;
@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

//    @Column(name = "date")
//    private String date;

//    @Id
    @Column(name = "external_id")
    private String externalId;

    @Column(name = "imglink")
    private String imglink;

    @OneToMany(mappedBy = "movie")
    private Set<SeenMovie> seenMovies = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getDate() {
//        return date;
//    }
//
//    public Movie date(String date) {
//        this.date = date;
//        return this;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }

    public String getExternalId() {
        return externalId;
    }

    public Movie externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
}
