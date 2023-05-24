package com.doosanMig.doosan.service;

import com.doosanMig.doosan.model.Migration.MigrationType;
import org.springframework.stereotype.Service;

@Service
public interface MigService{

    /**
     * 마이그레이션 타입별 프로세스 시작
     * @param migrationType
     * @return
     */
    void migrationStart(MigrationType migrationType);
}
