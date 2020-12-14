package org.veritasopher.senizweb.controller;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.springframework.web.bind.annotation.*;
import org.veritasopher.seniz.core.model.TransitionSystem;
import org.veritasopher.seniz.generator.DOTGenerator;
import org.veritasopher.senizweb.core.response.Response;
import org.veritasopher.senizweb.model.SourceFile;

import java.io.IOException;

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
            TransitionSystem ts = compileController.compile(content);
            String dotProgram = "digraph { }";
            if (ts != null) {
                DOTGenerator dotGenerator = new DOTGenerator(ts);
                dotProgram = dotGenerator.generateAsString();
            }
            return Response.success("ok", renderToSVG(dotProgram));
        } catch (Exception e) {
            String failureMsg = e.getMessage();
            if (e.getMessage() == null) {
                failureMsg = "Undefined behavior.";
            }
            return Response.failure(failureMsg);
        }
    }

    /**
     * Render DOT program to SVG
     *
     * @param dotProgram
     * @return SVG as string
     */
    private String renderToSVG(String dotProgram) throws IOException {
        MutableGraph g = new Parser().read(dotProgram);
        return Graphviz.fromGraph(g).render(Format.SVG).toString().trim();
    }

}
