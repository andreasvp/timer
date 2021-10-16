package net.vpein.timer.repo;

import net.vpein.timer.domain.Metimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetimerRepository extends JpaRepository<Metimer, Long> {
    List<Metimer> findBySprecher(String Sprecher);
    List<Metimer> findByTitleContaining(String title);
}
