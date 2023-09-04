package com.example.PracticalBio;


import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/TranscriptionAndTranslation")
public class TranscriptAndTranslateController {

    private String userad;

    TranscriptionAndTranslation base = new TranscriptionAndTranslation();

    @GetMapping
    public String base (Model model){
        String getDNA = base.getDNA();
        model.addAttribute("getDNA", getDNA);

        return "TranscriptionAndTranslation";
    }

    @PostMapping
    public String beginProblem (@RequestParam("userInput") String userInput, Model model) throws CompoundNotFoundException {
        String getDNA = base.getDNA();
        model.addAttribute("getDNA", getDNA);

        String getRNA = base.beginProblem(userInput);
        model.addAttribute("getRNA",getRNA);

        boolean isPassed = false;
        if(base.isPassed1()) {
            isPassed = true;
        }
        model.addAttribute("isPassed",isPassed);

        return "TranscriptionAndTranslation";
    }
    @PostMapping ("/a")
    public String endProblem (@RequestParam("userInput2") String userInput2, Model model) throws CompoundNotFoundException {
        if(base.isPassed1()) {
            model.addAttribute("isPassed",true);
            String getDNA = base.getDNA();
            model.addAttribute("getDNA", getDNA);

            String getRNA = base.getRNA();
            model.addAttribute("getRNA", getRNA);

            String getPRO = base.endProblem(userInput2);
            model.addAttribute("getPRO", getPRO);

            return "TranscriptionAndTranslation";
        }
        return "TranscriptionAndTranslation";
    }

}
