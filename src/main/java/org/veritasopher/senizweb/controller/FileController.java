package org.veritasopher.senizweb.controller;

import org.springframework.web.bind.annotation.*;
import org.veritasopher.senizweb.core.response.Response;
import org.veritasopher.senizweb.model.ProjectFile;
import org.veritasopher.senizweb.service.ProjectFileService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * File Controller
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private ProjectFileService projectFileService;

    @PostMapping("/create")
    public Response createFile(@RequestBody ProjectFile projectFile) {
        Optional<ProjectFile> fileOptional = projectFileService.findByNameAndProject(projectFile.getName(), projectFile.getProjectId());
        return fileOptional.map((file) -> Response.failure("File exists.")).orElseGet(() -> Response.success(projectFileService.create(projectFile)));
    }

    @PostMapping("/update")
    public Response updateFile(@RequestBody ProjectFile projectFile) {
        return Response.success(projectFileService.update(projectFile));
    }

    @GetMapping("/get/{fileId}")
    public Response getFile(@PathVariable("fileId") Long fileId) {
        Optional<ProjectFile> fileOptional = projectFileService.findById(fileId);
        return fileOptional.map(Response::success).orElseGet(() -> Response.failure("No such file."));
    }

    @PostMapping("/delete/{fileId}")
    public Response deleteFile(@PathVariable("fileId") Long fileId) {
        Optional<ProjectFile> fileOptional = projectFileService.findById(fileId);
        if (fileOptional.isEmpty()) {
            return Response.failure("No such file.");
        }
        projectFileService.delete(fileId);
        return Response.success("Delete successfully.");
    }

    @GetMapping("/list")
    public Response getFileList() {
        return Response.success(projectFileService.findAll());
    }

}
