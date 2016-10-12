package com.ekasilab.reportscard;

import java.io.Serializable;

/**
 * Created by ekasilab on 06/10/2016.
 */
public class LearnerReport implements Serializable {


    private String name, surname, learnerAddress, subjectName;
    private int stdNo;

    private double test1, test2, test3;

    public LearnerReport() {
        this(0);
    }

    public LearnerReport(int stdNo) {
        this(stdNo, "none", "none", "none", "none",  0, 0, 0);
    }

    public LearnerReport(int stdNo, String name, String surname, String learnerAddress, String subjectName,  double test1, double test2, double test3) {
        setName(name);
        this.stdNo = stdNo;
        setSurname(surname);
        setLearnerAddress(learnerAddress);
        setSubjectName(subjectName);

        setTest1(test1);
        setTest2(test2);
        setTest3(test3);
    }

    public LearnerReport(String name, String surname, String learnerAddress, String subjectName,  double test1, double test2, double test3) {
        setName(name);

        setSurname(surname);
        setLearnerAddress(learnerAddress);
        setSubjectName(subjectName);

        setTest1(test1);
        setTest2(test2);
        setTest3(test3);
    }


    public int getStdNo() {
        return stdNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()<3)
        {
            throw new IllegalArgumentException("Name must be more than 3 words");
        }
        this.name = name;
    }

    public String getSurname() {


        return surname;
    }

    public void setSurname(String surname) {

        if(surname.length()<3)
        {
            throw new IllegalArgumentException("surname must be more than 3 words");
        }
        this.surname = surname;
    }

    public String getLearnerAddress() {
        return learnerAddress;
    }

    public void setLearnerAddress(String learnerAddress) {
        this.learnerAddress = learnerAddress;
    }

    public String getSubjectName() {


        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        if(subjectName.length()<3)
        {
            throw new IllegalArgumentException("subject Name must be more than 3 words");
        }

        this.subjectName = subjectName;
    }


    public double getTest1() {
        return test1;
    }

    public void setTest1(double test1) {
      if(test1>100 & test1<0)
      {
          throw new IllegalArgumentException("test 1 must be not be greater than 100 or less than 0");
      }

        this.test1 = test1;
    }

    public double getTest2() {



        return test2;
    }

    public void setTest2(double test2) {
        if(test2>100 & test2<0)
        {
            throw new IllegalArgumentException("test 2 must be not be greater than 100 or less than 0");
        }


        this.test2 = test2;
    }

    public double getTest3() {
        return test3;
    }

    public void setTest3(double test3) {
        if(test3>100 & test3<0)
        {
            throw new IllegalArgumentException("test 3 must be not be greater than 100 or less than 0");
        }

        this.test3 = test3;
    }



    public double calcSubjectMark(double test1,double test2,double test3) {
        return (test1 + test2 + test3) / 3;
    }



}
