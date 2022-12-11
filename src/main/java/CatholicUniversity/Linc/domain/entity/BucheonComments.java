package CatholicUniversity.Linc.domain.entity;

import CatholicUniversity.Linc.domain.entity.common.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class BucheonComments extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(columnDefinition = "TEXT", nullable = false, name="user_name")
    private String user;

    @OneToOne
    @JoinColumn(name="Bucheon_id")
    @JsonBackReference
    private Bucheon bucheon;

}
