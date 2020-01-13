package com.fh.mapper;

import com.fh.model.Area;
import com.fh.model.Member;

import java.util.List;

public interface MemberMapper {

    void addMember(Member member);

    Member getMemberIsHas(String whatEver);

    Member getNameIsHas(String name);

    List<Area> queryAreaByPid(Integer pid);

    void updateMember(Member member);

    Member getMemberById(Integer id);
}
