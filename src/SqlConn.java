import java.sql.*;

class SqlConn {

    public static void main(String[] args) throws SQLException {

        String rezultat = SqlConn.koneksja("select login from users", false);
        String[] arrayList = rezultat.split("/");
        for(String s: arrayList){
            System.out.println(s);
        }


    }

    public static String koneksja(String query, boolean czy_update) throws SQLException {
        String rezultat = "";
        System.out.println(query);
        try{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/repodb", "root", "");
        Statement stmt = con.createStatement();
        if (czy_update){
            stmt.executeUpdate(query);
            return null;
        }else{


            ResultSet rs = stmt.executeQuery(query);

            while(rs.next())
                rezultat = rezultat + rs.getString(1) + "/";
            con.close();
            }


            return rezultat;

    }
        catch(Exception e){ System.out.println(e);}

    return null;
}}