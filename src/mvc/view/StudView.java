package mvc.view;

import mvc.model.Student;
import mvc.controller.StudController;
import mvc.controller.ConnectionObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudView {
    JFrame f;
    JTextField txt1, txt2;
    JLabel lbl1, lbl2;
    JButton bt1, bt2, bt3, bt4;
    JTable tbl;
    DefaultTableModel dtbl;
    JScrollPane sp;
    public JComboBox<String> ls;

    public StudView() throws ClassNotFoundException, SQLException {
        f = new JFrame("Student Form");

        lbl1 = new JLabel("Roll No");
        lbl2 = new JLabel("Student Name");
        txt1 = new JTextField(10);
        txt2 = new JTextField(10);
        bt1 = new JButton("Insert");
        bt2 = new JButton("Update");
        bt3 = new JButton("Delete");
        bt4 = new JButton("Select");

        dtbl = new DefaultTableModel();
        String[] colnm = {"Roll No", "Student Name"};
        dtbl.setColumnIdentifiers(colnm);
        tbl = new JTable(dtbl);
        sp = new JScrollPane(tbl);

        ls = new JComboBox<>();
        ls.addItem("Select");

        f.add(lbl1);
        f.add(txt1);
        f.add(lbl2);
        f.add(txt2);
        f.add(bt1);
        f.add(bt2);
        f.add(bt3);
        f.add(bt4);
        f.add(sp);
        f.add(ls);

        f.setLayout(new FlowLayout());
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StudController controller = new StudController();

        // INSERT
        bt1.addActionListener(e -> {
            int rno = Integer.parseInt(txt1.getText());
            String name = txt2.getText();
            Student s = new Student(rno, name);
            try {
                controller.insert(s);
                loadInfo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // UPDATE
        bt2.addActionListener(e -> {
            int rno = Integer.parseInt(txt1.getText());
            String name = txt2.getText();
            Student s = new Student(rno, name);
            try {
                controller.update(s);
                loadInfo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // DELETE
        bt3.addActionListener(e -> {
            int rno = Integer.parseInt(txt1.getText());
            String name = txt2.getText();
            Student s = new Student(rno, name);
            try {
                controller.delete(s);
                loadInfo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // SELECT/REFRESH
        bt4.addActionListener(e -> {
            try {
                loadInfo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // COMBOBOX ITEM SELECT
        ls.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                String data = ls.getSelectedItem().toString();
                if (data.equals("Select")) {
                    try {
                        loadInfo();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    int r = Integer.parseInt(data);
                    try (Connection con = ConnectionObject.getConn();
                         Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM studinfo WHERE rno = " + r)) {

                        dtbl.setRowCount(0);
                        while (rs.next()) {
                            dtbl.addRow(new Object[]{rs.getInt(1), rs.getString(2)});
                        }
                        JOptionPane.showMessageDialog(null, "Filtered data loaded.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Load table + combo data initially
        loadInfo();
        loadComboBox();
    }

    // Loads all records into JTable
    public void loadInfo() throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionObject.getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM studInfo")) {

            dtbl.setRowCount(0);
            while (rs.next()) {
                dtbl.addRow(new Object[]{rs.getInt(1), rs.getString(2)});
            }
        }
    }

    // Loads roll numbers into combo box
    public void loadComboBox() throws SQLException, ClassNotFoundException {
        try (Connection conn = ConnectionObject.getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT rno FROM studInfo")) {

            ls.removeAllItems();
            ls.addItem("Select");
            while (rs.next()) {
                ls.addItem(String.valueOf(rs.getInt(1)));
            }
        }
    }
}
