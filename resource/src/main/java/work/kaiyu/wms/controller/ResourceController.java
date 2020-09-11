package work.kaiyu.wms.controller;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work.kaiyu.wms.domain.CommonResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @GetMapping("/ping")
    public CommonResult ping(){
        return new CommonResult(200,"PONG");
    }
    @PostMapping("/uploadAvatar")
    public CommonResult uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        if (!avatar.isEmpty()) {
            //获取上传路径
            String path = "C:\\resources\\";
            String originalFilename = avatar.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(originalFilename);
            if ("jpg".equalsIgnoreCase(prefix)) {
                log.info("jpg");
            } else if ("png".equalsIgnoreCase(prefix)) {
                log.info("png");
            } else if ("jpeg".equalsIgnoreCase(prefix)) {
                log.info("jpeg");
            } else {
                log.warn("none type");
            }
            StringBuilder newAvatarName = new StringBuilder();
            newAvatarName.append("avatar-");
            newAvatarName.append(new Date().getTime());
            newAvatarName.append(".png");
            File targetFile = new File(path, newAvatarName.toString());
            //若目标路径文件夹不存在则创建
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //上传文件到服务器
            avatar.transferTo(targetFile);
        }
        return null;
    }
    @PostMapping("/uploadFile")
    public CommonResult uploadFile(@RequestParam("avatar") MultipartFile avatar) throws IOException{
        //存储路径
        String path="C:/resources/";

        //将png图片转为jpg图片样式
        BufferedImage bufferedImage;

        //获取文件的上传流
        //byte[] fbytes=avatar.getBytes();
        //文件名称在服务器重复问题

        StringBuilder newAvatarName = new StringBuilder();
        newAvatarName.append("avatar-");
        newAvatarName.append(new Date().getTime());
        newAvatarName.append(".jpg");

        //获取文件扩展名
        String originalFilename=avatar.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(originalFilename);

        File newFilename = new File(path + newAvatarName); // 保存位置
        if(!newFilename.exists()) {
            newFilename.mkdirs();
        }

        InputStream input=avatar.getInputStream();
        if ("jpg".equalsIgnoreCase(prefix)) {
            log.info("jpg");
            try {
                Thumbnails.of(input).scale(1f).outputQuality(0.25f).toFile(newFilename);
                return new CommonResult(200,"存储并压缩成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("png".equalsIgnoreCase(prefix)) {
            log.info("png");
            bufferedImage= ImageIO.read(input);
            BufferedImage newBufferedImage=new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(),bufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE,null);
            try {
                Thumbnails.of(bufferedImage).scale(1f).outputQuality(0.3f).toFile(newFilename);
                return new CommonResult(200,"存储并压缩成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            log.warn("none type");
        }
        input.close();
        try {
            avatar.transferTo(newFilename);
            return new CommonResult(102,"存储成功");
        }catch (IOException e){
            e.printStackTrace();
            return new CommonResult(500,"存储失败");
        }
    }
}
