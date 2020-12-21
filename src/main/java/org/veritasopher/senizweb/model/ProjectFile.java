package org.veritasopher.senizweb.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Project File
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@Entity
@Table(name = "projectFile")
@Data
public class ProjectFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @Column(name = "content")
    private String content;

    @Column(name = "projectId")
    private Long projectId;

}
