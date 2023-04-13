package cn.edu.xmu.blockchainandmultimedia.controller;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.WorkDetailedModifyVo;
import cn.edu.xmu.blockchainandmultimedia.service.*;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/mywork", produces = "application/json;charset=UTF-8")
public class WorkManagementController {
    private WorkManagementService workManagementService;

    @Autowired
    public WorkManagementController(WorkManagementService workManagementService){
        this.workManagementService = workManagementService;
    }

    @GetMapping("/")
    public ReturnObject retrieveWorkByUserId(@PathVariable Long id) {
        return new ReturnObject(workManagementService.retrieveWorkByUserId(id, 1, 10)); //id, page, pageSize
    }

    @GetMapping("/detailed")
    public ReturnObject findWorkDetailedById(@PathVariable Long workId){
        return new ReturnObject(workManagementService.retrieveWorkDetailedById(workId)); //id, page, pageSize
    }

    @PutMapping("/detailed")
    public ReturnObject updateWorkDetailedById(
            @PathVariable Long workId,
            @RequestBody WorkDetailedModifyVo body){
        return new ReturnObject(workManagementService.updateWorkDetailedById(workId, body));
    }

    @GetMapping("/certificate/status")
    public ReturnObject findCertificateStatus(@PathVariable Long workId){
        return new ReturnObject(workManagementService.findCertificateStatus);
    }

    @PostMapping("/certificate")
    public ReturnObject generateCertificate(@PathVariable Long workId){
        return new ReturnObject(workManagementService.generateCertificate(workId));
    }
}
