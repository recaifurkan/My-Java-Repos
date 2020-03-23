package com.rfbsoft.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Province {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;


    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="province")
    private Set<District> districts = new HashSet<>();

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

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

}
