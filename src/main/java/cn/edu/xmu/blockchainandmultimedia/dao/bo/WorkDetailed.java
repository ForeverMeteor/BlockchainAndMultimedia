package cn.edu.xmu.blockchainandmultimedia.dao.bo;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.AuthorVo;
import cn.edu.xmu.blockchainandmultimedia.dao.AuthorDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Data
@Builder
public class WorkDetailed {
    private Long id;
    private String workName;
    private String workDescription;

    @Setter
    @JsonIgnore
    private AuthorDao authorDao;

    @Setter
    private List<Author> authors;

    public List<Author> getAuthors(){
        if(null == this.authors && null != authorDao){
            this.authors = this.authorDao.findByWorkId();
        }
        return this.authors;
    }

    private Byte workCategory;
    private String finishTime;
    private String finishPlace;
    private Byte publishStatus;
    private Byte rightsObtain;
    private Byte workNature;
    private Byte publicNotice;
    private Byte rightBelonging;
}
