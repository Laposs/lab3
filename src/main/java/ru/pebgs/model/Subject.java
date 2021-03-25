package ru.pebgs.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "subject")
@NamedQueries({
        @NamedQuery(query = "select s from Student s", name = "findAll")
})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String shortName;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudyPlan> studyPlans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Set<StudyPlan> getStudyPlans() {
        if (studyPlans == null) {
            this.studyPlans = new HashSet<>();
        }
        return studyPlans;
    }

    public void setStudyPlans(Set<StudyPlan> studyPlans) {
        this.studyPlans = studyPlans;
    }
}
