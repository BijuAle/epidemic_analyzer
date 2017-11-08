/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju.enums;

/**
 *
 * @author Biju Ale
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String displayName;

    Gender(String displayname) {
        this.displayName = displayname;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
