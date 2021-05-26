package com.ling.dao;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/9
 * @ Time 8:56
 */

import com.ling.pojo.User;
import com.ling.vo.UserAuthCountVo;
import com.ling.vo.UsersVo;
import com.ling.vo.user.BlogInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepositry extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);


    /**
     * 通过<code>@Query</code>查询并返回自定义VO
     * @param roleName
     * @param nickName
     * @param
     * @return
     */
    @Query("select new com.ling.vo.UsersVo(a.id,b.roleName,a.nickname,a.avatar,a.createTime,a.updateTime,a.isSilence) from User a inner join a.roles b where b.roleName= :roleName and a.nickname like %:nickName% ")
    Page<UsersVo> findUsersPageByNicknameAndRoleName(@Param("roleName") String roleName,@Param("nickName") String nickName,Pageable pageable);

    @Query("select new com.ling.vo.UsersVo(a.id,b.roleName,a.nickname,a.avatar,a.createTime,a.updateTime,a.isSilence) from User a inner join a.roles b where a.nickname like %:nickName% ")
    Page<UsersVo> findUsersPageByNickname(@Param("nickName") String nickName,Pageable pageable);

    @Query("select new com.ling.vo.UsersVo(a.id,b.roleName,a.nickname,a.avatar,a.createTime,a.updateTime,a.isSilence) from User a inner join a.roles b where b.roleName= :roleName")
    Page<UsersVo> findUsersPageByRoleName(@Param("roleName") String roleName,Pageable pageable);

    @Query("select new com.ling.vo.UsersVo(a.id,b.roleName,a.nickname,a.avatar,a.createTime,a.updateTime,a.isSilence) from User a inner join a.roles b")
    Page<UsersVo> findAllUsersPage(Pageable pageable);

    @Query("select new com.ling.vo.UserAuthCountVo(b.roleName,count (a.nickname)) from User a inner join a.roles b group by b.roleName")
    List<UserAuthCountVo> findRoleCount();
    /**
     * jpa简单查询条件拼接
     * @param roleName
     * @param nickName
     * @param pageable
     * @return
     */
    Page<User> findAllByRolesEqualsAndNicknameLike(String roleName,String nickName,Pageable pageable);

    @Query("select new com.ling.vo.user.BlogInfoVo(a.nickname,a.avatar,a.info)from User a")
    BlogInfoVo findBlogInfo();
}
