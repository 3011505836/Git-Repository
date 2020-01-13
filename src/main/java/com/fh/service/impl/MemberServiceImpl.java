package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.mapper.MemberMapper;
import com.fh.model.Area;
import com.fh.model.Member;
import com.fh.service.MemberService;
import com.fh.util.JwtUtil;
import com.fh.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public ServerResponse addMember(Member member,String code) {
        //会员信息非空校验
        if(member == null){
            return ServerResponse.error(ResponseEnum.MEMBER_INFO_IS_NULL);
        }

        //用户名非空校验
        if(StringUtils.isBlank(member.getName())){
            return ServerResponse.error(ResponseEnum.MEMBER_USERNAME_IS_NULL);
        }

        //密码非空校验
        if(StringUtils.isBlank(member.getPassword())){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_IS_NULL);
        }

        //手机号非空校验
        if(StringUtils.isBlank(member.getPhoneNumber())){
            return ServerResponse.error(ResponseEnum.MEMBER_PHONE_NUMBER_IS_NULL);
        }

        //手机号合法性校验
        if(member.getPhoneNumber().matches("/^1\\d{10}$/")){
            return ServerResponse.error(ResponseEnum.MEMBER_PHONE_NUMBER_IS_ILLEGAL);
        }

        //验证码非空校验
        if(StringUtils.isBlank(code)){
            return ServerResponse.error(ResponseEnum.VERIFY_CODE_IS_NULL);
        }

        //根据用户输入的手机号从redis中取出对应的验证码
        String redisCode = (String) redisTemplate.opsForValue().get(member.getPhoneNumber());
        //如果验证码过期
        if(redisCode == null){
            return ServerResponse.error(ResponseEnum.VERIFY_CODE_IS_EXPIRED);
        }

        //判断redis中取出的验证码和用户输入的验证码是否一致
        if(!redisCode.equals(code)){
            return ServerResponse.error(ResponseEnum.VERIFY_CODE_IS_ERROR);
        }

        //用户名唯一性校验
        Member memberByUsername = memberMapper.getNameIsHas(member.getName());
        if(memberByUsername != null){
            return ServerResponse.error(ResponseEnum.MEMBER_USERNAME_IS_EXISTED);
        }

        //手机号唯一性校验
        Member memberByPhoneNumber = memberMapper.getMemberIsHas(member.getPhoneNumber());
        if(memberByPhoneNumber != null){
            return ServerResponse.error(ResponseEnum.MEMBER_PHONE_NUMBER_IS_USED);
        }

        memberMapper.addMember(member);
        return ServerResponse.success();
    }


    @Override
    public ServerResponse login(Member member) {
        //登录名非空校验
        if(StringUtils.isBlank(member.getWhatever())){
            return ServerResponse.error(ResponseEnum.MEMBER_LOGINNAME_IS_NULL);
        }

        //密码非空校验
        if(StringUtils.isBlank(member.getPassword())){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_IS_NULL);
        }

        //通过登录名去查找用户名等于登录名的用户信息
        Member getMember = memberMapper.getMemberIsHas(member.getWhatever());
        if(getMember == null){
            return ServerResponse.error(ResponseEnum.MEMBER_NOT_EXISTED);
        }

        //验证用户在登录页面输入的密码是否和数据库中存储的密码一致
        if(!member.getPassword().equals(getMember.getPassword())){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_ERROR);
        }

        Member loginMember = new Member();
        loginMember.setUuid(UUID.randomUUID().toString());
        loginMember.setId(getMember.getId());
        loginMember.setName(getMember.getName());

        String token = JwtUtil.createToken(loginMember);

        /*String loginMemberJson = JSON.toJSONString(loginMember);
        String base64LoginMemberJson = Base64.getEncoder().encodeToString(loginMemberJson.getBytes());
        String secretKey = "dKskJsd23#3$%!~CS32*";
        String sign = MD5Util.md5Hex(base64LoginMemberJson + secretKey);
        String base64Sign = Base64.getEncoder().encodeToString(sign.getBytes());
        String token = base64LoginMemberJson + "." + base64Sign;*/
        //将用户的登录状态存放到redis中
        redisTemplate.opsForValue().set("member:" + loginMember.getUuid(),232324,30, TimeUnit.MINUTES);
        return ServerResponse.success(token);
    }


    @Override
    public List<Area> queryAreaByPid(Integer pid) {
        return memberMapper.queryAreaByPid(pid);
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    @Override
    public Member getMemberById(Integer id) {
        return memberMapper.getMemberById(id);
    }
}
