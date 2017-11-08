/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju.model;

import ea.biju.enums.CurrentInfectionStatus;
import ea.biju.enums.Gender;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Biju Ale
 */
@Entity
@Table(name = "tbl_patient")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(message = "Only alphabets, dots, and spaces allowed.",
            regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")
    private String firstName;

    @Column
    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(message = "Only alphabets, dots, and spaces allowed.",
            regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")
    private String middleName;

    @Column
    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(message = "Only alphabets, dots, and spaces allowed.",
            regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @XmlElement
    @XmlInverseReference(mappedBy = "patient")
    private Address address;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @Enumerated(EnumType.STRING)
    private CurrentInfectionStatus currentInfectionStatus;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public CurrentInfectionStatus getCurrentInfectionStatus() {
        return currentInfectionStatus;
    }

    public void setCurrentInfectionStatus(CurrentInfectionStatus currentInfectionStatus) {
        this.currentInfectionStatus = currentInfectionStatus;
    }

}
