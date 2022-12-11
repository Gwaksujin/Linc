package CatholicUniversity.Linc.controller;

import CatholicUniversity.Linc.domain.entity.Bucheon;
import CatholicUniversity.Linc.domain.entity.BucheonComments;
import CatholicUniversity.Linc.dto.BucheonCommentRequestDto;
import CatholicUniversity.Linc.dto.BucheonDto;
import CatholicUniversity.Linc.dto.BucheonFileDto;
import CatholicUniversity.Linc.service.BucheonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class BucheonController {

    @Autowired
    private BucheonService bucheonService;

    private final String bucheonContextPath = Bucheon.class.getName();

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/bucheon")
    public String bucheon(Model model, @RequestParam(value="page", defaultValue = "0") int page){

        Page<Bucheon> paging = this.bucheonService.getBucheonPage(page);
        model.addAttribute("paging", paging);

        int startPage = Math.max(1, paging.getPageable().getPageNumber() - 4);
        int endPage = Math.min(paging.getTotalPages(), paging.getPageable().getPageNumber() + 5);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "Bucheon";
    }

    @GetMapping("/bucheon/{id}")
    public String bucheonCheck(@PathVariable("id") Long id, Model model){
        BucheonDto bucheonDto = bucheonService.getBucheonById(id);
        model.addAttribute("bucheon", bucheonDto);

        List<BucheonComments> comments = bucheonDto.getComments();
        if(comments != null && !comments.isEmpty()) {
            model.addAttribute("comment", comments);
        }

        return "BucheonDetail";
    }

    @GetMapping("/bucheon/1")
    public String bucheonCheck1(){
        return "BucheonDetail1";
    }

    @GetMapping("/bucheon/2")
    public String bucheonCheck2(){
        return "BucheonDetail2";
    }



    @PostMapping("/bucheon/{id}/comments")
    public String createBucheonComments(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes, BucheonCommentRequestDto bucheonCommentRequestDto) throws IOException {

        bucheonCommentRequestDto.setComment(bucheonCommentRequestDto.getComment());
        bucheonService.createBucheonComments(id, bucheonCommentRequestDto);

        redirectAttributes.addAttribute("id", id);

        return "redirect:/bucheon/{id}";
    }

    @GetMapping("/bucheon/{id}/comments/{commentid}")
    public String deleteBucheonCommentsById(@PathVariable("id") Long id, @PathVariable("commentid") Long commentid, RedirectAttributes redirectAttributes){

        bucheonService.deleteBucheonComments(id);

        redirectAttributes.addAttribute("id", id);

        return "redirect:/bucheon/{id}";
    }

    @GetMapping("/bucheon/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        BucheonFileDto bucheonFileDto = bucheonService.getBucheonFiles(fileId);
        Path path = Paths.get(bucheonFileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(bucheonFileDto.getOriginalFilename(), "UTF-8") + "\"")
                .body(resource);
    }

    @GetMapping("/bucheon/write")
    public String getBucheonWrite(Model model, HttpSession session) {
        return "BucheonWrite";
    }

    @PostMapping("/bucheon/write")
    @Transactional
    public String createBucheon(@RequestParam MultipartFile[] files, BucheonDto bucheonDto) {

        try {
            bucheonDto.setContent(bucheonDto.getContent());

            Long bucheonId = bucheonService.createBucheon(bucheonDto);

            bucheonService.createBucheonFiles(bucheonId, files, bucheonDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/bucheon";

    }

    @GetMapping("/bucheon/update/{id}")
    public String getBucheonUpdatePage(HttpSession session, @PathVariable("id") Long id, Model model){
        BucheonDto bucheonDto = bucheonService.getBucheonById(id);

        model.addAttribute("bucheon", bucheonDto);

        return "BucheonUpdate";
    }

    @PutMapping("/bucheon/update/{id}")
    public String updateBucheon(BucheonDto bucheonDto){

        bucheonService.updateBucheon(bucheonDto);
        return "redirect:/bucheon";
    }


    @DeleteMapping("/bucheon/update/{id}")
    public String deleteBucheonById(@PathVariable("id") Long id){
        bucheonService.deleteBucheonById(id);
        return "redirect:/bucheon";
    }
}
