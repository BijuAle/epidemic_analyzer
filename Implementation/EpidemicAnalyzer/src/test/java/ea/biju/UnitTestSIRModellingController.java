/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ea.biju.controller.PatientCaseController;
import ea.biju.repo.PatientRepository;

/**
 *
 * @author Biju Ale
 */
public class UnitTestSIRModellingController {

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

//All tests were run & verified with walkthrough and documented
    //because test needs visual verification due to graphics rendering
}
