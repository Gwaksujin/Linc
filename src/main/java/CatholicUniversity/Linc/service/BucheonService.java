package CatholicUniversity.Linc.service;


import CatholicUniversity.Linc.domain.entity.BucheonComments;
import CatholicUniversity.Linc.dto.BucheonCommentRequestDto;
import CatholicUniversity.Linc.repository.BucheonCommentsRepository;
import CatholicUniversity.Linc.util.MD5Generator;
import CatholicUniversity.Linc.domain.entity.Bucheon;
import CatholicUniversity.Linc.domain.entity.BucheonFiles;
import CatholicUniversity.Linc.dto.BucheonDto;
import CatholicUniversity.Linc.dto.BucheonFileDto;
import CatholicUniversity.Linc.repository.BucheonFilesRepository;
import CatholicUniversity.Linc.repository.BucheonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BucheonService {

    @Autowired
    private BucheonRepository bucheonRepository;

    @Autowired
    private BucheonFilesRepository bucheonFilesRepository;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private BucheonCommentsRepository bucheonCommentsRepository;

    // 게시판 게시글 저장
    @Transactional
    public Long createBucheon(BucheonDto bucheonDto){
        return bucheonRepository.save(bucheonDto.toEntity()).getId();
    }

    // 게시판 게시글 목록
    @Transactional
    public Page<Bucheon> getBucheonPage(int page){

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return this.bucheonRepository.findAll(pageable);
    }

    // 게시판 게시글
    @Transactional
    public BucheonDto getBucheonById(Long id){
        Bucheon bucheon = bucheonRepository.findById(id).get();

        BucheonDto bucheonDto = BucheonDto.builder()
                .id(bucheon.getId())
                .username(bucheon.getUsername())
                .title(bucheon.getTitle())
                .content(bucheon.getContent())
                .file(bucheon.getBucheonFiles())
                .date(LocalDateTime.of(bucheon.getCreatedDate().getYear(), bucheon.getCreatedDate().getMonth(), bucheon.getCreatedDate().getDate(), bucheon.getCreatedDate().getHours(), bucheon.getCreatedDate().getMinutes(), bucheon.getCreatedDate().getSeconds()))
                .build();


        return bucheonDto;
    }


    // 게시판 게시글 수정
    @Transactional
    public Long updateBucheon(BucheonDto bucheonDto){
        Bucheon bucheon = bucheonRepository.findById(bucheonDto.getId()).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        bucheon.setTitle(bucheonDto.getTitle());
        bucheon.setContent(bucheonDto.getContent());

        return bucheonRepository.save(bucheon).getId();
    }

    // 게시판 게시글 삭제
    @Transactional
    public void deleteBucheonById(Long id){
        bucheonRepository.deleteById(id);
    }


    @Transactional
    public BucheonFileDto getBucheonFiles(Long id){
        BucheonFiles bucheonFiles = bucheonFilesRepository.findById(id).get();

        BucheonFileDto bucheonFileDto = BucheonFileDto.builder()
                .id(id)
                .originalFilename(bucheonFiles.getOriginalFilename())
                .filename(bucheonFiles.getFilename())
                .filePath(bucheonFiles.getFilePath())
                .build();

        return bucheonFileDto;
    }


    @org.springframework.transaction.annotation.Transactional
    public void createBucheonFiles(Long bucheonId, MultipartFile[] files, BucheonDto request) throws IOException, NoSuchAlgorithmException {

        BucheonFiles bucheonFiles=null;

        File savingFile = null;


        for( MultipartFile multipartFile: files){

            String origFilename = multipartFile.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            String savePath = System.getProperty("user.dir") + File.separator + "files"+File.separator+ Bucheon.class.getName();
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            //1. 파일 저장
            String filePath = savePath + File.separator + filename;

            savingFile=new File(filePath);
            multipartFile.transferTo(savingFile);

            bucheonFiles=BucheonFiles.builder()
                    .bucheon(Bucheon.builder().id(bucheonId).build())
                    .filename(filename)
                    .originalFilename(origFilename)
                    .filePath(filePath)
                    .build();

            bucheonFilesRepository.save(bucheonFiles);

        }


    }

    @Transactional
    public void deleteBucheonComments(Long id){
        bucheonCommentsRepository.deleteById(id);
    }

    @Transactional
    public Long createBucheonComments(Long id, BucheonCommentRequestDto dto){
        Bucheon bucheon = bucheonRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        dto.setBucheon(bucheon);

        BucheonComments bucheonComments = dto.toEntity();
        bucheonCommentsRepository.save(bucheonComments);

        return dto.getCommentId();
    }



}