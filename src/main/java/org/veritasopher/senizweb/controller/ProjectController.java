package org.veritasopher.senizweb.controller;

import org.springframework.web.bind.annotation.*;
import org.veritasopher.senizweb.core.response.Response;
import org.veritasopher.senizweb.model.Project;
import org.veritasopher.senizweb.service.ProjectService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Project Controller
 *
 * @author Yepeng Ding
 * @date 12/22/2020
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @PostMapping("/create")
    public Response createProject(@RequestBody Project project) {
        Optional<Project> projectOptional = projectService.findByName(project.getName());
        return projectOptional.map((p) -> Response.failure("Project exists.")).orElseGet(() -> Response.success(projectService.create(project)));
    }

    @GetMapping("/list")
    public Response getProjectList() {
        return Response.success(projectService.findAllProject());
    }

}
