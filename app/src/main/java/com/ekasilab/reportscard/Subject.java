package com.ekasilab.reportscard;

import static com.ekasilab.reportscard.R.id.test1;
import static com.ekasilab.reportscard.R.id.test2;
import static com.ekasilab.reportscard.R.id.test3;

/**
 * Created by ekasilab on 12/10/2016.
 */

public class Subject {


    private String subjectName, subjectDescription;
    private int subjectID;
    private double test_1, test_2, test_3;

    public Subject(int subjectID, String subjectName, String subjectDescription, double test_1, double test_2, double test_3) {
        this.subjectID = subjectID;
        setSubjectDescription(subjectDescription);
        setSubjectName(subjectName);
        setTest_1(test_1);
        setTest_2(test_2);
        setTest_3(test_3);
    }

    public Subject(int subjectID) {
        this(subjectID, "none", "none", 0, 0, 0);
    }

    public Subject() {
        this(0);
    }

    public Subject(String subjectName, String subjectDescription, double test_1, double test_2, double test_3) {
        setSubjectDescription(subjectDescription);
        setSubjectName(subjectName);
        setTest_1(test_1);
        setTest_2(test_2);
        setTest_3(test_3);
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public void setTest_1(double test_1) {
        this.test_1 = test_1;
    }

    public void setTest_2(double test_2) {
        this.test_2 = test_2;
    }

    public void setTest_3(double test_3) {
        this.test_3 = test_3;
    }


    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }


    public double getTest_1() {
        return test_1;
    }

    public double getTest_2() {
        return test_2;
    }

    public double getTest_3() {
        return test_3;
    }


    public double calcSubjectMark() {
        double mark= (test_1 + test_2 + test_3) / 3;
        return mark;
    }

}
