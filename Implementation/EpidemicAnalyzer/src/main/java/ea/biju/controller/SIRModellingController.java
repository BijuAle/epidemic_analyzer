/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju.controller;

import ea.biju.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Biju Ale
 */
@Controller
@RequestMapping(value = "patient_case")
public class SIRModellingController {

    @Autowired
    PatientRepository patientRepository;

    @RequestMapping(value = "sir_modelling", method = RequestMethod.GET)
    public String getSIRModeller(Model model) {
        model.addAttribute("population", patientRepository.getPopulation());
        return "patient_case/sir_modelling";
    }
}
