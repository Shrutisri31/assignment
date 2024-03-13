package com.backend.assignment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String subject;
    private int chapters;
    private int noOfClasses;
    private String  courseType;
    private String learnMode;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public int getNoOfClasses() {
        return noOfClasses;
    }

    public void setNoOfClasses(int noOfClasses) {
        this.noOfClasses = noOfClasses;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getLearnMode() {
        return learnMode;
    }

    public void setLearnMode(String learnMode) {
        this.learnMode = learnMode;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", chapters=" + chapters +
                ", noOfClasses=" + noOfClasses +
                ", courseType='" + courseType + '\'' +
                ", learnMode='" + learnMode + '\'' +
                '}';
    }

    public Course(Long courseId, String name, String subject, int chapters, int noOfClasses, String courseType, String learnMode) {
        this.courseId = courseId;
        this.name = name;
        this.subject = subject;
        this.chapters = chapters;
        this.noOfClasses = noOfClasses;
        this.courseType = courseType;
        this.learnMode = learnMode;
    }

    public Course() {
        super();
    }
}
