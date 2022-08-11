package com.fanye.modules.sys.manage.files;

import com.fanye.modules.core.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Api(tags = {"文件上傳下載"})
@RestController
@CrossOrigin
@RequestMapping(value = "/file")
public class FileController {

    @Value("${app.upload.rootDir}")
    String rootDir;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value = "上傳文件")
    @PostMapping(value = "/upload")
    // 此处的@RequestParam中的file名应与前端upload组件中的name的值保持一致
    public Result upload(@RequestParam("file") MultipartFile multipartFile) {
        if (!this.rootDir.endsWith("/")) {
            this.rootDir += "/";
        }
        // replaceAll 用来替换windows中的\\ 为 /
        return uploadFile(multipartFile);
    }


    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadFile(HttpServletResponse response,
                                                           @RequestParam("path") String path) {
        if (!this.rootDir.endsWith("/")) {
            this.rootDir += "/";
        }

        try {
            //response.setHeader("Content-Disposition", "attachment;fileName=" + fileName); // 设置文件头
            File file = new File(this.rootDir + "/" + path);
            String fileName = file.getName();

            HttpHeaders headers = new HttpHeaders();
//            headers.add("Access-Control-Allow-Origin", "*");
//            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//            headers.add("Access-Control-Allow-Headers", "Content-Type");

            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//            headers.add("Content-Disposition", "attachment; filename=" + fileName);
            headers.add("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(System.currentTimeMillis()));

            return  ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.length())
//                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .contentType(MediaType.parseMediaType("multipart/form-data"))
                    .body(new FileSystemResource(file));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation(value = "文件下载")
    @GetMapping("/download2/{path}")
    public ResponseEntity<FileSystemResource> downloadFile2(HttpServletResponse response,
                                                           @PathVariable("path") String path) {
        if (!this.rootDir.endsWith("/")) {
            this.rootDir += "/";
        }

        try {
            String path2 = path.replaceAll("_","/");
            //response.setHeader("Content-Disposition", "attachment;fileName=" + fileName); // 设置文件头
            File file = new File(this.rootDir + "/" + path2);
            String fileName = file.getName();

            HttpHeaders headers = new HttpHeaders();
//            headers.add("Access-Control-Allow-Origin", "*");
//            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//            headers.add("Access-Control-Allow-Headers", "Content-Type");

            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment; filename=" + fileName);
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(System.currentTimeMillis()));
            return  ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new FileSystemResource(file));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "视频播放")
    @GetMapping("/video")
    public void mp4(HttpServletResponse response,
                    @RequestParam("path") String path) {
        if (!this.rootDir.endsWith("/")) {
            this.rootDir += "/";
        }
        String file = this.rootDir +path;

        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String diskfilename = "final.mp4";
            response.setContentType("video/mp4");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
            System.out.println("data.length " + data.length);
            response.setContentLength(data.length);
            response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
            OutputStream os = response.getOutputStream();

            os.write(data);
            //先声明的流后关掉！
            os.flush();
            os.close();
            inputStream.close();

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
       String file= "2021人工采集结果-上传系统0712-含直辖市(1)xlsx";
        System.out.println(file.substring(file.lastIndexOf(".")+1));
    }
    /**
     * 上传文件
     *
     * @param multipartFile
     * @return 文件存储路径
     */
    public Result uploadFile(MultipartFile multipartFile) {
        String relativeDir = simpleDateFormat.format(new Date());

        String fileName= multipartFile.getOriginalFilename();//UUID.randomUUID().toString().replace("-","");
        fileName= fileName.substring(fileName.lastIndexOf(".")+1);
        String name= UUID.randomUUID().toString().replace("-","")+"."+fileName;
        String relativeFilePath = relativeDir + name;
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        String filePath = this.rootDir + relativeFilePath;


        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Result.error("创建文件失败");
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            Result.error("保存文件失败");
        }

        return Result.success(relativeFilePath);
    }

    @RequestMapping("/test")
    public void testController(){
    }
}
