package cz.zcu.kiv.eegdatabase.wui.core.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;

import cz.zcu.kiv.eegdatabase.wui.core.educationlevel.EducationLevelDTO;
import cz.zcu.kiv.eegdatabase.wui.ui.security.Gender;

public class FullPersonDTO extends IdentifiDTO implements Serializable {

    private static final long serialVersionUID = -5467478793603707659L;

    private String name;
    private String surname;
    private Date dateOfBirth;
    private Gender gender;
    private String username;
    private String email;
    private String password;
    private String passwordVerify;
    private EducationLevelDTO educationLevel;
    private String controlText;
    private String captcha;
    private boolean confirmed;
    private DateTime registrationDate;
    private char laterality;
    private String authority;

    public FullPersonDTO() {
        dateOfBirth = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerify() {
        return passwordVerify;
    }

    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
    }

    public EducationLevelDTO getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevelDTO educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getControlText() {
        return controlText;
    }

    public void setControlText(String controlText) {
        this.controlText = controlText;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public boolean isPasswordValid() {
        return password.equals(passwordVerify);
    }

    public boolean isCaptchaValid() {
        return captcha.equals(controlText);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public char getLaterality() {
        return laterality;
    }

    public void setLaterality(char laterality) {
        this.laterality = laterality;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}