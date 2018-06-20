package cn.imust.ys.springbootshiro.modules.student.repository;

import cn.imust.ys.springbootshiro.modules.student.entity.Award;
import cn.imust.ys.springbootshiro.modules.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer>{

    @Query("select awardTime from Award group by awardTime")
    List<String> groupStime();

    List<Award> findByStudent(Student student);
}
