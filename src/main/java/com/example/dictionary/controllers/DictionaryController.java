package com.example.dictionary.controllers;

import com.example.dictionary.models.*;
import com.example.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
     private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("")
    public String selectDictionary(Model model) {
        model.addAttribute("dictionary", dictionaryService.select());
        return "dictionary/selectDictionary";
    }

    @GetMapping("/addDictionary")
    public String addDictionary(@ModelAttribute("dictionary") Dictionary dictionary) {
        return "pair/addDictionary";
    }

    @PostMapping("/addDictionary")
    public String addDictionary(@ModelAttribute("dictionary") Dictionary newDictionary,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "dictionary/addDictionary";
        dictionaryService.insert(newDictionary);

        return "redirect:/dictionary";
    }

    @GetMapping("/{id}/editDictionary")
    public String editDictionary(Model model, @PathVariable("id") int id) {
        Dictionary dictionary = dictionaryService.select(id);
        if(dictionary != null)
            model.addAttribute("dictionary", dictionary);
        return "dictionary/editDictionary";
    }

    @PutMapping("/{id}")
    public String updateDictionary(@ModelAttribute("dictionary") Dictionary dictionary,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "dictionary/editDictionary";

        dictionaryService.update(id, dictionary);
        return "redirect:/dictionary";
    }

    @DeleteMapping("/{id}")
    public String deleteDictionary(@PathVariable("id") int id) {
        dictionaryService.delete(id);
        return "redirect:/dictionary";
    }
}
