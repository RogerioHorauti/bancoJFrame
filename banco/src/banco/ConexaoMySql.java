package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class ConexaoMySql {
	
	public static String status = "";
	
	static Connection conn;
	
	//Metodo que retorna a conexao = cnn
    public static Connection getConnection() {
    
        String url = "jdbc:mysql://localhost/cliente";
        String usuario = "root";
        String senha = "root";

        try {
            //Carrega o driver
        	Class.forName("com.mysql.jdbc.Driver");//Driver de conexao
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
            
        } catch (SQLTimeoutException e){
        	e.printStackTrace();
        	
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
