package core.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import tools.development.Logger;

public class DataAccess {
	
	
	private static Executor executor = Executors.newCachedThreadPool() ;
	//private Connection connexion ;
	private Statement transmission ;
	private ResultSet result ;
	
	public static Object dbLock = new Object();
	public String host = "172.28.60.74";
	public String db = "testBig" ;
	public String user = "root";
	public String passwd = "mendrika";
	
	private Connection dbConnect() {
		try {
			
				Class.forName("com.mysql.jdbc.Driver");
				Connection lconnexion =  DriverManager.getConnection("jdbc:mysql://"+host+"/"+db,user,passwd);
				return lconnexion ;
			
		} catch (SQLException e) {
			Logger.error(e);
			return null ;
		} catch (ClassNotFoundException e) {
			Logger.error(e);
			return null ;
		}
		
		
	}
	
	private void update(String request) 
	{
		synchronized(dbLock){
			
			Connection connexion=dbConnect();
			try {
				transmission = connexion.createStatement() ;
				transmission.executeUpdate(request);
				connexion.close();
			} catch (SQLException e) {
				Logger.error(e,host +" : "+ request);
				
				
			}catch (NullPointerException e) {
				Logger.error(e);
			}
			finally{
				try {
					connexion.close();
				} catch (SQLException e1) {
					Logger.error(e1,host +" : "+ "aborting connexion");
				}
			}
			
		}				
		
	}


	public String requestString(String format,String table, String where, String ... columns){
		String ans = "";
		String request = "" ;
		
		synchronized(dbLock){
			
			Connection connexion = dbConnect();
			
			try {
				
				//préparation de la requête sql
				StringBuffer requestBuffer = new StringBuffer("select  ") ;			
				int  n = columns.length ;			
				int i= 0 ;			
				for(i=0 ; i<n-1 ; i++)
				{		
						requestBuffer.append(columns[i]+", ");			
				}
				requestBuffer.append(columns[n-1]+" from "+table+" where "+where);			
				request = requestBuffer.toString() +"; \n" ;
				
				//execution de la requête sql
				
				transmission = connexion.createStatement();			
				result =transmission.executeQuery(request);
				
				//traitement de la réponse
				while(result.next()){
					int colN = 1 ;
					char [] formatChars = format.toCharArray() ;
					
					for(char c : formatChars){
						if(c != '#')
						{
							ans = ans+c;
						}
						else {
							String val = result.getString(colN++);
							ans = ans+val ;
						}
					}
				}
				
			}  catch (SQLException e) {
				
				Logger.error(e,host +" : "+ request);
				
				return request + " : " + e.getMessage() ;
			} catch (NoSuchElementException e){
				
				Logger.error(e,host);
				
				
			}
			finally{
				try {
					connexion.close();
				} catch (SQLException e1) {
					Logger.error(e1,host +" : "+ "aborting connexion");
				}
			}
		}
		return ans.toString() ;
	}
	
	
	public String updateRequest(final String s){
		final String ans = s ;
		
		update(s);
		
		return ans ;
		
	}
	
	
	public DataAccess(){
		super();
		
	}
	
	
	public DataAccess(String host, String user, String pass, String database){
		super();
		
		this.host = host;
		this.db = database ;
		this.user = user;
		this.passwd = pass;
		
	}
	
	
	
}
