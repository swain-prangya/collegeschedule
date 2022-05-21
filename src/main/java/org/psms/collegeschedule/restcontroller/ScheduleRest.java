package org.psms.collegeschedule.restcontroller;

import org.psms.collegeschedule.entity.*;
import org.psms.collegeschedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("scheduleRest")
public class ScheduleRest {
    private TeacherRepository tr;
    private CourseRepository cr;
    private SubjectRepository sr;
    private SemesterRepository smr;
    private BranchRepository br;
    @Autowired
    public ScheduleRest(TeacherRepository tr, CourseRepository cr, SubjectRepository sr, SemesterRepository smr, BranchRepository br) {
        this.tr = tr;
        this.cr = cr;
        this.sr = sr;
        this.smr = smr;
        this.br = br;
    }
    @PostMapping("schedule/{tot}")
    Routine create(@PathVariable int tot, HttpServletRequest hr)
    {
        Routine r=new Routine();
        r.setBranchName(br.findById(Integer.parseInt(hr.getParameter("sc-branch"))).get().getBranchName());
        r.setSemesterName(smr.findById(Integer.parseInt(hr.getParameter("sc-semester"))).get().getSemesterName());
        List<Course> all = cr.findAll();
        List<Course> collect = all.stream().filter(e -> e.getSubject()
                .getSemester()
                .getBranch()
                .getBranchName()
                .equals(r.getBranchName())
                &&
                e.getSubject()
                        .getSemester()
                        .getSemesterName()
                        .equals(r.getSemesterName())
        ).collect(Collectors.toList());

        String[] day={"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
        List<Classes> ro=new ArrayList<>();

        for (String k : day)
        {
            LocalTime start=LocalTime.of(Integer.parseInt(hr.getParameter("start-time")),0);
            int dur=Integer.parseInt(hr.getParameter("duration"));
            Classes c=new Classes();
            c.setDay(k);
            List<ClassTime> list=new ArrayList<>();
            SplittableRandom sr=new SplittableRandom();
            LinkedHashSet<Integer> lhs=new LinkedHashSet<>();

            while(true)
            {
                if(lhs.size()==tot-1)
                    break;
                lhs.add(sr.nextInt(0,tot-1));

            }
            for(int i = 0,k1=0; i < tot;   i++) {
                ClassTime ct=new ClassTime();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm a");
                ct.setStartTime(start.format(df).toUpperCase());
                if(start.getHour()==Integer.parseInt(hr.getParameter("launch")))
                {
                    ct.setCourse(null);

                }else {

                    if(lhs.size()==collect.size())
                    {
//                        System.out.println("i : " +i);
                        ct.setCourse(collect.get((int)lhs.toArray()[k1++]));
                    }

                }
                start=start.plusMinutes(dur);


                ct.setEndTime(start.format(df).toUpperCase());
                list.add(ct);

            }
            c.setClassTimes(list);
            ro.add(c);
        }
        r.setClasses(ro);
        return r;
    }
}
