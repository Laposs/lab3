package ru.pebgs.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String value;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "mark", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Set<Journal> journalSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Journal> getJournalSet() {
        if (journalSet == null)
            return new HashSet<>();
        return journalSet;
    }

    public void setJournalSet(Set<Journal> journalSet) {
        this.journalSet = journalSet;
    }
}
