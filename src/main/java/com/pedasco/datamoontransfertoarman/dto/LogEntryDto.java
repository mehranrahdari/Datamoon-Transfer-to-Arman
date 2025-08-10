package com.pedasco.datamoontransfertoarman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntryDto {
    private String plateChar;
    private String insertTime;
    private String firstName;
    private String lastName;
    private String melliCode;
    private String companyName;
    private String phoneNumber;
    private int direction;
    private int cameraId;
    private String timeEpochMs;


    public String getPlateChar() {
        return plateChar;
    }

    public void setPlateChar(String plateChar) {
        this.plateChar = plateChar;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMelliCode() {
        return melliCode;
    }

    public void setMelliCode(String melliCode) {
        this.melliCode = melliCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public String getTimeEpochMs() {
        return timeEpochMs;
    }

    public void setTimeEpochMs(String timeEpochMs) {
        this.timeEpochMs = timeEpochMs;
    }

    @Override
    public String toString() {
        return "LogEntryDto{" +
               "plateChar='" + plateChar + '\'' +
               ", insertTime='" + insertTime + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", melliCode='" + melliCode + '\'' +
               ", companyName='" + companyName + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", direction=" + direction +
               ", cameraId=" + cameraId +
               ", timeEpochMs='" + timeEpochMs + '\'' +
               '}';
    }

    public LogEntryDto() {
    }

    public LogEntryDto(String plateChar, String insertTime, String firstName, String lastName, String melliCode, String companyName, String phoneNumber, int direction, int cameraId, String timeEpochMs) {
        this.plateChar = plateChar;
        this.insertTime = insertTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.melliCode = melliCode;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.direction = direction;
        this.cameraId = cameraId;
        this.timeEpochMs = timeEpochMs;
    }
}
