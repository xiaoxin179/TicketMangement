package test;

import service.TicketService;

import java.sql.SQLException;

/**
 * @author:XIAOXIN
 * @date:2023/07/09
 **/
public class Test1 {
    public static void main(String[] args) throws SQLException {
        System.out.println(new TicketService().user_login("xiaoxin", "13044833216", "1234"));
    }
}
