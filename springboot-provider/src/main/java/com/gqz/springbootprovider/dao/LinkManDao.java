package com.gqz.springbootprovider.dao;

import com.gqz.springbootprovider.pojo.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-09-21 10:32
 **/
public interface LinkManDao extends JpaRepository<Linkman,Long> {
    @Override
    List<Linkman> findAll();
}
