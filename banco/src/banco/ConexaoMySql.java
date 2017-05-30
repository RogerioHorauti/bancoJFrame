package banco;
import java.sql.*;

public class ConexaoMySql {
	
	public static String status = "N�o conectou...";
	
	static Connection conn;
	
	//Metodo que retorna a conexao = cnn
    public static Connection getConnection() {
    
        String driver = "com.mysql.jdbc.Driver";//Driver de conexao
        String serverName = "localhost";//Servidor
        String mydatabase = "cliente";  //Base de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String usuario = "root";
        String senha = "root";

        try {
            //Carrega o driver
        	Class.forName(driver);
        	//Abrir uma nova conexao
            conn = DriverManager.getConnection(
                                    //Host
                                    url,
                                    //"jdbc:mysql://172.18.22.5/impacta",
                                    //Usuario
                                    usuario,
                                    //Senha
                                    senha
                                    //"impacta"
                                    );
            if (conn != null) { 
            	System.out.println("STATUS--->Conectado com sucesso!"); 
            } else { 
            	System.out.println("STATUS--->Nao foi possivel realizar conexao"); 
            } 

           
            
        } catch (ClassNotFoundException e) {
        	status = ("O driver expecificado nao foi encontrado.");
            //e.printStackTrace();
                
        } catch (SQLException e) {
        	status = ("Nao foi possivel conectar ao Banco de Dados.");
            //e.printStackTrace();
        }
        return conn;
    }
    
}
