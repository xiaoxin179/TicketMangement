package dao;

import entity.UserEnity;
import util.JdbcUtile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public int addUser(String name,int age,String statue,String gender,String phone,String password) throws SQLException {
        int res = 0;

        Connection con = JdbcUtile.getConnection();

        String sql = "insert into user (name,age,statue,gender,phone,password) values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,name);
        ps.setInt(2,age);
        ps.setString(3,statue);
        ps.setString(4,gender);
        ps.setString(5,phone);
        ps.setString(6,password);

        res = ps.executeUpdate();
        return res;
    }


//
    public int deleteUser(int id) throws SQLException {
        int res = 0;
        Connection con = JdbcUtile.getConnection();
        String sql = "DELETE FROM `user`\n" +
                "WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        boolean bool = ps.execute();
        if (bool = true){
            res = 1;
        }
        JdbcUtile.closeConnect(con);
        return res;
    }
    public int updateUser(int id,String name,int age,String statue,String gender,String phone) throws SQLException{
        int res = 0;

        Connection con = JdbcUtile.getConnection();
        String sql = "update user set name=?,age=?,statue=?,gender=?,phone=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1,name);
        ps.setInt(2,age);
        ps.setString(3,statue);
        ps.setString(4,gender);
        ps.setString(5,phone);
        ps.setInt(6,id);

        res = ps.executeUpdate();
        JdbcUtile.closeConnect(con);
        return res;
    }

//查询全部信息
public List<UserEnity> selectAllUser() throws SQLException {
    List<UserEnity> list = new ArrayList<>();
    Connection con = JdbcUtile.getConnection();

    String sql = "SELECT id,name,age,statue,gender,phone\n" +
            "FROM `user`\n" ;
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet set = ps.executeQuery();
    while (set.next()){
        int id = set.getInt(1);
        String user_name = set.getString(2);
        int user_age = set.getInt(3);
        String user_statue = set.getString(4);
        String user_gender = set.getString(5);
        String user_phone = set.getString(6);
        list.add(new UserEnity(id,user_name,user_age,user_statue,user_gender,user_phone));
    }
    JdbcUtile.closeConnect(con);
    return list;
}
//根据用户名查找信息
    public List<UserEnity> searchPageUser(String name) throws SQLException {
        List<UserEnity> list = new ArrayList<>();
        Connection con = JdbcUtile.getConnection();

        String sql = "SELECT id,name,age,statue,gender,phone\n" +
                "FROM `user`\n" +
                "WHERE name = ?\n" ;
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1,name);
        ResultSet set = ps.executeQuery();

        while (set.next()){
            int id = set.getInt(1);
            String user_name = set.getString(2);
            int user_age = set.getInt(3);
            String user_statue = set.getString(4);
            String user_gender = set.getString(5);
            String user_phone = set.getString(6);
            list.add(new UserEnity(id,user_name,user_age,user_statue,user_gender,user_phone));
        }
        JdbcUtile.closeConnect(con);
        return list;
    }
}
