package entity;

import java.util.Date;

public class TicketCategory {
    private int tid;
    private String start_station;
    private String end_station;
    private String start_time;
    private String end_time;
    private int price;
    private int residual_ticket;
    private int total_ticket;

    public TicketCategory(int tid,String start_station, String end_station, String start_time, String end_time, int price, int residual_ticket) {
        this.tid = tid;
        this.start_station = start_station;
        this.end_station = end_station;
        this.start_time = start_time;
        this.end_time = end_time;
        this.price = price;
        this.residual_ticket = residual_ticket;
    }

    public TicketCategory(int price, int residual_ticket) {

    }

    public TicketCategory(String start_station, String end_station, String start_time, String end_time) {

    }

    @Override
    public String toString() {
        return "TicketCategory{" +
                "tid=" + tid +
                ", start_station='" + start_station + '\'' +
                ", end_station='" + end_station + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", price=" + price +
                ", residual_ticket=" + residual_ticket +
                ", total_ticket=" + total_ticket +
                '}';
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getStart_station() {
        return start_station;
    }

    public void setStart_station(String start_station) {
        this.start_station = start_station;
    }

    public String getEnd_station() {
        return end_station;
    }

    public void setEnd_station(String end_station) {
        this.end_station = end_station;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getResidual_ticket() {
        return residual_ticket;
    }

    public void setResidual_ticket(int residual_ticket) {
        this.residual_ticket = residual_ticket;
    }

    public int getTotal_ticket() {
        return total_ticket;
    }

    public void setTotal_ticket(int total_ticket) {
        this.total_ticket = total_ticket;
    }
}