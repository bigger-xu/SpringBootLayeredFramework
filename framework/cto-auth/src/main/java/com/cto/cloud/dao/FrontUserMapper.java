package com.cto.cloud.dao;

import java.util.List;

import com.cto.cloud.entity.FrontUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Zhang Wei
 * @version 1.0
 * @date 2020/3/1
 */
@Repository
public class FrontUserMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public FrontUser queryFrontUserByName(String username){
        List<FrontUser> list = jdbcTemplate.query("select * from front_user where user_name = ?",new BeanPropertyRowMapper<>(FrontUser.class), username);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

   /* public int addBook(Book book) {
        return jdbcTemplate.update("INSERT INTO book(name,author) VALUES (?,?)",
                book.getName(), book.getAuthor());
    }
    public int updateBook(Book book) {
        return jdbcTemplate.update("UPDATE book SET name=?,author=? WHERE id=?",
                book.getName(), book.getAuthor(), book.getId());
    }
    public int deleteBookById(Integer id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
    public Book getBookById(Integer id) {
        return jdbcTemplate.queryForObject("select * from book where id=?",
                new BeanPropertyRowMapper<>(Book.class), id);
    }
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }*/
}
