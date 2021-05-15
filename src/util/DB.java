/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import inputcentar.uposlenik;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Merisa
 */
public class DB {
      private static DB instance;
      public static DB getInstance(){
        if(instance == null){
            instance = new DB();
        }
        return instance;
    }     
      /*       private static final String url=  "jdbc:mysql://inputcentar.com:3306/inputcen_inputdb?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      */
      private static final String url=  "jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4412031";
     private static final String driverName = "com.mysql.cj.jdbc.Driver";
     private static final String username= "sql4412031";
     private static final String pass="ua3ULxcHG7";
     private static Connection con=null;
     
     public static Connection getConnection(){
         
         try{
             Class.forName(driverName);
             
             try{
                 con=DriverManager.getConnection(url, username, pass);
             }
             catch(SQLException ex){
                 System.out.println("Failed to create the database connection." + ex);
             }
         }
         catch(ClassNotFoundException ex){
             System.out.println("Greska, nije ucitana klasa... nije uredu nesto sa driverName" + ex.getMessage());
             ex.printStackTrace();
         }
         
         return con;
     }
     
     public static void UnosUposlenik(String ime,String prezime, int spol,Date datum_rodjenja, Date datum_zaposlenja, String grad, String broj_telefona, String email, String lozinka, int tim_id, int admin){
         
         Statement st = null;
         String upit = "INSERT INTO uposlenik(ime,prezime,spol,datum_rodjenja,datum_zaposlenja, grad, broj_telefona, email, lozinka, tim_id, admin) VALUES ('"+ime+"','"+prezime+"','"+spol+"','"+datum_rodjenja+"','"+datum_zaposlenja+"','"+grad+"','"+broj_telefona+"','"+email+"','"+lozinka+"','"+tim_id+"','"+admin+"');";
         try{
         st=DB.getConnection().createStatement();
         st.executeUpdate(upit);
         }
         catch(SQLException ex)
         {
             System.out.println("Greska! Nije moguce kreirati SQL recenicu");
             System.out.println(ex.getMessage());
         }
     }
     
     public static ArrayList<String[]> IspisLista(){
         Statement st=null;
         ArrayList<String[]> nizNeki=new ArrayList<>();
         String upit="SELECT I.ime,I.prezime,I.godiste,A.marka, A.tip, A.gorivo, A.broj FROM vozac as I inner join vozilo AS A on I.idVozilo = A.id ORDER BY 3 DESC";
         try{
              st=DB.getConnection().createStatement();
              ResultSet rez=st.executeQuery(upit);
              
              while(rez.next()){
                  String[] niz={rez.getString(1), rez.getString(2), String.valueOf(rez.getInt(3)), rez.getString(4), rez.getString(5), rez.getString(6), String.valueOf(rez.getInt(7))};
                  nizNeki.add(niz);
              }
         }
         catch(SQLException ex){
             
         }
         return nizNeki;
     }
     
     public static void UnosVozilo(String marka,String tip, String gorivo, int broj){
         
         Statement st = null;
         String upit = "INSERT INTO vozilo(marka,tip,gorivo,broj) VALUES ('"+marka+"','"+tip+"','"+gorivo+"','"+broj+"');";
         try{
         st=DB.getConnection().createStatement();
         st.executeUpdate(upit);
         }
         catch(SQLException ex)
         {
             System.out.println("Greska! Nije moguce kreirati SQL recenicu");
         }
     }
     
     public static void UnosDan(Date datum,Double pocetakRada, Double krajRada){
       
         Statement st = null;
         String upit = "INSERT INTO raspored(datum,pocetak_rada,kraj_rada) VALUES ('"+datum+"','"+pocetakRada+"','"+krajRada+"');";
         try{
         st=DB.getConnection().createStatement();
         st.executeUpdate(upit);
         }
         catch(SQLException ex)
         {
             System.out.println("Greska! Nije moguce kreirati SQL recenicu");
             
         }
     }
     public static void UnosTim(String naziv, String opis){
       
         Statement st = null;
         String upit = "INSERT INTO tim(naziv,opis_zadatka) VALUES ('"+naziv+"','"+opis+"');";
         try{
         st=DB.getConnection().createStatement();
         st.executeUpdate(upit);
         }
         catch(SQLException ex)
         {
             System.out.println("Greska! Nije moguce kreirati SQL recenicu");
             System.out.println(ex);
         }
     }
     
     public static void KreirajEvidenciju(int brSastanka, String email, String opis, Double vrijemeProvedeno){
         Statement st = null;
         System.out.println(brSastanka);
         String upit1 = "SELECT uposlenik_id FROM uposlenik WHERE email='"+email+"';";
         try{
             st=DB.getConnection().createStatement();
            ResultSet br=st.executeQuery(upit1);
                    int broj=-1;
            while(br.next()){
              broj=br.getInt(1);
            }
            
            
            System.out.println(broj);
                     String upit = "INSERT INTO evidencija VALUES('1','"+vrijemeProvedeno+"','"+opis+"','"+broj+"','"+brSastanka+"');";
                     try{
         st=DB.getConnection().createStatement();
         st.executeUpdate(upit);
         }
         catch(SQLException ex)
         {
             System.out.println("Greska! Nije moguce kreirati SQL recenicu");
                          System.out.println(ex);

         }

         }
         catch(SQLException ex){
             System.out.println(ex);
         }
         
     }
     
     public static ArrayList<uposlenik> IspisKorisnici(ArrayList<uposlenik> ko){
         Statement st= null;
         String upit= "Select * from uposlenik";
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
             while(rez.next()){
                 uposlenik kor= new uposlenik(rez.getString(2),rez.getString(3), rez.getInt(4), rez.getString(5), rez.getString(6), rez.getString(7), rez.getString(8),rez.getString(9),rez.getString(10),rez.getInt(11),rez.getInt(12));
                 ko.add(kor);
             }
         }
         catch(SQLException ex){
             System.out.println("Greska, recenica nije kreirana" + ex);
         }
         return ko;
     }
     
     public static ArrayList<String[]> IspisRaspored(ArrayList<String[]> ras){
         Statement st = null;
         String upit ="SELECT * FROM raspored";
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                String artkl[]= {String.valueOf(rez.getInt(1)),String.valueOf(rez.getDate(2)),String.valueOf(rez.getDouble(3)), String.valueOf(rez.getDouble(4))};
                ras.add(artkl);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return ras;
     }
     public static ArrayList<String[]> IspisTim(ArrayList<String[]> ras){
         Statement st = null;
         String upit ="SELECT * FROM tim";
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                String artkl[]= {String.valueOf(rez.getInt(1)),String.valueOf(rez.getString(2)),String.valueOf(rez.getString(3))};
                ras.add(artkl);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return ras;
     }
     
     public static ArrayList<String[]> IspisClan(ArrayList<String[]> ras){
         Statement st = null;
         String upit ="SELECT * FROM uposlenik";
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                String clan[]= {String.valueOf(rez.getString(2)),String.valueOf(rez.getString(3)), String.valueOf(rez.getInt(4)), String.valueOf(rez.getString(5)), String.valueOf(rez.getString(6)), String.valueOf(rez.getString(7)), String.valueOf(rez.getString(8)),String.valueOf(rez.getString(9)),String.valueOf(rez.getString(10)),String.valueOf(rez.getInt(11)),String.valueOf(rez.getInt(12))};
                ras.add(clan);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return ras;
     }
     
     public static ArrayList<String[]> IspisTimTabela(ArrayList<String[]> up_ev, String timID){
         Statement st = null;
         
       
         String upit = "select u.ime, u.prezime, u.email, u.grad " +
                        "from uposlenik as u " +
                        "join tim as t  on " +
                        "u.tim_id = t.tim_id " +
                        "where t.naziv = '" + timID + "'";
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                String artkl[]= {String.valueOf(rez.getString(1)),String.valueOf(rez.getString(2)),String.valueOf(rez.getString(3)),String.valueOf(rez.getString(4))};
                up_ev.add(artkl);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return up_ev;
     }
     
      public static ArrayList<String[]> IspisUpEv(ArrayList<String[]> up_ev, String em){
         Statement st = null;
         String upit ="SELECT RA.raspored_id, RA.datum, RA.pocetak_rada, EV.sati, UP.tim_id, EV.opis_rada  FROM uposlenik as UP inner join evidencija as EV on UP.uposlenik_id = EV.uposlenik_id inner join raspored as RA on EV.raspored_id = RA.raspored_id WHERE UP.email='"+em+"'";
         
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                String artkl[]= {String.valueOf(rez.getInt(1)),String.valueOf(rez.getDate(2)),String.valueOf(rez.getDouble(3)),String.valueOf(rez.getDouble(4)),String.valueOf(rez.getInt(5)),rez.getString(6)};
                up_ev.add(artkl);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return up_ev;
     }
      
      public static ArrayList<String[]> IspisEvidencija(ArrayList<String[]> up_ev){
         Statement st = null;
         String upit ="SELECT UP.ime, UP.prezime, UP.email, RA.datum, EV.sati, EV.opis_rada FROM uposlenik as UP inner join evidencija as EV on UP.uposlenik_id = EV.uposlenik_id inner join raspored as RA on EV.raspored_id = RA.raspored_id";
         
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                String artkl[]= {String.valueOf(rez.getString(1)),String.valueOf(rez.getString(2)),String.valueOf(rez.getString(3)),String.valueOf(rez.getDate(4)),String.valueOf(rez.getDouble(5)),rez.getString(6)};
                up_ev.add(artkl);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return up_ev;
     }
     /*
     public static ArrayList<vozac> IspisVozac(ArrayList<vozac> ar){
         Statement st = null;
         String upit ="SELECT * FROM vozac";
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                vozac artkl= new vozac(rez.getInt(1),rez.getString(2),rez.getString(3), rez.getInt(4), rez.getInt(5));
                ar.add(artkl);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return ar;
     } */
     
   
     // _----------------------VRACA NIZ OBJEKATA KLASE--------------------
     /*public static ArrayList<vozilo> IspisVozilo(ArrayList<vozilo> ar){
         Statement st = null;
         String upit ="SELECT * FROM vozilo";
         try{
             st=DB.getConnection().createStatement();
             ResultSet rez=st.executeQuery(upit);
            
              while(rez.next())
              {
                vozilo artkl= new vozilo(rez.getInt(1),rez.getString(2),rez.getString(3), rez.getString(4),rez.getInt(5));
                ar.add(artkl);
              }
         }
         catch(SQLException ex)
         {
             System.out.println("Greska, recenica nije kreirana"+ex);
         }
         return ar;
     } */
  /*  private static DB instance;
    private static Connection con;

    public DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wpv5?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "merisa", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static DB getInstance(){
        if(instance == null){
            instance = new DB();
        }
        return instance;
    }
    
    public Connection getConnection( ){
        return con; 
    }*/
}

