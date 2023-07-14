package service;

import dao.UserDao;
import entity.UserEnity;
import net.sf.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private UserDao dao = new UserDao();

    //增
    public boolean addUser(String name,int age,String statue,String gender,String phone,String password)  {
        int res = 0;
        try {
            res = dao.addUser(name,age,statue,gender,phone,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res > 0;
    }

    //删
    public boolean deleteUser(int id){
        int res = 0;
        try {
            res = dao.deleteUser(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res > 0;
    }

    //改
    public boolean updateUser(int id,String name,int age,String statue,String gender,String phone){
        int res = 0;
        try {
            res = dao.updateUser(id,name,age,statue,gender,phone);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res > 0;
    }

    //查
    public String searchPageUser(String name){
        String str = "";
        try {
            List<UserEnity> list = dao.searchPageUser(name);
            Map<String,Object> map = new HashMap<>();
            map.put("rows",list);
            JSONObject json = JSONObject.fromObject(map);
            str = json.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
//    查询全部信息
public String selectAllUser(){
    String str = "";
    try {
        List<UserEnity> list = dao.selectAllUser();
        Map<String,Object> map = new HashMap<>();
        map.put("rows",list);
        JSONObject json = JSONObject.fromObject(map);
        str = json.toString();
    }catch (Exception e){
        e.printStackTrace();
    }
    return str;
}
}
