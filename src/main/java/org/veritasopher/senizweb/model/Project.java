package org.veritasopher.senizweb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desc")
    private String description;

//    @OneToMany(mappedBy="project")
//    private Set<ProjectFile> projectFiles;

}
