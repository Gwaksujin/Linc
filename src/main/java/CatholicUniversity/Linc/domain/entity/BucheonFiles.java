package CatholicUniversity.Linc.domain.entity;

import CatholicUniversity.Linc.domain.entity.common.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class BucheonFiles extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String originalFilename;

    @Column(nullable=false)
    private String filename;

    @Column(nullable=false)
    private String filePath;

    @OneToOne
    @JoinColumn(name="Bucheon_id")
    @JsonBackReference
    private Bucheon bucheon;



}
