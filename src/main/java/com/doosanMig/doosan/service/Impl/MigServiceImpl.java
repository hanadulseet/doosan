package com.doosanMig.doosan.service.Impl;

import com.doosanMig.doosan.model.Migration.KCS;
import com.doosanMig.doosan.model.Migration.MigrationType;
import com.doosanMig.doosan.persistence.mapper.MigMapper;
import com.doosanMig.doosan.service.MigService;
import com.doosanMig.doosan.service.SNservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MigServiceImpl implements MigService {

    private MigMapper migMapper;

    private SNservice sNservice;

    @Autowired
    public void setMigMapper(MigMapper migMapper) {
        this.migMapper = migMapper;
    }

    @Autowired
    public void setsNservice(SNservice sNservice) {
        this.sNservice = sNservice;
    }

    @Override
    public void migrationStart(MigrationType migrationType) {
        switch (migrationType) {
            case KCS -> this.KCSmigration();
            case HOWTO -> this.HOWTOmigration();
            case REFERENCE -> this.REFERENCEmigration();
            default -> {
            }
        }
    }

    private void KCSmigration() {
        List<KCS> kcsList =  migMapper.selectKCSlist();
        for(KCS kcs : kcsList){
            //kcs.getText() <- 해당 텍스트를 정규식 돌려서 이미지 주소 추출 및 업로드 진행
            //서비스 나우 등록 요청
            //로그 저장
        }
    }

    private void HOWTOmigration() {

    }

    private void REFERENCEmigration() {

    }



/*        for (MigrationType migrationType : MigrationType.values()) {
            if(migrationType.equals(MigrationType.KCS)) {
                //블라 블라
            }
        }

        //sNservice.createKBContents();
        int adminCount = migMapper.selectAdminTotalCount();
    }

    @Override
    public void testServiceNowAPI() {
        sNservice.createKBContents();
    }

    @Override
    public void serviceNowAPIupdateFile() throws Exception {
        sNservice.uploadKBAttachment();
    }
 */
}
