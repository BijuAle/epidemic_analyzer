/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ea.biju.controller.PatientCaseController;
import ea.biju.enums.CurrentInfectionStatus;
import ea.biju.enums.Gender;
import ea.biju.model.Patient;
import ea.biju.repo.PatientRepository;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

/**
 *
 * @author Biju Ale
 */
public class UnitTestPatientCaseController {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientCaseController patientCaseController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientCaseController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDisplayAddPatientCaseView() throws Exception {
        mockMvc.perform(get("/patient_case/add_patient_case"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentInfectionStatusList", CurrentInfectionStatus.values()))
                .andExpect(model().attribute("genderList", Gender.values()))
                .andExpect(view().name("patient_case/add_patient_case"));
    }

    @Test
    public void testProcesssAddPatientCaseView() throws Exception {

        Patient p = new Patient();
        p.setFirstName("Test");
        patientRepository.save(p);
        mockMvc.perform(post("/patient_case/add_patient_case"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("patient", instanceOf(Patient.class)))
                .andExpect(model().attribute("genderList", Gender.values()))
                .andExpect(view().name("patient_case/add_patient_case"));
    }

    @Test
    public void testDisplayAllPatientCases() throws Exception {

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());

        when(patientRepository.findAll()).thenReturn((List) patients);

        mockMvc.perform(get("/patient_case/display_patient_cases"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient_case/display_patient_cases"))
                .andExpect(model().attribute("patients", hasSize(2)));

    }

    @Test
    public void testProcessUpdatePatientCaseView() throws Exception {
        Integer id = 1;
        when(patientRepository.findOne(id)).thenReturn(new Patient());

        mockMvc.perform(post("/patient_case/update_patient_case/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("patient", instanceOf(Patient.class)))
                .andExpect(model().attribute("currentInfectionStatusList", CurrentInfectionStatus.values()))
                .andExpect(model().attribute("genderList", Gender.values()))
                .andExpect(view().name("patient_case/update_patient_case"));
    }

    @Test
    public void removePatientCase() throws Exception {
        Integer[] id = new Integer[1];
        id[0] = 1;
        Patient p = new Patient();
        patientRepository.delete(p);
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());
        when(patientRepository.findAll()).thenReturn((List) patients);
        mockMvc.perform(get("/patient_case/display_patient_cases"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient_case/display_patient_cases"))
                .andExpect(model().attribute("patients", hasSize(2)));

    }
}
