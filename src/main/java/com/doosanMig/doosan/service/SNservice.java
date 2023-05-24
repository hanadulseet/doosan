package com.doosanMig.doosan.service;

import org.springframework.stereotype.Service;

@Service
public interface SNservice {

    void createKBContents();

    void uploadKBAttachment() throws Exception;

}
