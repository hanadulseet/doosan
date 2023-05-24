package com.doosanMig.doosan.service.Impl;

import com.doosanMig.doosan.model.SNservice.request.RequestKBcreate;
import com.doosanMig.doosan.model.SNservice.response.ResponseKBAttachment;
import com.doosanMig.doosan.model.SNservice.response.ResponseKBcreate;
import com.doosanMig.doosan.config.ExternalService;
import com.doosanMig.doosan.service.SNservice;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ResourceLoader;

@Service
public class SNserviceImpl implements SNservice {

    @Value("${external.serviceNOW.authID}")
    public String authId;

    @Value("${external.serviceNOW.authPWD}")
    public String authpwd;

    @Value("${external.serviceNOW.apiURL}")
    public String apiURL;

    private ExternalService externalService;

    @Autowired
    public void setExternalService(ExternalService externalService) {
        this.externalService = externalService;
    }

    @Autowired
    private ResourceLoader resourceLoader;

    public void createKBContents() {
        RequestKBcreate requestKBcreate = RequestKBcreate.builder()
                .article_type("html")
                .text("<p><em>***See&nbsp;<a href=\\\"javascript:if ('external' in window && 'SendSignal' in window.external) { window.external.SendSignal('302300528', 'OpenExternalArticle', 'KBA00001025'); } else {parent.DView.SendSignalToParent('F302300528', 'OpenExternalArticle', 'KBA00001025'); }\\\" target=\\\"_blank\\\">When To Create a How-To Article - Knowledge Management in SmartIT</a>&nbsp;to verify you are using the correct template.</em></p>  <p>1. Navigate to the <strong>Create New</strong> tab, select the<strong> </strong>drop-down arrow, and select <strong>Knowledge</strong>.&nbsp;</p>  <p>&nbsp;</p>  <p><img alt=\\\"test.png\\\" arattid=\\\"302290601\\\" arentryid=\\\"000000000000502\\\" arschema=\\\"RKM:HowToTemplate\\\" rkmalt=\\\"test.png\\\" rkmtitle=\\\"test.png\\\" src=\\\"\\\" style=\\\"width: 126px; height: 190px;\\\" title=\\\"test.png\\\" /></p>  <p>&nbsp;</p>  <p>&nbsp;</p>  <p><img alt=\\\"Creating How To Article.png\\\" arattid=\\\"302303571\\\" arentryid=\\\"000000000000502\\\" arschema=\\\"RKM:HowToTemplate\\\" rkmalt=\\\"Creating How To Article.png\\\" rkmtitle=\\\"Creating How To Article.png\\\" src=\\\"\\\" style=\\\"width: 432px; height: 330px;\\\" title=\\\"Creating How To Article.png\\\" /></p>  <p>&nbsp;</p>")
                .short_description("This is title of the article.")
                .valid_to("9999-05-12 06:06:47")
                .published("1999-05-12 06:06:47")
                .build();
        ResponseEntity<ResponseKBcreate> responseKBcreateResponseEntity =  externalService.post(
                apiURL +"/table/kb_template_how_to",
                getDefaultSNHeaders(MediaType.APPLICATION_JSON),
                requestKBcreate,
                ResponseKBcreate.class
                );
         String responseText = responseKBcreateResponseEntity.getBody().getResult().getNumber();
        System.out.println(responseText);
        //new ResponseEntity<>(null);
    }

    public void uploadKBAttachment() throws Exception {
        MultiValueMap<String, Object> requestbody
                = new LinkedMultiValueMap<>();
        try {
            requestbody.add("table_name", "kb_knowledge");
            requestbody.add("table_sys_id", "dfc19531bf2021003f07e2c1ac0739ab");
            requestbody.add("file", getMultipartFile().getResource());
        } catch (Exception e){
            throw new Exception();
        }

        ResponseEntity<ResponseKBAttachment> responseKBcreateResponseEntity =  externalService.post(
                apiURL +"/attachment/upload",
                getDefaultSNHeaders(MediaType.MULTIPART_FORM_DATA),
                requestbody,
                ResponseKBAttachment.class
        );
        String responseText = responseKBcreateResponseEntity.getBody().getResult().getFile_name();
        System.out.println(responseText);
        //new ResponseEntity<>(null);
    }

    public MultipartFile getMultipartFile() throws IOException {
        File file = new File(new File("").getAbsolutePath() + "/src/main/resources/imgtest.png");
        FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
            System.out.println(ex.getMessage());
        }

        MultipartFile mFile = new CommonsMultipartFile(fileItem);
        return mFile;
    }

    private HttpHeaders getDefaultSNHeaders(MediaType mediaType){
        HttpHeaders HttpHeaders = new HttpHeaders();
        HttpHeaders.setContentType(mediaType);
        HttpHeaders.setBasicAuth(authId, authpwd);
        return HttpHeaders;
    }
}
