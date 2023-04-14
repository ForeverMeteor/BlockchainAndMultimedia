package cn.edu.xmu.blockchainandmultimedia.service;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.AuthorDetailedVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.CertificationWorkDetailedVo;
import cn.edu.xmu.blockchainandmultimedia.dao.AuthorDao;
import cn.edu.xmu.blockchainandmultimedia.dao.CochainDao;
import cn.edu.xmu.blockchainandmultimedia.dao.WorkAuthorDao;
import cn.edu.xmu.blockchainandmultimedia.dao.WorkDao;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.CertificationWorkDetailed;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.WorkAuthor;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class CertificationService {

    private CochainDao cochainDao;
    private AuthorDao authorDao;
    private WorkAuthorDao workAuthorDao;
    private WorkDao workDao;

    //
    private final RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    private static final String UPLOAD_URL = "https://xxx";
    private static final String AUTH_USER = "user";
    private static final String AUTH_PASSWORD = "password";

    @Autowired
    CertificationService(CochainDao cochainDao, AuthorDao authorDao, WorkDao workDao){
        this.cochainDao = cochainDao;
        this.authorDao = authorDao;
        this.workDao = workDao;
    }

    /**
     * 上传作品附加信息
     *
     * @param
     * @return
     */
    @Transactional
    public void uploadWorkinfo(Long id, CertificationWorkDetailedVo body){
        //其余信息sql入库
        CertificationWorkDetailed certificationWorkDetailed = CertificationWorkDetailed.builder()
                .workName(body.getWorkName())
                .workDescription(body.getWorkDescription())
                .workCategory(body.getWorkCategory())
                .finishTime(body.getFinishTime())
                .finishPlace(body.getFinishPlace())
                .publishStatus(body.getPublishStatus())
                .rightsObtain(body.getRightsObtain())
                .workNature(body.getWorkNature())
                .publicNotice(body.getPublicNotice())
                .rightBelonging(body.getRightBelonging())
                .evidence(body.getEvidence())
                .build();

        //return写死，后续重新设计
        cochainDao.insert(certificationWorkDetailed);

        for(AuthorDetailedVo authorDetailedVo : body.getAuthors()){
            Author author = new Author();
            /**
             * 抛弃了用户上传的很多信息
             */
            BeanUtils.copyProperties(author, authorDetailedVo);
            authorDao.insert(author);
            WorkAuthor workAuthor = WorkAuthor.builder()
                    .workId(999L)//写死，回头改
                    .authorId(id)
                    .build();
            workAuthorDao.insert(workAuthor);
        }

    }


    /**
     * 上传作品本体
     *
     * @param
     * @return
     */
    public void cochain(MultipartFile file){
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
        File targetFile = this.transferToFile(file);
        body.add("file",new FileSystemResource(targetFile));
        body.add("param","其他参数");

        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body,headers);

        try {
            ResponseEntity<String> response = restTemplateBuilder
                    .basicAuthentication(AUTH_USER,AUTH_PASSWORD)
                    .build()
                    .postForEntity(UPLOAD_URL,requestEntity,String.class);
            return;
            // return response.getBody();
        } catch (Exception e) {
            //log.info("上传文件失败:{}",e.getMessage());
        }

        // 上链

        // 存储文件
    }

    //辅助函数
    public File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file=File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
