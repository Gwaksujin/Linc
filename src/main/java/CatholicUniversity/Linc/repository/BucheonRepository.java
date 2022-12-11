package CatholicUniversity.Linc.repository;

import CatholicUniversity.Linc.domain.entity.Bucheon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucheonRepository extends JpaRepository<Bucheon, Long> {
    Page<Bucheon> findAllBy(Pageable pageable);
}