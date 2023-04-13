package cn.edu.xmu.blockchainandmultimedia.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkDetailedModifyVo {
    private Long workId;
    private String workName;
    private String workDescription;
    private List<AuthorVo> authors;
    private int workCategory;
    private String finishTime;
    private String finishPlace;
    private int publishStatus;
    private int rightsObtain;
    private int workNature;
    private int publicNotice;
    private int rightBelonging;
}
