package org.psms.collegeschedule.restcontroller;

import org.psms.collegeschedule.entity.Branch;
import org.psms.collegeschedule.entity.Semester;
import org.psms.collegeschedule.entity.Subject;
import org.psms.collegeschedule.repository.BranchRepository;
import org.psms.collegeschedule.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("subjectRest")
public class SubjectRest {
    private BranchRepository br;
    private SemesterRepository sr;
    @Autowired
    public SubjectRest(BranchRepository br, SemesterRepository sr) {
        this.br = br;
        this.sr = sr;
    }

    @GetMapping("branches")
    List<Branch> allBranch()
    {
        return br.findAll();
    }
    @GetMapping("semester/{id}")
    List<Semester> semester(@PathVariable int id)
    {
        Branch b = br.findById(id).get();
        return b.getSemesters();
    }
    @GetMapping("subject/{id}")
    List<Subject> subject(@PathVariable int id)
    {
        Semester s = sr.findById(id).get();
        return s.getSubjects();
    }

}
