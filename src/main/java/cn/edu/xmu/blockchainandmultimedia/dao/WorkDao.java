package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.Work;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.WorkDetailed;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkAuthorMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkDetailedMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkDetailedPo;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RefreshScope
public class WorkDao {

    private WorkMapper workMapper;
    private WorkAuthorMapper workAuthorMapper;
    private WorkDetailedMapper workDetailedMapper;
    private AuthorDao authorDao;

    @Autowired
    @Lazy
    public WorkDao(WorkMapper workMapper, WorkAuthorMapper workAuthorMapper, WorkDetailedMapper workDetailedMapper, AuthorDao authorDao){
        this.workMapper = workMapper;
        this.workAuthorMapper = workAuthorMapper;
        this.workDetailedMapper = workDetailedMapper;
        this.authorDao = authorDao;
    }

    public Work getWorkBo(WorkPo po){
        Work bo = Work.builder()
                .id(po.getId())
                .workName(po.getWorkName())
                .updateTime(po.getUpdateTime())
                .status(po.getStatus())
                .build();
        return bo;
    }

    public List<Work> retrieveWorkByAuthorId(Long authorId, PageRequest pageRequest){
        if (authorId == null)
            return null;

        List<WorkPo> workPos = workMapper.findSimpleWorkByMainAuthorId(authorId);

        List<Work> works = new ArrayList<>();
        for(WorkPo workPo : workPos){
            Work work = getWorkBo(workPo);
            works.add(work);
        }
        return works;
    }

    public WorkDetailed retrieveWorkDetailedById(Long workId){
        WorkDetailedPo workDetailedPo =  workDetailedMapper.findById(workId).get();
        return getWorkDetailedBo(workDetailedPo);
    }
}

