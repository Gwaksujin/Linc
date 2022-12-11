package CatholicUniversity.Linc.dto;

import CatholicUniversity.Linc.domain.entity.BucheonFiles;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BucheonFileDto {
    private Long id;
    private String originalFilename;
    private String filename;
    private String filePath;

    public BucheonFiles toEntity(){
        BucheonFiles build = BucheonFiles.builder()
                .id(id)
                .originalFilename(originalFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public BucheonFileDto(Long id, String originalFilename, String filename, String filePath){
        this.id=id;
        this.originalFilename=originalFilename;
        this.filename=filename;
        this.filePath=filePath;
    }
}