package org.psms.collegeschedule.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "slNo")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slNo;
    @OneToOne
    private Subject subject;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @JsonIgnore
    @OneToOne(mappedBy = "course")
    private ClassTime classTime;
    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
