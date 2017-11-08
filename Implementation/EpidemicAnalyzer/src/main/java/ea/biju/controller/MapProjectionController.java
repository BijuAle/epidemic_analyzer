/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju.controller;

import ea.biju.model.Patient;
import ea.biju.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.joda.time.Years;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Biju Ale
 */
@Controller
@RequestMapping("patient_case")
public class MapProjectionController {

    @Autowired
    PatientRepository patientRepository;

    @RequestMapping(value = "project_to_map")
    public String getJSON() {
        createEpidemicMapProjectionData();
        return "patient_case/epidemic_map_projection";
    }

    private void createEpidemicMapProjectionData() {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Create 1 root element - parent node = 'markers'
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("markers");
            doc.appendChild(rootElement);

            //Create many marker element - child node = 'marker'
            Iterable<Patient> resultSet = patientRepository.findAll();
            for (Patient p : resultSet) {
                Element marker = doc.createElement("marker");
                rootElement.appendChild(marker);

                marker.setAttribute("id", Integer.toString(p.getId()));
                marker.setAttribute("name", p.getFirstName() + " " + p.getMiddleName() + " " + p.getLastName());
                marker.setAttribute("age", getAge(p.getDob()));
                marker.setAttribute("gender", p.getGender().getDisplayName());
                marker.setAttribute("address", p.getAddress().getWardNo() + ", " + p.getAddress().getVdc() + ", " + p.getAddress().getDistrict());
                marker.setAttribute("lat", p.getAddress().getLatitude());
                marker.setAttribute("lng", p.getAddress().getLongitude());
                marker.setAttribute("currentInfectionStatus", p.getCurrentInfectionStatus().getDisplayName());
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            String filePathString = "src\\main\\resources\\static\\xml\\EpidemicMapProjectionData.xml";
            File xmlFile = new File(filePathString);
            if (xmlFile.exists() && !xmlFile.isDirectory()) {
                xmlFile.delete();
            }
            StreamResult result = new StreamResult(xmlFile);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private String getAge(Date dob) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        LocalDate birthdate = new LocalDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        LocalDate now = new LocalDate();
        String age = Integer.toString(Years.yearsBetween(birthdate, now).getYears());
        return age;
    }

}
