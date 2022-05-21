package org.psms.collegeschedule.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slNo;
    @JsonBackReference
    @ManyToOne
    private Routine routine;
    private String day;
    @JsonManagedReference
    @OneToMany(mappedBy = "classes")
    private List<ClassTime> classTimes;

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<ClassTime> getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(List<ClassTime> classTimes) {
        this.classTimes = classTimes;
    }
}
