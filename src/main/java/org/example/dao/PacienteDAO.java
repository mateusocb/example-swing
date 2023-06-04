package org.example.dao;

import org.example.config.MysqlDB;
import org.example.entity.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PacienteDAO {
    private Connection conn;

    public PacienteDAO(){
       conn = MysqlDB.getConnection();
    }

    public void create(Paciente paciente){
        try{
            String query = "INSERT INTO paciente (nome,cpf) VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getCpf());
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(String cpf){
        try{
            String query = "DELETE from paciente WHERE cpf = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void edit(String cpf, Paciente paciente){
        try{
            String query = "UPDATE paciente SET nome = ? , cpf = ? WHERE cpf = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getCpf());
            statement.setString(3, cpf);
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Paciente> getList() {

        String query = "SELECT * FROM paciente";

        List<Paciente> resultados = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Paciente paciente = new Paciente();

                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setNome(resultSet.getString("nome"));

                resultados.add(paciente);
            }

            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }

        return resultados;
    }

    public Paciente get(String cpf){

        String query = "SELECT * from paciente where cpf = ?";

        Paciente paciente = new Paciente();

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setNome(resultSet.getString("nome"));
            }

            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }

        return paciente;
    }
}
