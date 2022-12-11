package CatholicUniversity.Linc.repository;

import CatholicUniversity.Linc.domain.entity.BucheonFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucheonFilesRepository extends JpaRepository<BucheonFiles, Long> {
}
