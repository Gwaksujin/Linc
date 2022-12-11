package CatholicUniversity.Linc.dto;

import CatholicUniversity.Linc.domain.entity.Bucheon;
import CatholicUniversity.Linc.domain.entity.BucheonComments;
import CatholicUniversity.Linc.domain.entity.BucheonFiles;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Builder
@Data
public class BucheonDto {
    private Long id;
    private String username;

    private String password;
    private String title;
    private String content;
    private Long fileId;
    private LocalDateTime date;
    private List<BucheonComments> comments;
    private List<BucheonFiles> file;

    public Bucheon toEntity(){
        Bucheon build = Bucheon.builder()
                .id(id)
                .username(username)
                .title(title)
                .content(content)
                .fileId(fileId)
                .build();
        return build;
    }

    @Override
    public String toString() {
        return "BucheonDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", fileId=" + fileId +
                ", date=" + date +
                ", comments=" + comments +
                ", file=" + file +
                '}';
    }
}