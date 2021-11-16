package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.dto.QueryUserDto;
import com.wgzhao.addax.admin.pojo.User;
import com.wgzhao.addax.admin.vo.QueryUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCustomMapper
{
    int checkUserName(String userName);

    User login(@Param("userName") String userName, @Param("userPass") String userPass);

    List<QueryUserVo> queryUserListWithPage(QueryUserDto queryUserDto);
}