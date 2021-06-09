package com.app.tools.repository.type;

import com.app.tools.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface TypeRepository extends JpaRepository<Type, Integer> {

    @Query("SELECT t FROM Type t ORDER BY t.id")
    List<Type> getAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM Type t WHERE t.id=:id")
    int delete(@Param("id") int id);


}