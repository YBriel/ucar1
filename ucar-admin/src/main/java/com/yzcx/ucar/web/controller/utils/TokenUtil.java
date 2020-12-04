package com.yzcx.ucar.web.controller.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yzcx.ucar.common.utils.StringUtils;
import com.yzcx.ucar.web.controller.utils.entity.DevicePageInfo;
import com.yzcx.ucar.web.controller.utils.entity.DevicePosition;
import com.yzcx.ucar.web.controller.utils.entity.PageDevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.*;

/**
 * author: yuzq
 * create: 2020-11-24 17:19
 **/
@Slf4j
public class TokenUtil {

    private static RestTemplate restTemplate;
    private static String token;

    static {
        restTemplate=new RestTemplate();
    }

    public static String getToken(){
        Date date = new Date();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        Map<String,String> params=new HashMap<>();
        params.put("appid","YZZXyhbb06");
        params.put("appsecret","G4h32Y88f4ad744cD819b24Fe6eddv");
        params.put("rememberme","true");
        httpHeaders.add("timestamp",String.valueOf(date.getTime()));
        httpHeaders.add("aid","yzzx");
        String signStr="appid=YZZXyhbb06&appsecret=G4h32Y88f4ad744cD819b24Fe6eddv&rememberme=true&timestamp="+date.getTime()+"&aid=yzzxT9EG3db46c4a7683q2d42Ab5dff8F4";
        String sign =MD5Encode(signStr, "utf-8");
        httpHeaders.add("sign",sign);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(params), httpHeaders);
        token = restTemplate.postForObject("https://yhbb.hp888.com/open-api-test/customer/login?appid={appid}&appsecret={appsecret}&rememberme={rememberme}", request, String.class,params);
        JSONObject jsonObject = JSONObject.parseObject(token);
        String data = jsonObject.getString("data");
        log.info("获取到的token为{}",data);
       return data;
    }

    public static List<PageDevice> listAll(Integer pageNum,Integer pageSize){
        String url="https://yhbb.hp888.com/open-api-test/device/listAll";
        HttpHeaders httpHeaders=new HttpHeaders();
        //httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if(StringUtils.isEmpty(token)){
            token=getToken();
        }
        Map<String,String> params=new HashMap<>();
        params.put("pageNum",String.valueOf(pageNum));
        params.put("pageSize",String.valueOf(pageSize));
        String aa="eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJ5enp4Iiwic3ViIjoiWVpaWHloYmIwNiIsImlhdCI6MTYwNjIxNTYzMCwiZXhwIjoxNjA4ODA3NjMwfQ.W1q0DNEVPKIrzWLLKdZptHqJqhFV-aiwGD-89TEBx3aLZQ93Hx5NapjuOoyj6eaDYiQNmm7_SStsH-WAEQ617w";
        httpHeaders.add("Authorization","Bearer "+token);
        HttpEntity<String> request = new HttpEntity<>(null, httpHeaders);
        String  postForObject = restTemplate.postForObject(url, request, String.class);
        JSONObject jsonObject = JSONObject.parseObject(postForObject);
        List<PageDevice> data = jsonObject.getObject("data", new TypeReference<List<PageDevice>>() {});
        log.info("devicePageInfo{}", JSON.toJSONString(data));
        return data;
    }

    public static  List<DevicePosition> listCurrentPos(List<PageDevice> data){
        String url="https://yhbb.hp888.com/open-api-test/devicestatus/listCurrentPos?deviceIds={deviceIds}";
        HttpHeaders httpHeaders=new HttpHeaders();
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        httpHeaders.add("Authorization","Bearer "+token);
        String[] strings = data.stream().map(PageDevice::getId).toArray(String[]::new);
       // params.put("deviceIds", Arrays.toString(strings));

        Map<String,String> params1=new HashMap<>();
        params1.put("deviceIds",Arrays.toString(strings));
        HttpEntity<String> request = new HttpEntity<>(null, httpHeaders);
        String  postForObject = restTemplate.postForObject(url,request, String.class,params1);
        System.out.println(postForObject);
        List<DevicePosition> data1 = JSONObject.parseObject(postForObject).getObject("data", new TypeReference<List<DevicePosition>>() {
        });
        System.out.println(JSON.toJSONString(data1));
        return data1;
    }

    public List<DevicePosition> listAllDevices(){
        List<PageDevice> pageDevices = TokenUtil.listAll(1, 10);
      return  listCurrentPos(pageDevices);
    }


    public static void main(String[] args) {
        //String token = TokenUtil.getToken();
        List<PageDevice> pageDevices = TokenUtil.listAll(1, 10);
        listCurrentPos(pageDevices);
        // System.out.println(token);
    }



    public void getPositions(){

    }


    String SendStringArray() {
        Map<String, String> orgNames = new HashMap<>();
        String[] allIdArray = new String[] {"id1", "id2"};
        MultiValueMap<String, Object> convertVars = new LinkedMultiValueMap();
        convertVars.add("ids", allIdArray);
        return restTemplate.postForObject("url", convertVars, String.class);
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname != null && !"".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }
        } catch (Exception var4) {
        }

        return resultString;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = b + 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
