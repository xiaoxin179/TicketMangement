package service;



import dao.ticket_dao;
import entity.TicketCategory;
import net.sf.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TicketService {
    private ticket_dao dao = new ticket_dao();
//    注册
    public boolean user_register(String name,String phone,String password,int age,String gender){
        int res=0;
        try {
            res = dao.register(name,phone,password,age,gender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res > 0;
    }
//    登录
    public String user_login(String name,String phone,String password) throws SQLException{
        String res = null;
        try {
            res = dao.login(name,phone,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public int totalUser() {
        int res = 0;
        try{
            res = dao.getAllUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }
    //增
    public boolean addTicket(String start_station,String end_station){
        int res = 0;
        try {
            res = dao.addTicket(start_station,end_station);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  res > 0;
    }

    //删
    public boolean deleteTicket(int tid){
        int res = 0;
        try {
            res = dao.deleteTicket(tid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  res > 0;
    }

    //改
    public boolean updateTicket(int tid,String start_station,String end_station,int price){
        int res = 0;
        try {
            res = dao.updateTicketTime(tid,start_station,end_station,price);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  res > 0;
    }

    //s搜
    public String ticketsearch( String start_station_name,String end_station_name){
        String str = "";
        try {
            List<TicketCategory> list = dao.searchPageTicket( start_station_name,end_station_name);
            Map<String,Object> map = new HashMap<>();
            map.put("rows",list);
            JSONObject json = JSONObject.fromObject(map);
            str = json.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
    //查
    public String selectTicketService(){
        String str = "";
        try {
            List<TicketCategory> list = dao.selectPageTicket();
            Map<String,Object> map = new HashMap<>();
            map.put("rows",list);
            JSONObject json = JSONObject.fromObject(map);
            str = json.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}

