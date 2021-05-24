package com.hx.test.dao;

import com.hx.test.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * dao
 *
 * @author hexian
 */
public interface UserJpaDao extends JpaRepository<TUser, Integer> {
}