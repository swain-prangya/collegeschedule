package org.psms.collegeschedule.restcontroller;

import org.psms.collegeschedule.entity.Course;
import org.psms.collegeschedule.entity.Teacher;
import org.psms.collegeschedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teacherRest")
public class TeacherRest {
    private BranchRepository br;
    private SubjectRepository sr;
    private SemesterRepository smr;
    private CourseRepository cr;
    private TeacherRepository tr;
    @Autowired
    public TeacherRest(BranchRepository br, SubjectRepository sr, SemesterRepository smr, CourseRepository cr, TeacherRepository tr) {
        this.br = br;
        this.sr = sr;
        this.smr = smr;
        this.cr = cr;
        this.tr = tr;
    }

    @PostMapping("teacher/{counter}")
    Teacher add(HttpServletRequest hr,@PathVariable int counter)
    {
        List<Course> courses=new ArrayList<>(counter);
        String name=hr.getParameter("name");
        for (int i = 1; i <= counter; i++) {
            Course c=new Course();
            c.setSubject(sr.findById(Integer.parseInt(hr.getParameter("subject-"+i))).get());
            courses.add(c);
        }
        Teacher t=new Teacher();
        t.setTeacherName(name);
        courses=courses.stream().map(c->{c.setTeacher(t);return c;}).collect(Collectors.toList());
        t.setCourses(courses);

        tr.save(t);
        return t;
    }
    @GetMapping("teachers")
    List<Teacher> teachers()
    {
        return tr.findAll();
    }
}
