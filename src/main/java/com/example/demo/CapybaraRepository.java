package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CapybaraRepository extends JpaRepository<Capybara, Long> {

    @Query("SELECT (h.cost + b.cost + l.cost) FROM Capybara c join head as h on c.head_id = h.id join body asb on c.head_id = b.id join legs as l on c.head_id = l.id WHERE c.capybaraId = :capyId")
    Integer findCapyCostById(@Param("capyId") Long userId);

}
