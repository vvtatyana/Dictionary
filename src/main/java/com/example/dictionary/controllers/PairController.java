package com.example.dictionary.controllers;

import com.example.dictionary.models.Dictionary;
import com.example.dictionary.models.RelatedPair;
import com.example.dictionary.service.DictionaryService;
import com.example.dictionary.service.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dictionary/pair")
public class PairController {
    private final DictionaryService dictionaryService;
    private final PairService pairService;
    private Dictionary dictionary;

    @Autowired
    public PairController(DictionaryService dictionaryService, PairService pairService) {
        this.dictionaryService = dictionaryService;
        this.pairService = pairService;
    }

    @GetMapping("/{name}")
    public String selectPairs(Model model, @PathVariable String name) {
        dictionary = dictionaryService.select(name);
        model.addAttribute("relatedPair", pairService.selectAll(dictionary.getId()));
        model.addAttribute("dictionary", dictionary);
        return "/dictionary/pair/selectPair";
    }

    @GetMapping("/addPair")
    public String addPair(@ModelAttribute("relatedPair") RelatedPair relatedPair) {
        return "dictionary/pair/addPair";
    }

    @PostMapping("")
    public String addPair(@ModelAttribute("relatedPair") RelatedPair relatedPair,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/dictionary/pair/addPair";
        pairService.insert(dictionary.getId(), relatedPair);

        return "redirect:/dictionary/pair/" + dictionary.getName();
    }

    @GetMapping("/{id}/editPair")
    public String editPair(Model model, @PathVariable("id") int id) {
        RelatedPair pair = pairService.select(id);
        if(pair != null)
            model.addAttribute("relatedPair", pair);
        return "/dictionary/pair/editPair";
    }

    @PutMapping("/{id}")
    public String updatePair(@ModelAttribute("relatedPair") RelatedPair relatedPair,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/dictionary/pair/editPair";

        pairService.update(id, relatedPair);
        return "redirect:/dictionary/pair/" + dictionary.getName();
    }

    @DeleteMapping("/{id}")
    public String deletePair(@PathVariable("id") int id) {
        pairService.delete(id);
        return "redirect:/dictionary/pair/" + dictionary.getName();
    }


    @GetMapping("/searchPair")
    public String searchPair(Model model, @RequestParam String keyword) {
        model.addAttribute("relatedPair", pairService.search(dictionary.getId(), keyword));
        model.addAttribute("dictionary", dictionary);
        return "dictionary/pair/selectPair";
    }

}
