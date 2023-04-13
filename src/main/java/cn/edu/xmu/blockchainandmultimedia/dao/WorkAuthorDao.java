package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.mapper.WorkAuthorMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkAuthorPo;
import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RefreshScope
public class WorkAuthorDao {
    private WorkAuthorMapper workAuthorMapper;

    @Autowired
    @Lazy
    public WorkAuthorDao(WorkAuthorMapper workAuthorMapper){
        this.workAuthorMapper = workAuthorMapper;
    }

    public List<WorkAuthorPo> retrieveByWorkId(Long workId){
        this.workAuthorMapper.findByAuthorId(workId);
    }
}
