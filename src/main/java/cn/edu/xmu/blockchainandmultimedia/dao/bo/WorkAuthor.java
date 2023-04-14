package cn.edu.xmu.blockchainandmultimedia.dao.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Data
@Builder
public class WorkAuthor {
    private Long id;
    private Long workId;
    private Long authorId;
}
