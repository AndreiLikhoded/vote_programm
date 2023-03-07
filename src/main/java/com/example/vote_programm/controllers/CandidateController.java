package com.example.vote_programm.controllers;


import com.example.vote_programm.Units.Candidate;
import com.example.vote_programm.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class CandidateController {

    @RequestMapping("/candidates")
    public String allCandidates(Model model){
        model.addAttribute("candidates", getCandidate());
        return "candidates";
    }

    private List<Candidate> getCandidate(){
        List<Candidate> candidates = FileService.readString();
        AtomicReference<Integer> i = new AtomicReference<>(1);
        candidates.forEach(candidate -> {
            candidate.setId(i.get());
            i.getAndSet(i.get() + 1);
        });
        return candidates;
    }

}
