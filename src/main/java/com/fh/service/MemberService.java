package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.Area;
import com.fh.model.Member;

import java.util.List;

public interface MemberService {
    ServerResponse addMember(Member member, String code);

    ServerResponse login(Member member);

    List<Area> queryAreaByPid(Integer pid);

    void updateMember(Member member);

    Member getMemberById(Integer id);
}
