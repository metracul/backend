

package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CapybaraRepository extends JpaRepository<Capybara, Long> {

    @Query(value = """
        SELECT COALESCE((SELECT price FROM head WHERE id = c.head), 0) +
               COALESCE((SELECT price FROM body WHERE id = c.body), 0) +
               COALESCE((SELECT price FROM legs WHERE id = c.legs), 0)
        FROM capybara c WHERE c.id = :capyId
        """, nativeQuery = true)
    Integer findCapyCostById(@Param("capyId") Long capyId);
}
