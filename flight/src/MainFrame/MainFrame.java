package MainFrame;

import AddOrUpdateFrame.AddOrUpdateFrame;
import Dao.FlightDAO;
import FlighttInfo.FlightInfo;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class MainFrame extends JFrame implements ActionListener
    {
        JLabel numLa,nameLa;
        JTextField numTxt,nameTxt;
        JButton numFBt,nameFBt,addBt,delBt,upBt;
        JTable table;
        JScrollPane panel;

        public MainFrame()
        {
            //this.setSize(800,600);
            this.setBounds(500,100,800,600);
            this.setTitle("欢迎进入航班信息管理");
            this.setLayout(null);
            numLa=new JLabel("航班号");
            numLa.setSize(60, 30);
            numLa.setLocation(30,30);
            this.add(numLa);
            numTxt=new JTextField();
            numTxt.setSize(150, 30);
            numTxt.setLocation(90,30);
            this.add(numTxt);
            numFBt=new JButton("航班号查找");
            numFBt.setSize(90, 30);
            numFBt.setLocation(280,30);
            numFBt.addActionListener(this);
            this.add(numFBt);

            nameLa=new JLabel("起始点");
            nameLa.setSize(60, 30);
            nameLa.setLocation(410,30);
            this.add(nameLa);
            nameTxt=new JTextField();
            nameTxt.setSize(150, 30);
            nameTxt.setLocation(470,30);
            this.add(nameTxt);
            nameFBt=new JButton("起始点查找");
            nameFBt.setSize(90, 30);
            nameFBt.setLocation(660,30);
            nameFBt.addActionListener(this);
            this.add(nameFBt);

            addBt=new JButton("增加");
            addBt.setSize(60, 30);
            addBt.setLocation(30,90);
            addBt.addActionListener(this);
            this.add(addBt);
            delBt=new JButton("删除");
            delBt.setSize(60, 30);
            delBt.setLocation(120,90);
            delBt.addActionListener(this);
            this.add(delBt);
            upBt=new JButton("修改");
            upBt.setSize(60, 30);
            upBt.setLocation(210,90);
            upBt.addActionListener(this);
            this.add(upBt);

            FlightDAO dao=new FlightDAO();
            ArrayList list=dao.findStuByinit("");
            initTable(list);



            JMenuBar menuBar = new JMenuBar();

            /*
             * 创建一级菜单
             */
            JMenu fileMenu = new JMenu("文件");
            this.add(menuBar);
            // 一级菜单添加到菜单栏
            menuBar.add(fileMenu);


            /*
             * 创建 "文件" 一级菜单的子菜单
             */
            JMenuItem newMenuItem = new JMenuItem("新建");
            JMenuItem openMenuItem = new JMenuItem("删除");
            JMenuItem exitMenuItem = new JMenuItem("修改");
            // 子菜单添加到一级菜单
            fileMenu.add(newMenuItem);
            fileMenu.add(openMenuItem);
            fileMenu.addSeparator();       // 添加一条分割线
            fileMenu.add(exitMenuItem);







            // 设置 "新建" 子菜单被点击的监听器
            newMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     table.setOpaque(false);
                     AddOrUpdateFrame newframe=new AddOrUpdateFrame("增加",null,MainFrame.this);
                }
            });
            // 设置 "删除" 子菜单被点击的监听器
            openMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JMenuItem jm = (JMenuItem) e.getSource();
                    if(jm.getText().equals("删除")){
                        if(table.getSelectedRow()==-1)
                        {
                            JOptionPane.showMessageDialog(MainFrame.this, "请选中要删除的航班");
                        }  else
                        {
                            FlightDAO dao = new FlightDAO();
                            dao.delFlight(table.getValueAt(table.getSelectedRow(),0).toString());
                            ArrayList list = dao.findStuByinit("");
                            initTable(list);
                        }
                    }
                    System.out.println(jm.getText());



                }
            });
            // 设置 "修改" 子菜单被点击的监听器
            exitMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JMenuItem jm = (JMenuItem) e.getSource();
                    if (jm.getText().equals("修改")) {
                        System.out.println(jm.getText());
                        if (table.getSelectedRow() == -1) {
                            JOptionPane.showMessageDialog(MainFrame.this, "请选中要修改的航班");
                        } else {
                            System.out.println("123");
                            int row = table.getSelectedRow();
                            String FlightNumber = table.getValueAt(row, 0).toString();
                            System.out.println(FlightNumber);
                            String InitialPoint = table.getValueAt(row, 1).toString();
                            String destination = table.getValueAt(row, 4).toString();
                            int price = Integer.parseInt(table.getValueAt(row, 3).toString());
                            String FlightType = table.getValueAt(row, 2).toString();
                            FlightInfo stu = new FlightInfo(FlightNumber, InitialPoint, destination, price, FlightType);
                            AddOrUpdateFrame newFrame = new AddOrUpdateFrame("修改", stu, MainFrame.this);
                        }
                    }
                }

            });


            this.setJMenuBar(menuBar);
            this.setVisible(true);
        }

        public void initTable(ArrayList<FlightInfo> stus)//初始化表格的方法
        {

            if((stus!=null)||(stus.size()!=0))
            {

                if(panel!=null)
                {
                    this.remove(panel);
                }
                String[] columnNames = { "航班号", "起始地", "类型", "价格", "目的地", };
                String[][] values = new String[stus.size()][5];
                for (int i = 0; i < stus.size(); i++) {
                    FlightInfo stu = (FlightInfo) stus.get(i);
                    values[i][0] = stu.getFlightNumber();
                    values[i][1] = stu.getInitialPoint();
                    values[i][2] = stu.getDestination();
                    values[i][3] = String.valueOf(stu.getPrice());
                    values[i][4] = stu.getFlightType();
                }




                ImageIcon icon = new ImageIcon("333.jpg");
                JLabel lab = new JLabel(icon); // 将图片放入到label中
                lab.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置放有图片的label的位置
                 table = new JTable(values, columnNames) { // 设置jtable的单元格为透明的
                    public Component prepareRenderer(TableCellRenderer renderer,
                                                     int row, int column) {
                        Component c = super.prepareRenderer(renderer, row, column);
                        if (c instanceof JComponent) {
                            ((JComponent) c).setOpaque(false);
                        }
                        return c;
                    }
                };



                //table = new JTable(values, columnNames);
                panel = new JScrollPane(table);
                panel.setSize(750, 400);
                panel.setLocation(20, 150);
                panel.setOpaque(true);
                this.add(panel);


                table.setOpaque(false); // 设置jtable本身为透明的
                this.getContentPane().add(lab, -1); // jframe本身是窗体，不能放置任何组件，用getContentPane()方法得到frame的默认内容面板，将lab放入其中，-1表示放入面板的下层

               // this.getContentPane().add(table, 0); // 0表示放在面板的最顶层
                this.getContentPane().add(panel, 0); // 0表示放在面板的最顶层

                Container con = this.getContentPane();
                ((JPanel)con).setOpaque(false); // 设置面板为透明的
                this.setVisible(true);
            }

        }

        public void actionPerformed(ActionEvent e)
        {
            JButton bt=(JButton)e.getSource();
            if(bt.getText().equals("起始点查找"))
            {
                FlightDAO dao=new FlightDAO();
                ArrayList list=dao.findStuByinit(nameTxt.getText().trim());
                initTable(list);


            }
            else
            {
                if(bt.getText().equals("航班号查找"))
                {
                    FlightDAO dao=new FlightDAO();
                    ArrayList list=new ArrayList();

                    FlightInfo stu=dao.findStuByFlightNum(numTxt.getText().trim());
                    if(stu!=null)
                    {
                        list.add(stu);
                    }
                    initTable(list);
                }
                else
                {
                    if(bt.getText().equals("删除"))
                    {
                        if(table.getSelectedRow()==-1)
                        {
                            JOptionPane.showMessageDialog(this, "请选中要删除的航班");
                        }
                        else
                        {
                            FlightDAO dao = new FlightDAO();
                            dao.delFlight(table.getValueAt(table.getSelectedRow(),0).toString());
                            ArrayList list = dao.findStuByinit("");
                            initTable(list);
                        }
                    }
                    else
                    {
                        if(bt.getText().equals("修改"))
                        {
                            if(table.getSelectedRow()==-1)
                            {
                                JOptionPane.showMessageDialog(this, "请选中要修改的航班");
                            }
                            else
                            {
                                int row = table.getSelectedRow();
                                String FlightNumber = table.getValueAt(row, 0).toString();
                                String InitialPoint = table.getValueAt(row, 1).toString();
                                String destination = table.getValueAt(row, 4).toString();
                                int price = Integer.parseInt(table.getValueAt(row, 3).toString());
                                String FlightType = table.getValueAt(row, 2).toString();
                                FlightInfo stu = new FlightInfo(FlightNumber, InitialPoint, destination, price, FlightType);
                                AddOrUpdateFrame newFrame = new AddOrUpdateFrame("修改", stu, this);
                            }
                        }
                        else
                        {
                            AddOrUpdateFrame newFrame=new AddOrUpdateFrame("增加",null,this);
                        }
                    }
                }
            }
        }
    }


