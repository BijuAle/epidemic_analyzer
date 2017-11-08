/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju.controller;

import ea.biju.enums.CurrentInfectionStatus;
import ea.biju.enums.Gender;
import ea.biju.model.Patient;
import ea.biju.repo.AddressRepository;
import ea.biju.repo.PatientRepository;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Biju Ale
 */
@Controller
@RequestMapping("patient_case")
public class PatientCaseController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(value = "add_patient_case", method = RequestMethod.GET)
    public String displayAddPatientCaseView(Model model) {
        model.addAttribute(new Patient());
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("currentInfectionStatusList", CurrentInfectionStatus.values());
        return "patient_case/add_patient_case";
    }

    @RequestMapping(value = "add_patient_case", method = RequestMethod.POST)
    public String processsAddPatientCaseView(@ModelAttribute @Valid Patient newPatient, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("genderList", Gender.values());
            model.addAttribute("currentInfectionStatusList", CurrentInfectionStatus.values());
            return "patient_case/add_patient_case";
        }
        patientRepository.save(newPatient); //persist logic here    
        return "redirect:add_patient_case";
    }

    @RequestMapping(value = "display_patient_cases", method = RequestMethod.GET)
    public String displayAllPatientCases(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient_case/display_patient_cases";
    }

    @RequestMapping(value = "display_patient_cases", method = RequestMethod.POST)
    public String removePatientCase(@RequestParam(required = false) int[] patientIds) {
        if (patientIds == null) {
            return "redirect:display_patient_cases";
        }
        for (int patientId : patientIds) {
            patientRepository.delete(patientId);
        }
        return "redirect:display_patient_cases";
    }

    @RequestMapping(value = "update_patient_case/{id}", method = RequestMethod.GET)
    public String displayUpdatePatientCaseView(Model model, @PathVariable(value = "id") Integer id) {
        model.addAttribute("patient", patientRepository.findOne(id));
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("currentInfectionStatusList", CurrentInfectionStatus.values());
        return "patient_case/update_patient_case";
    }

    @RequestMapping(value = "update_patient_case/{id}", method = RequestMethod.POST)
    public String processUpdatePatientCaseView(
            @PathVariable(value = "id") Integer id,
            @ModelAttribute
            @Valid Patient currentPatient,
            Errors errors,
            Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("genderList", Gender.values());
            model.addAttribute("currentInfectionStatusList", CurrentInfectionStatus.values());
            return "patient_case/update_patient_case";
        }
        Patient updatedPatient = patientRepository.findOne(id);
        updatedPatient.setFirstName(currentPatient.getFirstName());
        updatedPatient.setMiddleName(currentPatient.getMiddleName());
        updatedPatient.setLastName(currentPatient.getLastName());
        updatedPatient.setGender(currentPatient.getGender());
        updatedPatient.setDob(currentPatient.getDob());
        updatedPatient.setCurrentInfectionStatus(currentPatient.getCurrentInfectionStatus());
        updatedPatient.setAddress(currentPatient.getAddress());

        patientRepository.save(updatedPatient);
        return "redirect:../display_patient_cases";
    }
}
