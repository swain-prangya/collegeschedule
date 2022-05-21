package org.psms.collegeschedule.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class ClassTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slNo;
    private String startTime;
    private String endTime;
//    @JsonManagedReference
    @OneToOne
    private Course course;
    @JsonBackReference
    @ManyToOne
    private Classes classes;

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
