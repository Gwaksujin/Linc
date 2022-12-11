package CatholicUniversity.Linc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SosaController {

    /*@Autowired
    private BucheonService bucheonService;

    private final String bucheonContextPath = Bucheon.class.getName();

    @GetMapping("/main")
    public String main(){
        return "main";
    }*/

    @GetMapping("/sosa")
    public String sosa(){
        return "Sosa";
    }

    @GetMapping("/sosa/1")
    public String sosaCheck1(){
        return "SosaDetail1";
    }

    @GetMapping("/sosa/2")
    public String sosaCheck2(){
        return "SosaDetail2";
    }

    /*@PostMapping("/bucheon/{id}/comments")
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
    }*/
}
