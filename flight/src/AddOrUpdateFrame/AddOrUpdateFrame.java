package AddOrUpdateFrame;

import Dao.FlightDAO;
import MainFrame.MainFrame;
import FlighttInfo.FlightInfo;
import javax.swing.*;
import java.awt.event.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;



    public class AddOrUpdateFrame extends JFrame implements ActionListener {

        JLabel numLa, initLa, typeLa, priceLa,deptLa;
        JTextField numTxt, initTxt, priTxt,depTxt;
        JComboBox typeCom;
        JButton bt;
        MainFrame main;//定义主窗口

        public AddOrUpdateFrame(String type, FlightInfo stu, MainFrame main)
        {
            /*this.setSize(300,400);*/
            this.setBounds(700,250,300,400);
            this.setLayout(null);
            numLa=new JLabel("航班号");
            numLa.setSize(60, 30);
            numLa.setLocation(30, 30);
            this.add(numLa);
            numTxt=new JTextField();
            numTxt.setSize(120, 30);
            numTxt.setLocation(100, 30);
            this.add(numTxt);
            initLa =new JLabel("起始地");
            initLa.setSize(60, 30);
            initLa.setLocation(30, 80);
            this.add(initLa);
            initTxt =new JTextField();
            initTxt.setSize(120, 30);
            initTxt.setLocation(100, 80);
            this.add(initTxt);
            deptLa=new JLabel("目的地");
            deptLa.setSize(60, 30);
            deptLa.setLocation(30, 130);
            this.add(deptLa);
            depTxt =new JTextField();
            depTxt.setSize(120, 30);
            depTxt.setLocation(100, 130);
            this.add(depTxt);
            priceLa =new JLabel("价格");
            priceLa.setSize(60, 30);
            priceLa.setLocation(30, 180);
            this.add(priceLa);
            priTxt =new JTextField();
            priTxt.setSize(120, 30);
            priTxt.setLocation(100, 180);
            this.add(priTxt);
            typeLa =new JLabel("类型");
            typeLa.setSize(60, 30);
            typeLa.setLocation(30, 230);
            this.add(typeLa);
            typeCom =new JComboBox();
            typeCom.setSize(120, 30);
            typeCom.setLocation(100, 230);
            typeCom.addItem("--请选择--");
            typeCom.addItem("经济舱");
            typeCom.addItem("头等舱");
            this.add(typeCom);

            if(type.equals("增加"))
            {
                this.setTitle("增加");
                bt=new JButton("增加");
                bt.setSize(60, 30);
                bt.setLocation(90,280);
            }
            else
            {
                this.setTitle("修改");
                bt=new JButton("修改");
                bt.setSize(60, 30);
                bt.setLocation(90,280);
                if(stu!=null)
                {
                    numTxt.setText(stu.getFlightNumber());
                    numTxt.setEditable(true);
                    initTxt.setText(stu.getInitialPoint());
                    typeCom.setSelectedItem(stu.getFlightType());
                    priTxt.setText(String.valueOf(stu.getPrice()));
                    depTxt.setText(stu.getDestination());
                }
            }
            this.add(bt);
            bt.addActionListener(this);
            this.setVisible(true);
            this.main=main;
        }

        public void actionPerformed(ActionEvent e)
        {
            JButton bt=(JButton)e.getSource();
            FlightDAO dao=new FlightDAO();
            String sno=numTxt.getText().trim();
            String sname= initTxt.getText().trim();
            String sex= typeCom.getSelectedItem().toString();
            int age=Integer.parseInt(priTxt.getText().trim());
            String sdept=depTxt.getText().trim();
            FlightInfo stu=new FlightInfo(sno,sname,sex,age,sdept);
            if(bt.getText().equals("修改"))
            {
                dao.updateFlight(stu);
            }
            else
            {
                dao.addFlight(stu);
            }
            ArrayList list=dao.findStuByinit("");
            main.initTable(list);
            this.dispose();

        }
    }


