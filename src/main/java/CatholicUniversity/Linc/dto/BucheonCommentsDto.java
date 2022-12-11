package CatholicUniversity.Linc.dto;

import CatholicUniversity.Linc.domain.entity.Bucheon;
import CatholicUniversity.Linc.domain.entity.BucheonComments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucheonCommentsDto {
    private Long id;
    private String comment;
    private String user;
    /*private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));*/
    private Bucheon bucheon;

    public BucheonComments toEntity(){
        BucheonComments comments = BucheonComments.builder()
                .id(id)
                .comment(comment)
                .user(user)
                /*.createdDate(createdDate)*/
                .bucheon(bucheon)
                .build();

        return comments;
    }
}