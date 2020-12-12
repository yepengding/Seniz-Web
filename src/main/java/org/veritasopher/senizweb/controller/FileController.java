package org.veritasopher.senizweb.controller;

import org.springframework.web.bind.annotation.*;
import org.veritasopher.senizweb.core.response.Response;
import org.veritasopher.senizweb.model.SourceFile;

import java.util.ArrayList;
import java.util.List;

/**
 * File Controller
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @GetMapping("/list")
    public Response getList() {
        List<SourceFile> sourceFiles = new ArrayList<>();
        sourceFiles.add(new SourceFile(1L, "file1", 10, "hello world"));
        sourceFiles.add(new SourceFile(2L, "file2", 20, "hi"));
        sourceFiles.add(new SourceFile(3L, "file3", 30, "you"));
        return Response.success(sourceFiles);
    }

}
