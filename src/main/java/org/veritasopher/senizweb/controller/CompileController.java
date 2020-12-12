package org.veritasopher.senizweb.controller;

import org.springframework.web.bind.annotation.*;
import org.veritasopher.seniz.core.model.TransitionSystem;
import org.veritasopher.seniz.generator.DOTGenerator;
import org.veritasopher.senizweb.core.response.Response;
import org.veritasopher.senizweb.model.SourceFile;

/**
 * Compile Controller
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@RestController
@RequestMapping("/compile")
public class CompileController {

    @PostMapping("/{fileId}")
    public Response compileCurrent(@PathVariable("fileId") Long fileId, @RequestBody SourceFile sourceFile) {

        String content = sourceFile.getContent();
        if (content.length() < 3) {
            return Response.failure("invalid");
        }
        org.veritasopher.seniz.controller.CompileController compileController = new org.veritasopher.seniz.controller.CompileController();
        try {
            compileController.compile(content);
            TransitionSystem ts = compileController.getHighestTS();
            DOTGenerator dotGenerator = new DOTGenerator(ts);
            String dotProgram = dotGenerator.generateAsString();

            return Response.success("ok", dotProgram);
        } catch (Exception e) {
            String failureMsg = e.getMessage();
            if (e.getMessage() == null) {
                failureMsg = "Undefined behavior.";
            }
            return Response.failure(failureMsg);
        }
    }

}
