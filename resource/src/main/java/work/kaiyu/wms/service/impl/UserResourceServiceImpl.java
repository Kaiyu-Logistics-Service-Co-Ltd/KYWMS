package work.kaiyu.wms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import work.kaiyu.wms.dao.UserResourceDao;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.Resource;
import work.kaiyu.wms.domain.User;
import work.kaiyu.wms.service.UserResourceService;
import work.kaiyu.wms.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserResourceServiceImpl implements UserResourceService {
    @javax.annotation.Resource
    private UserResourceDao userResourceDao;
    @javax.annotation.Resource
    private UserService userService;
    @Override
    public Boolean createResource(Integer type) {
        CommonResult<User> session = userService.getSession();
        if (200==session.getCode()){
            User currentUser = session.getData();
            String relativePath ="/resources/user"+"/"+currentUser.getUserCode();
            String realPath = "C:/Users/cssly/OneDrive"+relativePath;
            try{
                File newFilename = new File(realPath);
                if(!newFilename.exists()) {
                    boolean mkdirs = newFilename.mkdirs();
                    if (!mkdirs){
                        return false;
                    }
                }
                Integer flag = userResourceDao.createResource(new Resource(type, relativePath, realPath, currentUser.getUserId()));
                if (flag==1){
                    return true;
                }else {
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean insertResource(Integer type, MultipartFile file) {
        CommonResult<User> session = userService.getSession();
        if (200==session.getCode()){
            User currentUser = session.getData();
            StringBuilder relativePath = new StringBuilder();
            StringBuilder realPath = new StringBuilder();
            relativePath.append("/resources/user").append("/").append(currentUser.getUserCode());
            realPath.append("C:/Users/cssly/OneDrive").append(relativePath);
            try{
                if (!file.isEmpty()) {
                    String originalFilename = file.getOriginalFilename();
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
                    String newAvatarName = "avatar.png";
                    File targetFile = new File(realPath.toString(), newAvatarName);
                    if (!targetFile.exists()) {
                        boolean mkdir = targetFile.mkdir();
                        if (!mkdir){
                            return false;
                        }
                    }
                    file.transferTo(targetFile);
                    relativePath.append("/").append(newAvatarName);
                    realPath.append("/").append(newAvatarName);
                    if (userResourceDao.selectOneResource(1,currentUser.getUserId())!=null){
                        log.info("update");
                        return true;
                    }else {
                        log.info("insert");
                        Integer flag = userResourceDao.createResource(new Resource(type, relativePath.toString(), realPath.toString(), currentUser.getUserId()));
                        if (flag==1){
                            return true;
                        }else {
                            return false;
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Integer updateResource(Resource resource) {
        return null;
    }

    @Override
    public Integer deleteResource(Resource resource) {
        return null;
    }

    @Override
    public Resource selectOneResource(Integer resourceType) {
        CommonResult<User> session = userService.getSession();
        if (200==session.getCode()){
            User currentUser = session.getData();
            try{
                Resource resource = userResourceDao.selectOneResource(resourceType, currentUser.getUserId());
                return resource;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Resource> selectResourceList(Integer resourceType, Long resourceBy) {
        return null;
    }
}
