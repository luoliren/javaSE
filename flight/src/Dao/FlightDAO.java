package Dao;
import FlighttInfo.FlightInfo;
import User.users;

import java.sql.*;
import java.util.*;



 public class FlightDAO {
     private  Connection conn = null;
    public FlightDAO()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //加载驱动
             conn =DriverManager.getConnection("jdbc:mysql:///db4","root","990305");
        }
        catch (ClassNotFoundException e) {//捕捉处理驱动类未找到异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e) {//捕捉处理数据连接或者操作异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    //根据学号来查询学生
    public FlightInfo findStuByFlightNum(String FlightNumber)
    {
        FlightInfo flight=null;
        try{
            PreparedStatement ps=conn.prepareStatement("select * from Flight where FlightNumber=?");
            ps.setString(1, FlightNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                String InitialPoint=rs.getString(2);
                String destination=rs.getString(3);
                int price=rs.getInt(4);
                String FlightType=rs.getString(5);
                flight=new FlightInfo(FlightNumber,InitialPoint,destination,price,FlightType);
            }
            ps.close();
            //关闭SQL语句执行对象n
            conn.close();
            //关闭数据库连接对象
        }
        catch (SQLException e) {//捕捉处理数据连接或者操作异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return flight;
    }
     public static users LoginIn(String name,String password){



         //  List<users> list = null;
         try {
             Class.forName("com.mysql.jdbc.Driver");
             //加载驱动
             Connection conn1 =DriverManager.getConnection("jdbc:mysql:///db4","root","990305");
             String sql = "select * from users where username=? and password = ?";
             PreparedStatement ps = conn1.prepareStatement(sql);
             ps.setString(1, name);
             ps.setString(2, password);
             //   ps.setString(2, num);
             ResultSet rs=ps.executeQuery();
             //    users users = null;
             users users = new users();
             if (rs.next()) {

                 String username = rs.getString("username");
                 String password1 = rs.getString("password");
                //
                 // System.out.println("11111111");
                if (username !=null&&password1 !=null){
                    users.setPassword(password1);
                    users.setUsername(username);
                  //  System.out.println(password);

                    return users;
                }

             }
         } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
         }
         return null;

     }


    public ArrayList<FlightInfo> findStuByinit(String InitialPoint1)
    {
        ArrayList<FlightInfo> result=new ArrayList<FlightInfo>();
        try{
            Statement st=conn.createStatement();
            String strSQL="select * from Flight where InitialPoint like '%"+InitialPoint1+"%'";
            ResultSet rs=st.executeQuery(strSQL);
            while(rs.next())
            {
                String FlightNumber=rs.getString(1);
                String InitialPoint=rs.getString(2);
                String destination=rs.getString(3);
                int price=rs.getInt(4);
                String FlightType=rs.getString(5);
                FlightInfo stu=new FlightInfo(FlightNumber,InitialPoint,destination,price,FlightType);
                result.add(stu);
            }
            st.close();
            //关闭SQL语句执行对象
            //con.close();
            //关闭数据库连接对象
        }
        catch (SQLException e) {//捕捉处理数据连接或者操作异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public void addFlight(FlightInfo stu)
    {
        try{
            PreparedStatement ps=conn.prepareStatement("insert into Flight values(?,?,?,?,?)");
            //创建SQL语句执行对象
            ps.setString(1, stu.getFlightNumber());
            ps.setString(2,stu.getInitialPoint());
            ps.setString(3, stu.getDestination());
            ps.setInt(4,stu.getPrice());
            ps.setString(5,stu.getFlightType());
            ps.execute();
            ps.close();
            //关闭SQL语句执行对象
            //con.close();
            //关闭数据库连接对象
        }
        catch (SQLException e) {//捕捉处理数据连接或者操作异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void delFlight(String FlightNumber)
    {
        try{
            PreparedStatement ps=conn.prepareStatement("delete from Flight where FlightNumber=?");
            //创建SQL语句执行对象
            ps.setString(1, FlightNumber);
            ps.execute();
            ps.close();
            //关闭SQL语句执行对象
            //con.close();
            //关闭数据库连接对象
        }
        catch (SQLException e) {//捕捉处理数据连接或者操作异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateFlight(FlightInfo stu)
    {

        try{
            PreparedStatement ps=conn.prepareStatement("update Flight set InitialPoint=?,destination=?,price=?,FlightType=? where FlightNumber=?");
            //创建SQL语句执行对象
            ps.setString(1,stu.getInitialPoint());
            ps.setString(2, stu.getDestination());
            ps.setInt(3,stu.getPrice());
            ps.setString(4,stu.getFlightType());
            ps.setString(5, stu.getFlightNumber());
            ps.execute();
            ps.close();
            //关闭SQL语句执行对象
            //con.close();
            //关闭数据库连接对象
        }
        catch (SQLException e) {//捕捉处理数据连接或者操作异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
