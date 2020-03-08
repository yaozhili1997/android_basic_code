package com.yzl.test5;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//保存用户名和密码的业务方法
public class UserInfoUtils {
    public static boolean saveInfo(String username,String pwd){
        String result=username+"##"+pwd;
        //创建file流指定我们要把数据存储的位置
        //创建一个文件的输出1
        File file=new File("/data/data/com.yzl.test5/info.txt");
        try {
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(result.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //读取用户的信息
    public static Map<String,String> readInfo() {
        try {
            Map<String,String> maps = new HashMap<String,String>();
            File file=new File("/data/data/com.yzl.test5/info.txt");
            FileInputStream fis=new FileInputStream(file);
            BufferedReader buffer=new BufferedReader(new InputStreamReader(fis));
            String content = buffer.readLine();
            //切割字符串，封装到map集合中
            String[]split=content.split("##");
            String name=split[0];
            String pwd=split[1];
            //把name和和pwd放入map中
            maps.put("name", name);
            maps.put("pwd", pwd);
            fis.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
