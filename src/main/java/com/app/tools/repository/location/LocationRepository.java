package com.app.tools.repository.location;

import com.app.tools.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT l FROM Location l ORDER BY l.id")
    List<Location> getAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM Location l WHERE l.id=:id")
    int delete(@Param("id") int id);


}