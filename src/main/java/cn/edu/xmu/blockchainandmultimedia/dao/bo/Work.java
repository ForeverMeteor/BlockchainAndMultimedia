package cn.edu.xmu.blockchainandmultimedia.dao.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Data
@Builder
public class Work {
    private Long id;
    private String workName;
    private String updateTime;
    private Byte status;

    //0未上链 1已上链未生成证书 2已生成证书
    public static final int COCHAIN = 0;
    public static final int NOT_CERTIFICATED = 1;
    public static final int CERTIFICATED = 2;

}
