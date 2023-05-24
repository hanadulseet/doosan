package com.doosanMig.doosan.persistence.mapper;

import com.doosanMig.doosan.model.Migration.KCS;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MigMapper {

    /**
     * KCS 테이블 조회
     * @param
     * @return KCS테이블 내역 리스트
     */
    List<KCS> selectKCSlist();
}
