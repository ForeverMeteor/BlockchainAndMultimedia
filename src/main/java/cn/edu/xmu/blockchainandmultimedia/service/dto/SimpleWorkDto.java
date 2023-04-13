package cn.edu.xmu.blockchainandmultimedia.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class SimpleWorkDto {
    private Long workId;
    private String name;
    private String updateTime;
    private Byte status;

    //0未上链 1已上链未生成证书 2已生成证书
    public static final int COCHAIN = 0;
    public static final int NOT_CERTIFICATED = 1;
    public static final int CERTIFICATED = 2;

}
