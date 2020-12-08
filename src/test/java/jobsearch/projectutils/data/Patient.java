package jobsearch.projectutils.data;

import jobsearch.framework.testmanagement.BaseTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static jobsearch.framework.utils.Utils.dateFormatter;

public class Patient extends BaseTest {

    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private String middleName;
    private String externalId;
    private String ssn;
    private String filePath = "MEND.csv";

    public Patient(String dateOfBirth, String firstName, String lastName, String middleName, String externalId, String ssn) {
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.externalId = externalId;
        this.ssn = ssn;
    }

    public Patient() {
    }

    public Patient(String externalId) {
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getPatientCount() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while(line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines.size();
    }

    public String getPatientObject(int index) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while(line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines.get(index);
    }

    public String getPatientDateOfBirth(int index) {
        String date = getPatientObject(index).split(",")[0];
        return dateFormatter(date);
    }

    public String getPatientFirstName(int index) {
        return getPatientObject(index).split(",")[1];
    }

    public String getPatientLastName(int index) {
        return getPatientObject(index).split(",")[2];
    }

    public String getPatientMiddleName(int index) {
        return getPatientObject(index).split(",")[3];
    }

    public String getPatientExternalId(int index) {
        return getPatientObject(index).split(",")[4];
    }

    public String getPatientSsn(int index) {
        String ssnText = getPatientObject(index).split(",")[5];
        if(ssnText.equals("999999999")) {
            ssnText = "000000000";
        }
        return ssnText;
    }
}
