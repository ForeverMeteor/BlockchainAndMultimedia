package cn.edu.xmu.blockchainandmultimedia.service;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.WorkDetailedModifyVo;
import cn.edu.xmu.blockchainandmultimedia.dao.WorkDao;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.Work;
import cn.edu.xmu.blockchainandmultimedia.service.dto.PageDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.SimpleWorkDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.WorkDetailedDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.WorkStatusDto;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkManagementService {

    private WorkDao workDao;

    @Autowired
    public WorkManagementService(WorkDao workDao){
        this.workDao = workDao;
    }

    /**
     * 查看自己作品简略信息
     *
     * @param
     * @return
     */
    @Transactional
    public PageDto<SimpleWorkDto> retrieveWorkByUserId(Long id, Integer page, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        List<Work> works= workDao.retrieveWorkByUserId(id, pageRequest);
        List<SimpleWorkDto> simpleWorkDtos = new ArrayList<>();
        for(int i=0; i<pageSize;i++){
            Work work = works.get((page-1)*pageSize + i);

            SimpleWorkDto simpleWorkDto = new SimpleWorkDto();
            simpleWorkDto.setId(work.getId());
            simpleWorkDto.setName(work.getWorkName());
            simpleWorkDto.setUpdateTime(work.getUpdateTime());
            simpleWorkDto.setStatus(work.getStatus());

            simpleWorkDtos.add(simpleWorkDto);
        }
        return new PageDto<>(simpleWorkDtos, page, pageSize);
    }

    /**
     * 查看自己作品详细信息
     *
     * @param
     * @return
     */
    @Transactional
    public WorkDetailedDto retrieveWorkDetailedById(Long workId){
        return workDao.retrieveWorkDetailedById(workId);
    }

    /**
     * 修改自己作品详细信息
     *
     * @param
     * @return
     */
    public ReturnObject updateWorkDetailedById(Long workId, WorkDetailedModifyVo body){
        //TODO 复制为BO
        return workDao.saveWorkDetailedById(workId, BO);
    }

    /**
     * 查看自己作品证书状态
     *
     * @param
     * @return
     */
    public WorkStatusDto findCertificateStatus(Long workId){
        return workDao.findCertificateStatus(workId);
    }

    /**
     * 生成证书/查看证书
     * TODO
     * @param
     * @return
     */
    public String generateCertificate(Long workId){
        return null;
    }

}
