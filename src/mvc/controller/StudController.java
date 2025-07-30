package mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import mvc.model.Student;

public class StudController 
{
	public void insert(Student s) //one object will get inserted as a row---u
	{
		try
		{
		Connection con = ConnectionObject.getConn();		
		
		String query = "insert into studinfo values(?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, s.getRno());
		ps.setString(2, s.getName());
		
		int n = ps.executeUpdate();
		if(n==1)
		{
			JOptionPane.showMessageDialog(null, "Record inserted!!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Try again..");
		}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//update info
	public void update(Student s) throws SQLException
	{	
		try
		{
		Connection con = ConnectionObject.getConn();
		
		String query = "update studinfo set name=? where rno="+s.getRno()+"";
		PreparedStatement ps = con.prepareStatement(query);		
		
		ps.setString(1, s.getName());
		
		int n = ps.executeUpdate();
		if(n==1)
		{
			JOptionPane.showMessageDialog(null, "Information is updated!!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Try again..");
		}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	//delete record
	public void delete(Student s)
	{		
		try
		{
		Connection con = ConnectionObject.getConn();
		
		String query = "delete from studinfo where rno="+s.getRno()+"";
		PreparedStatement ps = con.prepareStatement(query);		
		
		int n = ps.executeUpdate();
		if(n==1)
		{
			JOptionPane.showMessageDialog(null, "Deleted!!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Try again..");
		}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
}
