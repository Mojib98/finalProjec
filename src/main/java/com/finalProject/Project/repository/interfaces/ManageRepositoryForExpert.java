package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.Specialty;
import com.finalProject.Project.entity.enumeration.UserStatus;
import com.finalProject.Project.entity.enumeration.WorkStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManageRepositoryForExpert extends CrudRepository<Expert,Integer> {
    @Modifying
    @Query("update Expert set status=:status where id=:id")
    void updateWorkStatus(@Param(value = "id") Integer id, @Param(value = "status") UserStatus status);
//    List<Specialty> findAllByStatus(UserStatus userStatus );

    List<Expert> findAllByStatus(UserStatus status);

}
