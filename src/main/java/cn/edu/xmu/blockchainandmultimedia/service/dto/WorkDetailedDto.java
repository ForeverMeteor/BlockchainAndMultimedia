package cn.edu.xmu.blockchainandmultimedia.service.dto;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.AuthorVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class WorkDetailedDto {
    private String workName;
    private String workDescription;
    private List<AuthorVo> authors;
    private Byte workCategory;
    private String finishTime;
    private String finishPlace;
    private Byte publishStatus;
    private Byte rightsObtain;
    private Byte workNature;
    private Byte publicNotice;
    private Byte rightBelonging;
}
