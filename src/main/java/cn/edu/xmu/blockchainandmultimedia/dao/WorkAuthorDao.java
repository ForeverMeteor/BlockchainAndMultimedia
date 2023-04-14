package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.WorkAuthor;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkAuthorMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkAuthorPo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RefreshScope
public class WorkAuthorDao {
    private WorkAuthorMapper workAuthorMapper;
    private AuthorDao authorDao;

    @Autowired
    @Lazy
    public WorkAuthorDao(WorkAuthorMapper workAuthorMapper,AuthorDao authorDao){
        this.workAuthorMapper = workAuthorMapper;
        this.authorDao = authorDao;
    }

    public List<Author> retrieveAuthorsByWorkId(Long workId){
        List<Author> authors = new ArrayList<>();
        List<WorkAuthorPo> workAuthorPos = workAuthorMapper.findByAuthorId(workId);
        for(WorkAuthorPo workAuthorPo : workAuthorPos){
            Author author = authorDao.findById(workAuthorPo.getAuthorId());
            authors.add(author);
        }
        return authors;
    }

    public WorkAuthorPo findByWorkIdAndAuthorId(Long workId, Long authorId){
        return this.workAuthorMapper.findByWorkIdAndAuthorId(workId, authorId);
    }

    public void insert(WorkAuthor workAuthor){
        WorkAuthorPo workAuthorPo = new WorkAuthorPo();
        BeanUtils.copyProperties(workAuthorPo, workAuthor);
        this.insert(workAuthorPo);
    }
    public void insert(WorkAuthorPo workAuthorPo){
        this.workAuthorMapper.save(workAuthorPo);
    }
}
