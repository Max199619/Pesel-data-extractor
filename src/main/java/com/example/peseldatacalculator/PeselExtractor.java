package com.example.peseldatacalculator;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PeselExtractor {
    public TextField peselInput;
    public TextField peselValid;
    public TextField yearOfBirth;
    public TextField monthOfBirth;
    public TextField dayOfBirth;
    public TextField gender;
    public Button data;

    public void CalculateData() {
        if (peselInput.getLength() == 11)
        {
            char[] PESEL = peselInput.getText().toCharArray();
            yearOfBirth.setText(setYOB(PESEL));
            monthOfBirth.setText(setMOB(PESEL,yearOfBirth.getText()));
            dayOfBirth.setText(setDOB(PESEL));
            gender.setText(setGender(PESEL));
            peselValid.setText(getValidity(PESEL));
        }
        else {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("WARNING!!!");
            warning.setContentText("PESEL must be 11 digits long.");
            warning.setHeaderText("Wrong Length!");
            warning.showAndWait();
        }
    }

    public String setYOB(char[] PESEL){
        String year = String.copyValueOf(PESEL, 0,2);
        switch (PESEL[2]){
            case '8':
            case '9':
                year = "18" + year;
                break;
            case '0':
            case '1':
                year = "19" + year;
                break;
            case '2':
            case '3':
                year = "20" + year;
                break;
            case '4':
            case '5':
                year = "21" + year;
                break;
            case '6':
            case '7':
                year = "22" + year;
                break;
        }
        return year;
    }

    public String setMOB(char[] PESEL, String year){
        int month = Integer.parseInt(String.copyValueOf(PESEL, 2,2));
        if (Integer.parseInt(year) < 1900 && Integer.parseInt(year) > 1799) {
            month -= 80;
        }
        else if (Integer.parseInt(year) < 2100 && Integer.parseInt(year) > 1999){
            month -= 20;
        }
        else if (Integer.parseInt(year) < 2200 && Integer.parseInt(year) > 2099){
            month -= 40;
        }
        else if (Integer.parseInt(year) < 2300 && Integer.parseInt(year) > 2199){
            month -= 60;
        }

        return String.valueOf(months.values()[month-1]);
    }

    public String setDOB(char[] PESEL){
        return String.copyValueOf(PESEL, 4,2);
    }

    public String setGender(char[] PESEL){
        int digit = Integer.parseInt(String.copyValueOf(PESEL, 9,1));
        return digit % 2 == 0 ? "Female" : "Male";
    }

    public String getValidity(char[] PESEL){
        int digit = Integer.parseInt(String.copyValueOf(PESEL, 10,1));

        int check = 0;
        int[] weights = new int[]{1,3,7,9,1,3,7,9,1,3};
        for (int i = 0; i<=9; i++){
            check += (weights[i] * Integer.parseInt(String.copyValueOf(PESEL, i,1))) % 10;
        }
        String isValid;
        if (digit == 10 - (check % 10)){
            isValid = "Valid";
            peselValid.setStyle("-fx-text-fill: green");
        }
        else{
            isValid = "Not Valid";
            peselValid.setStyle("-fx-text-fill: red");
        }

        return  isValid;
    }
}
