package com.app.tools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.app.tools.model.Tool;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface ToolRepository extends JpaRepository<Tool, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Tool t WHERE t.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT t FROM Tool t ORDER BY t.registrationDate DESC")
    List<Tool> getAll();

    @Query("SELECT t from Tool t  WHERE ((:toolType) IS NULL OR t.type.id IN (:toolType)) " +
            "AND t.registrationDate >= :startDate AND t.registrationDate < :endDate " +
            "AND (:filteredLocation IS NULL OR t.location.id IN :filteredLocation) " +
            "ORDER BY t.registrationDate DESC")
    List<Tool> getBetween(@Param("toolType") List<Integer> toolType, @Param("filteredLocation") Integer filteredLocation,
                          @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM Tool t JOIN FETCH t.user WHERE t.id = ?1 and t.user.id = ?2")
    Tool getWithUser(int id, int userId);

}