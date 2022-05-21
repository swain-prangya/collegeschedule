package org.psms.collegeschedule.component;

import org.psms.collegeschedule.entity.Branch;
import org.psms.collegeschedule.entity.Semester;
import org.psms.collegeschedule.entity.Subject;
import org.psms.collegeschedule.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataEntry {
    private BranchRepository br;
    @Autowired
    public DataEntry(BranchRepository br) {
        this.br = br;
    }
    @Transactional
    public void subEntry()
    {
        Branch b1 = new Branch();
        b1.setBranchName("CSE");

        Semester s1=new Semester();
        s1.setSemesterName("5th");
        Subject sub1=new Subject();
        sub1.setSubjectName("Formal Language AND Automata Theory ");
        Subject sub2=new Subject();
        sub2.setSubjectName("Database Management");
        Subject sub3=new Subject();
        sub3.setSubjectName("Operating System");
        Subject sub4=new Subject();
        sub4.setSubjectName("Artificial Intelligence & ML");
        Subject sub5=new Subject();
        sub5.setSubjectName("Computer Graphics");
        s1.setSubjects(List.of(sub1,sub2,sub3,sub4,sub5).stream().map(sub->{sub.setSemester(s1);return sub;}).collect(Collectors.toList()));

        Semester s2=new Semester();
        s2.setSemesterName("7th");

        Subject su1=new Subject();
        su1.setSubjectName("Cryptography & Network Security");
        Subject su2=new Subject();
        su2.setSubjectName("Software Project Management");
        Subject su3=new Subject();
        su3.setSubjectName("Intellectual Property Rights");
        Subject su4=new Subject();
        su4.setSubjectName("Introduction to Management and Functions");
        Subject su5=new Subject();
        su5.setSubjectName("Entrepreneurship Development");
        s2.setSubjects(List.of(su1,su2,su3,su4,su5).stream().map(sub->{sub.setSemester(s2);return sub;}).collect(Collectors.toList()));

        Semester s3=new Semester();
        s3.setSemesterName("3th");

        Subject sb1=new Subject();
        sb1.setSubjectName("Math III");
        Subject sb2=new Subject();
        sb2.setSubjectName("Engineering economics");
        Subject sb3=new Subject();
        sb3.setSubjectName("Data Structure");
        Subject sb4=new Subject();
        sb4.setSubjectName("OOPS using Java");
        Subject sb5=new Subject();
        sb5.setSubjectName("Digital Logic Design");
        Subject sb6=new Subject();
        sb6.setSubjectName("Environmental Science");

        s3.setSubjects(List.of(sb1,sb2,sb3,sb4,sb5,sb6).stream().map(sub->{sub.setSemester(s3);return sub;}).collect(Collectors.toList()));


        b1.setSemesters(List.of(s2,s1,s3).stream().map(br->{br.setBranch(b1);return br;}).collect(Collectors.toList()));
        br.save(b1);
    }
}
