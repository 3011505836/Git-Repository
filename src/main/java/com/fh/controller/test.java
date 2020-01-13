package com.fh.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Base64;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        String abc = "asdad";
        Base64.getEncoder().encodeToString(abc.getBytes());

    }
}
