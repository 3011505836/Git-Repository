package com.fh.controller;

import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.model.Area;
import com.fh.model.Member;
import com.fh.service.MemberService;
import com.fh.util.AliyunSmsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("MemberController")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("sendMsg")
    public ServerResponse sendMsg(String phoneNumber){
        try {
            //判断手机号是否为空
            if (StringUtils.isBlank(phoneNumber)){
                return ServerResponse.error(ResponseEnum.PHONE_NUMBER_IS_NULL);
            }

            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
            boolean success = AliyunSmsUtil.sendSms(phoneNumber, verifyCode);
            //判断验证码是否发送成功
            if (!success){
                return ServerResponse.error();
            }
            //将生成的验证码放入redis中，过期时间设为1分钟
            redisTemplate.opsForValue().set(phoneNumber,verifyCode,1, TimeUnit.MINUTES);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }



    @RequestMapping("addMember")
    public ServerResponse addMember( Member member, String code){
        try {
            ServerResponse serverResponse = memberService.addMember(member, code);
            return serverResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }


    @RequestMapping("addLogin")
    public ServerResponse addLogin(Member member, HttpSession session){
        try {
            ServerResponse serverResponse =  memberService.login(member);
           /* if(serverResponse.getCode() == ResponseEnum.SUCCESS.getCode()){
                session.setAttribute("member",serverResponse.getData());
            }*/
            return serverResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }

    }


    //获取当前登录会员信息的API接口
    @RequestMapping("getCurrentLoginMember")
    public ServerResponse getCurrentLoginMember(HttpSession session, HttpServletRequest request){
        try {
           /* Member member = (Member) session.getAttribute("member");*/
            Member loginMember = (Member) request.getAttribute("loginMember");
            Member member = memberService.getMemberById(loginMember.getId());
            return ServerResponse.success(member);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

    //通过pid查询所有对应地区
    @RequestMapping("queryAreaByPid")
    public ServerResponse queryAreaByPid(Integer pid){
        try {
            List<Area> areaList = memberService.queryAreaByPid(pid);
            return ServerResponse.success(areaList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

    //获取当前登录会员信息的API接口
    @RequestMapping("updateMember")
    public ServerResponse updateMember(Member member){
        try {
            memberService.updateMember(member);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

}
