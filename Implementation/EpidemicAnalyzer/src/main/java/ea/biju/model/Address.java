/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Biju Ale
 */
@Entity
@Table(name = "tbl_address")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull
    @Size(min = 1, message = "District cannot be null")
    private String district;

    @Column
    @NotNull
    @Size(min = 1, message = "VDC cannot be null")
    private String vdc;

    @Column
    @NotNull
    @Min(1)
    @Max(9)
    private int wardNo;

    @Column(precision = 10, scale = 6)
    @Pattern(regexp = "([+-]?\\d+\\.?\\d+)",
            message = "GPS Coordinates contains positive or negative floating points only.")
    private String latitude;

    @Column(precision = 10, scale = 6)
    @Pattern(regexp = "([+-]?\\d+\\.?\\d+)",
            message = "GPS Coordinates contains positive or negative floating points only.")
    private String longitude;

    @OneToOne
    @XmlElement
    @XmlInverseReference(mappedBy = "address")
    @JoinColumn(name = "address_id")
    private Patient patient;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVdc() {
        return vdc;
    }

    public void setVdc(String vdc) {
        this.vdc = vdc;
    }

    public int getWardNo() {
        return wardNo;
    }

    public void setWardNo(int wardNo) {
        this.wardNo = wardNo;
    }

    public int getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
