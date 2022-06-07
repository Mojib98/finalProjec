package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Expert;
import com.finalProject.Project.entity.enumeration.UserStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ManageRepositoryForExpert extends CrudRepository<Expert,Integer>, JpaSpecificationExecutor<Expert> {
    @Modifying
    @Query("update Expert set status=:status where id=:id")
    void updateWorkStatus(@Param(value = "id") Integer id, @Param(value = "status") UserStatus status);

    List<Expert> findAllByStatus(UserStatus status);
    List<Expert> findAll(Specification<Expert> spec);
    List<Expert> findAllByTimeBetween(LocalDateTime start,LocalDateTime end);
    @Query(
            "select o from Order c inner join c.offer.expert o " +
                    "group by o having count(c) =:number"
    )
    List<Expert> findAllByNumberOfOrder(@Param(value = "number") Long number);

}
