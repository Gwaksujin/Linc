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
public class BucheonCommentRequestDto {
    private Long commentId;
    private String comment;
    private String user;
    private Bucheon bucheon;

    public BucheonComments toEntity(){
        BucheonComments comments = BucheonComments.builder()
                .id(commentId)
                .comment(comment)
                .user(user)
                .bucheon(bucheon)
                .build();

        return comments;
    }
}