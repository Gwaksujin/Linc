package CatholicUniversity.Linc.domain.entity;

import CatholicUniversity.Linc.domain.entity.common.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Bucheon extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", length = 50000)// 에러로 인해 잠시 nullable = false 뺌
    private String content;

    @Column
    private Long fileId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="Bucheon_id")
    @OrderBy("id asc")
    private List<BucheonComments> bucheonCommentsList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="Bucheon_id")
    @JsonManagedReference
    private List<BucheonComments> bucheonComments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="Bucheon_id")
    private List<BucheonFiles> bucheonFiles;
}
