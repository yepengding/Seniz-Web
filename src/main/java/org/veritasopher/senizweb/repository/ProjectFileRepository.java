package org.veritasopher.senizweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.veritasopher.senizweb.model.ProjectFile;

import java.util.Optional;

/**
 * Source File Repository
 *
 * @author Yepeng Ding
 * @date 12/21/2020
 */
public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {
    Optional<ProjectFile> findByNameAndProjectId(String name, Long projectId);
}
