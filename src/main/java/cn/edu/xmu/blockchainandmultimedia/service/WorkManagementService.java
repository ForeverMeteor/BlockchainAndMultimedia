package cn.edu.xmu.blockchainandmultimedia.service;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.WorkDetailedModifyVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.WorkDetailedVo;
import cn.edu.xmu.blockchainandmultimedia.dao.WorkManagementDao;
import cn.edu.xmu.blockchainandmultimedia.service.dto.PageDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.SimpleWorkDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.WorkDetailedDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.WorkStatusDto;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkManagementService {

    private WorkManagementDao workManagementDao;

    @Autowired
    public WorkManagementService(WorkManagementDao workManagementDao){
        this.workManagementDao = workManagementDao;
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
        return workManagementDao.retrieveWorkByUserId(id, pageRequest);
    }

    /**
     * 查看自己作品详细信息
     *
     * @param
     * @return
     */
    @Transactional
    public WorkDetailedDto retrieveWorkDetailedById(Long workId){
        return workManagementDao.retrieveWorkDetailedById(workId);
    }

    /**
     * 修改自己作品详细信息
     *
     * @param
     * @return
     */
    public ReturnObject updateWorkDetailedById(Long workId, WorkDetailedModifyVo body){
        //TODO 复制为BO
        return workManagementDao.saveWorkDetailedById(workId, BO);
    }

    /**
     * 查看自己作品证书状态
     *
     * @param
     * @return
     */
    public WorkStatusDto findCertificateStatus(Long workId){
        return workManagementDao.findCertificateStatus(workId);
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
