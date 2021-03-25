package ru.pebgs.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "exam_type")
public class ExamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String type;

    @OneToMany(mappedBy = "examType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudyPlan> studyPlans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
