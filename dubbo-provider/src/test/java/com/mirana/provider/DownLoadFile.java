package com.mirana.provider;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownLoadFile {

    private static  String url="http://10.161.16.172/timesadmin/user/photo";


    public static  List<String> getUrls(){
        List<String> urls=new ArrayList<String>();
        for (int i=1;i<10000;i++){
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("mobile", "13366564333");
            url=url+=params;
            urls.add(url);
       }
        return urls;
        /*
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", "13366564333");
        String result = HttpClientUtil.doGet(url,params,false);
        System.out.println(result);*/
    }




}
