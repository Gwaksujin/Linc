package CatholicUniversity.Linc.repository;

import CatholicUniversity.Linc.domain.entity.BucheonComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BucheonCommentsRepository extends JpaRepository<BucheonComments, Long>{
}