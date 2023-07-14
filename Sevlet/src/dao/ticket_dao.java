package dao;


import entity.TicketCategory;
import util.JdbcUtile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ticket_dao {
    public static String login(String name,String phone,String password) throws SQLException {
        String res;
        Connection con = JdbcUtile.getConnection();

        String sql = "SELECT name, password, phone FROM user WHERE name = ? OR phone = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, phone);
        ResultSet rs = ps.executeQuery();
        System.out.println(name);
        System.out.println(password);
        if (rs.next()) {
            String dbPassword = rs.getString("password");
            String dbPhone = rs.getString("phone");
            System.out.println(dbPassword);
            System.out.println(dbPhone);
            if (Objects.equals(dbPassword, password)) {
                if (Objects.equals(dbPhone, phone)) {
                    res = "loginSuccess"; // 登录成功
                } else {
                    res = "手机号错误"; // 手机号错误
                }
            } else {
                res = "密码错误"; // 密码错误
            }
        } else {
            res = "用户不存在"; // 用户不存在
        }
        JdbcUtile.closeConnect(con);
        return res;
    }
    public static int register(String name,String phone,String password,int age,String gender) throws SQLException{
        int res=0;
        Connection con = JdbcUtile.getConnection();
        String sql = "insert into user (name,age,gender,statue,phone,password) value(?,?,?,1,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, gender);
        ps.setString(4, phone);
        ps.setString(5, password);
        try {
            res = ps.executeUpdate();
            System.out.println("插入成功，受影响的行数为：" + res);
        } catch (SQLException e) {
            System.out.println("插入失败，可能是该用户已经存在：" + e.getMessage());
        }
        JdbcUtile.closeConnect(con);
        return res;
    }

    //    获取总人数
    public int getAllUser() throws SQLException {
        int res=0;
        Connection con = JdbcUtile.getConnection();
        String sql="select count(*) from user";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            res = set.getInt(1);
        }
        JdbcUtile.closeConnect(con);
        return res;
    }
    //    增
    public int addTicket(String start_station,String end_station) throws SQLException {
        int res = 0;

//        获取连接
        Connection con = JdbcUtile.getConnection();
        String sql = "insert into ticket (start_station,end_station,start_time,end_time,price,residual_ticket,total_ticket)\n" +
                "values (?,?,?,?,10,200,200)";
        PreparedStatement ps = con.prepareStatement(sql);
        Date start_time = new Date();
        Date end_time = new Date();
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(start_time);
        calendar.add(Calendar.HOUR,24);
        calendar.add(Calendar.MINUTE,20);
        end_time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String str1 = format.format(start_time);
        String str2 = format.format(end_time);
        ps.setString(1,start_station);
        ps.setString(2,end_station);
        ps.setString(3,str1);
        ps.setString(4,str2);
        res = ps.executeUpdate();
        JdbcUtile.closeConnect(con);
        return res;
    }

    //    删
    public int deleteTicket(int tid) throws SQLException{
        int res = 0;
        Connection con = JdbcUtile.getConnection();
        String sql = "delete from ticket where tid=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,tid);
        boolean bool = ps.execute();
        if (bool = true){
            res = 1;
        }
        JdbcUtile.closeConnect(con);
        return res;

    }

    //    改
    public int updateTicketTime(int tid,String start_station,String end_station,int price) throws SQLException{
        int res = 0;

        Connection con = JdbcUtile.getConnection();
        String sql = "update ticket set start_station=?,end_station=?,price=? where tid=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,start_station);
        ps.setString(2,end_station);
        ps.setInt(3,price);
        ps.setInt(4,tid);

        res = ps.executeUpdate();
        JdbcUtile.closeConnect(con);
        return res;
    }

    //    分页查询  -- 搜索
    public List<TicketCategory> searchPageTicket(String go_station,String ended_station) throws SQLException {
        List<TicketCategory> list = new ArrayList<>();

        Connection con = JdbcUtile.getConnection();
        String sql = "SELECT tid,start_station,end_station,start_time,end_time,price,residual_ticket\n" +
                "FROM ticket\n" +
                "WHERE start_station = ? AND end_station = ?\n" +
                "LIMIT 0,5;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,go_station);
        ps.setString(2,ended_station);

        ResultSet set = ps.executeQuery();

        while (set.next()){
            int tid = set.getInt(1);
            String start_station = set.getString(2);
            String end_station = set.getString(3);
            String start_time = set.getString(4);
            String end_time = set.getString(5);
            int price = set.getInt(6);
            int residual_ticket = set.getInt(7);
            list.add(new TicketCategory(tid,start_station,end_station,start_time,end_time,price,residual_ticket));
        }
        JdbcUtile.closeConnect(con);
        return list;
    }

    //    查询全部
    public List<TicketCategory> selectPageTicket() throws SQLException {
        List<TicketCategory> list = new ArrayList<>();

        Connection con = JdbcUtile.getConnection();
        String sql = "SELECT tid,start_station,end_station,start_time,end_time,price,residual_ticket\n" +
                "FROM ticket;";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet set = ps.executeQuery();

        while (set.next()){
            int tid = set.getInt(1);
            String start_station = set.getString(2);
            String end_station = set.getString(3);
            String start_time = set.getString(4);
            String end_time = set.getString(5);
            int price = set.getInt(6);
            int residual_ticket = set.getInt(7);
            list.add(new TicketCategory(tid,start_station,end_station,start_time,end_time,price,residual_ticket));
        }
        JdbcUtile.closeConnect(con);
        return list;
    }


//
}
