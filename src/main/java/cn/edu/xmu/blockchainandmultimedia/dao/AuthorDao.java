package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import cn.edu.xmu.blockchainandmultimedia.mapper.AuthorMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkAuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RefreshScope
public class AuthorDao {
    private AuthorMapper authorMapper;
    private WorkAuthorMapper

    @Autowired
    @Lazy
    public AuthorDao(AuthorMapper authorMapper){
        this.authorMapper = authorMapper;
    }

    public List<Author> findByWorkId(){
        return authorMapper.
    }
}

