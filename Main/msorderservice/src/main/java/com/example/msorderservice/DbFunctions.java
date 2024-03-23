package com.example.msorderservice;
import com.example.msorderservice.entity.labE;
import com.example.msorderservice.models.lab;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection conn=null;
        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:32774/"+dbname,user,pass);
            if(conn!=null){
                System.out.println("connection succefully...!");
            }else{
                System.out.println("There is an error..!");
            }

        }catch (Exception e) {
            System.out.println(e);
        }
        return conn;

    }
    public void createTable(Connection conn,String table_name){
        Statement statement;
        try {
          String query ="create table "+ table_name + "(empid serial PRIMARY KEY,name VARCHAR (255),address VARCHAR (255))";
          statement=conn.createStatement();
          statement.executeUpdate(query);
          System.out.println("Table was created");
        }catch (Exception e){
           System.out.println(e);
        }

    }
    public  void insert_row(Connection conn,String table_name,String name,String address){
        Statement statement;
        try{
            String query=String.format("insert into %s (name,address) values ('%s','%s');",table_name,name,address);
            statement= conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Inserted ...!");

        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void read_data(Connection conn,String Table_name){
        Statement statement;
        ResultSet rs=null;
        try{
            String query=String.format("select * from %s",Table_name);
            statement=conn.createStatement();
            rs =statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+"  ");
                System.out.print(rs.getString("name")+"  ");
                System.out.println(rs.getString("address"));
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void update_name(Connection conn,String Table_name, String old_name, String new_name){
        Statement statement;
        try{
            String query=String.format("update %s set name='%s' where name='%s'",Table_name,new_name,old_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Date updated ..!");

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public String search_by_name(Connection conn,String Table_name,String Sname){
        Statement statement;
        ResultSet rs=null;
        try{
            String query=String.format("select * from %s where name='%s'",Table_name,Sname);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                //System.out.print(rs.getString("empid")+"  ");
                //System.out.print(rs.getString("name")+"  ");
                //System.out.println(rs.getString("address"));
                return "exist";
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return "notexist";
    }
    public void search_by_id(Connection conn,String Table_name,int id){
        Statement statement;
        ResultSet rs=null;
        try{
            String query=String.format("select * from %s where empid='%s'",Table_name,id);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+"  ");
                System.out.print(rs.getString("name")+"  ");
                System.out.println(rs.getString("address"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void Delete_row_by_id(Connection conn,String Table_name,int id){
        Statement statement;
        try{
            String query=String.format("delete from %s where empid= '%s'",Table_name,id);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data was deleted ..!");
        }catch(Exception e){
            System.out.println(e);
        }
    }


    //lab database login system
    public Map lab_email_pass_login(Connection conn,String Table_name,String email, String password) {
        //map variable to return it with the result
        Map<String, Object> map = new HashMap<>();

        Statement statement;
        //result set var to store the database results
        ResultSet rs = null;

        try {
            //check if the lab user is exist by email and password
            String query = String.format("select * from %s where l_email='%s' and l_password='%s'", Table_name, email, password);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            //check if there is a next so there is a result
            while (rs.next()) {
                //if there is a result
                //put data into the map variable
                map.put("l_id",rs.getString("l_id") );
                map.put("l_name",rs.getString("l_name") );
                map.put("l_email",rs.getString("l_email") );
                map.put("l_password",rs.getString("l_password") );
                map.put("l_phonen",rs.getString("l_phonen") );
                //exist for the checking in logincontroller
                map.put("exist","exist");
                return map;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        //if there is no result and the first return doesnt run
        map.put("exist","notexist");
        return map;
    }


    // display lab accounts from db

    public ArrayList<lab> read_labs(Connection conn){
        Statement statement;
        ResultSet rs=null;
        String Table_name = "labs";

        //make list of items
        ArrayList<lab> LabElements = new ArrayList<lab>();
        try{
            String query=String.format("select * from %s",Table_name);
            statement=conn.createStatement();
            rs =statement.executeQuery(query);
            while (rs.next()){

                //add new item
                LabElements.add(new lab(rs.getString("l_id"),rs.getString("l_name")
                        ,rs.getString("l_address"),rs.getString("l_password"),
                        rs.getString("l_email"),rs.getString("l_phonen")));
            }
            return LabElements;

        }catch (Exception e){
            System.out.println(e);
        }

        return LabElements;
    }

//get labs that have the analyse
    public ArrayList<String> read_labs_by_analyse_id(Connection conn,String analyseid) {
        Statement statement;
        ResultSet rs = null;
        String Table_name = "lanalyses";
//make list of items
        ArrayList<String> LabElements = new ArrayList<String>();

        try {
            String query = String.format("select * from %s where analyse_id='%s'", Table_name, analyseid);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                LabElements.add(rs.getString("lab_id"));
            }
            return LabElements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<labE> read_labs_name_by_id(Connection conn, ArrayList labsid) {
        Statement statement;
        ResultSet rs = null;
        String Table_name = "lab";
//make list of items
        ArrayList<labE> LabElements = new ArrayList<labE>();

        for (int i = 0; i < labsid.size(); i++) {
        try {
            System.out.printf("for loop"+ labsid.get(i));
            String query = String.format("select * from %s where l_id='%s'", Table_name, labsid.get(i));
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Long dbLid = Long.valueOf(rs.getString("l_id"));
                LabElements.add(new labE(dbLid,rs.getString("l_name")
                        ,rs.getString("l_address")," ",
                        rs.getString("l_email"),rs.getString("l_phonen")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
        return LabElements;
    }



    }

