package org.psms.collegeschedule.repository;

import org.psms.collegeschedule.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester,Integer> {
}
