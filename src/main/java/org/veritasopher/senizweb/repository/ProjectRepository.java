package org.veritasopher.senizweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.veritasopher.senizweb.model.Project;

import java.util.Optional;

/**
 * Project Repository
 *
 * @author Yepeng Ding
 * @date 12/21/2020
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);

}
