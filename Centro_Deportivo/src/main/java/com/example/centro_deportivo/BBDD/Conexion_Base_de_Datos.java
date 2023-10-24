package com.example.centro_deportivo.BBDD;

import com.example.centro_deportivo.controllers.Centro_DeportivoController;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * En la clase Conexion_Base_de_Datos, tenemos todos los metodos que necesitamos para comunicar con la base de datos
 */
public class Conexion_Base_de_Datos {
    private static Connection a;



    /**
     * El metodo conecta a la mysql
     * @return true o false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static boolean conectar() throws ClassNotFoundException, SQLException, IOException {
        boolean conect=false;
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");
        Class.forName("com.mysql.cj.jdbc.Driver");
        a = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
        conect=true;
        return conect;
    }

    /**
     * El metodo desconecta
     * @throws SQLException
     */
    public static void desconectar() throws SQLException {
        a.close();
    }

    /**
     * metodo que devuleve el estado de la conexion
     * @return true o false
     */
    public static boolean estadoConexion() {
        boolean ok=false;
        try {
            if (a != null && a.isValid(0)) {
                ok= true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    /**
     * metodo para saber se usuario existe en la tabla usuarios(base de datos)
     * @param x email
     * @param y contraseña
     * @return 0(nao existe), 1(email ok pero la contraseña esta incorrecta) o 2( el email y la contraseña correctos)
     */
    public static int usuario_existe(String x, String y) {
        int existe = 0;//nao existe
        try{
            Statement s=a.createStatement();
            ResultSet consulta_uno=s.executeQuery("Select * from usuarios;");//busca tudo que ha na tabela usuarios

            while(consulta_uno.next()){//enquanto volver linhas para consultar
                if (consulta_uno.getString("email").equals(x)){//se o que esta na coluna email é igual a x(pasado por parametro)
                    existe=1;//Si el usuario existe
                    if (consulta_uno.getString("contrasena").equals(y)){//se o que esta na coluna contraseña é igual a y(pasado por parametros)
                        existe=2;//Si el usuario existe y la contraseña coincide
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }

    /**
     * metodo para insertar usuarios en la tabla usuarios(base de datos)
     * @param id_usuario
     * @param email
     * @param contraseña
     * @return true o false
     */
    public static boolean insertar_usuario(int id_usuario,String email,String contraseña) {
        boolean ok=false;
        try {
            Conexion_Base_de_Datos.conectar();
            String sql="insert into usuarios values(?,?,?)";
            PreparedStatement pre = a.prepareStatement(sql);
            pre.setInt(1,id_usuario);
            pre.setString(2, email);
            pre.setString(3, contraseña);
            pre.executeUpdate();
            ok=true;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ok;
    }

    /**
     * metodo para obtener el ultimo ID del usuario que hay el la tabla usuarios(base de datos)
     * @return el ID +1
     * @throws SQLException
     */
    public static int obtenerultimoIDUsuario() throws SQLException {
        try{
            int identifica=0;
            Conexion_Base_de_Datos.conectar();
            Statement s= a.createStatement();
            String sql = "SELECT * FROM usuarios ";
            ResultSet resultado = s.executeQuery(sql);
            while (resultado.next()) {
                identifica = resultado.getInt(1)+1;
            }
            return identifica;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * metodo que devueve todos id que tengo en la tabla clientes(base de datos)
     * @return
     * @throws SQLException
     */
    public static ArrayList<Integer> obtenertodosIDUsuario() throws SQLException {
        try{
            ArrayList<Integer>numeros=new ArrayList<Integer>();
            int identifica=0;
            Conexion_Base_de_Datos.conectar();
            Statement s= a.createStatement();
            String sql = "SELECT * FROM clientes ";
            ResultSet resultado = s.executeQuery(sql);
            while (resultado.next()) {
                identifica = resultado.getInt(1);
                numeros.add(identifica);
            }
            return numeros;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * metodo para saber se email existe en la tabla usuarios(base de datos)
     * @param x passo por parametro el email
     * @return true o false
     */
    public static boolean email_existe(String x) {
        boolean existe = false;//nao existe
        try{
            Statement s=a.createStatement();
            ResultSet consulta_uno=s.executeQuery("Select * from usuarios;");//busca tudo que ha na tabela usuarios

            while(consulta_uno.next()){//enquanto volver linhas para consultar
                if (consulta_uno.getString("email").equals(x)){//se o que esta na coluna email é igual a x(pasado por parametro)
                    existe=true;//Si el usuario existe
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }

    /**
     * metodo para obtener los datos de un usuario en concreto
     * @param b paso por parametro el id del usuario
     * @return String con los datos
     * @throws SQLException
     */
    public static String obtener_Datos_Usuario(int b) throws SQLException {
        int id=Conexion_Base_de_Datos.obtenerIDUsuario(Centro_DeportivoController.getEmail());
        String resul=" ";
        try{

            if(Conexion_Base_de_Datos.obtenertodosIDUsuario().contains(id)){
                HashMap<String,String>lista= new HashMap<String,String>();
                StringBuilder stringBuilder = new StringBuilder();

                String x=" ";
                String y=" ";
                Conexion_Base_de_Datos.conectar();
                Statement s= a.createStatement();
                String sql = "Select * from clientes where Id_usuario='" + id + "';";
                ResultSet resultado = s.executeQuery(sql);
                while (resultado.next()) {

                    x=resultado.getString(2);
                    y="  -Eres un cliente "+resultado.getString(3)+
                            "\n  -Has pagado la mensualidade en dia? "+resultado.getString(4)+
                            "\n  -Tienes "+resultado.getString(5)+" dias de penalizacion"+
                            "\nSi alguna informacion no es correcta,"+
                            "\nPuedes enviar en correo al administrador, pulsando el botton abajo Enviar Email\n"+
                            "El correo es: admin@hotmail.com";

                    lista.put(x,y);

                }
                for (String chave : lista.keySet()) {
                    String valor = lista.get(chave);
                    String datos=("********************************************************"+"\nHola " + chave +" ,BienVenido"+"\nInformaciones de la matricula en el centro deportivo: " +"\n" +valor+"\n********************************************************");;
                    stringBuilder.append(datos);
                }


                resul = stringBuilder.toString();

            }else {
                resul="********************************************************"+
                        "\nNo hay informaciones."+
                        "\nPulse el boton abajo Enviar Email"+
                        "\nInforme al administrador del Centro Deportivo que no pudes ver tus informaciones"+
                        "\nEl correo es: admin@hotmail.com"+
                        "\n********************************************************";
            }

            return resul;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * metodo para obtener el id de un usuario en concretro
     * @param x paso poa parametro el email
     * @return
     * @throws SQLException
     */

    public static int obtenerIDUsuario(String x) throws SQLException {
        try{
            int identifica=0;
            Conexion_Base_de_Datos.conectar();
            Statement s= a.createStatement();
            String sql = ("SELECT * FROM usuarios where email='" + x + "';");
            ResultSet resultado = s.executeQuery(sql);
            while (resultado.next()) {
                identifica = resultado.getInt(1);
            }
            return identifica;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
