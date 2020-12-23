package org.veritasopher.senizweb.controller;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.veritasopher.seniz.controller.MasterController;
import org.veritasopher.seniz.core.model.GlobalEnvironment;
import org.veritasopher.seniz.core.model.SourceFile;
import org.veritasopher.seniz.core.model.TransitionSystem;
import org.veritasopher.seniz.generator.DOTGenerator;
import org.veritasopher.senizweb.core.exception.Assert;
import org.veritasopher.senizweb.core.response.Response;
import org.veritasopher.senizweb.service.ProjectService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Compile Controller
 *
 * @author Yepeng Ding
 * @date 12/12/2020
 */
@RestController
@RequestMapping("/compile")
public class CompileController {

    @Resource
    private ProjectService projectService;

    @PostMapping("/project/{projectId}")
    public Response compileProject(@PathVariable("projectId") Long projectId) {
        List<SourceFile> sourceFiles = projectService.findSourceFileByProjectId(projectId);

        Assert.isTrue(sourceFiles.size() > 0, "No source file to compile.");

        MasterController masterController = new MasterController();
        GlobalEnvironment env;
        try {
            env = masterController.compile(sourceFiles);
            String dotProgram = "digraph { }";
            TransitionSystem ts = env.getMainTS();
            if (ts != null) {
                DOTGenerator dotGenerator = new DOTGenerator(env, ts);
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

//    @PostMapping("/{fileId}")
//    public Response compileCurrent(@PathVariable("fileId") Long fileId, @RequestBody SourceFile sourceFile) {
//
//        String content = sourceFile.getContent();
//        if (content.length() < 3) {
//            return Response.failure("invalid");
//        }
//        org.veritasopher.seniz.controller.CompileController compileController = new org.veritasopher.seniz.controller.CompileController();
//        try {
//            TransitionSystem ts = compileController.compile(content);
//            String dotProgram = "digraph { }";
//            if (ts != null) {
//                DOTGenerator dotGenerator = new DOTGenerator(ts);
//                dotProgram = dotGenerator.generateAsString();
//            }
//            return Response.success("ok", renderToSVG(dotProgram));
//        } catch (Exception e) {
//            String failureMsg = e.getMessage();
//            if (e.getMessage() == null) {
//                failureMsg = "Undefined behavior.";
//            }
//            return Response.failure(failureMsg);
//        }
//    }

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
