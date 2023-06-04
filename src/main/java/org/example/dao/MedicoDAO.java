package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.example.config.MysqlDB;
import org.example.entity.Medico;


public class MedicoDAO {
    private final Connection conn;

    public MedicoDAO() {
        conn = MysqlDB.getConnection();
    }

    public void create(Medico medico) {
        try {
            String query = "INSERT INTO medicos (nome,crm, valor_hora) VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, medico.getNome());
            statement.setInt(2, medico.getCrm());
            statement.setDouble(3, medico.getValorHora());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int crm) {
        try {
            String query = "DELETE from medicos WHERE crm = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, crm);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(String crm, Medico medico) {
        try {
            String query = "UPDATE paciente SET nome = ? , crm = ?, valor_hora = ? WHERE crm = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, medico.getNome());
            statement.setInt(2, medico.getCrm());
            statement.setDouble(3, medico.getValorHora());
            statement.setString(4, crm);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Medico> getList() {
        String query = "SELECT * FROM medicos";
        List<Medico> medicos = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medico medico = new Medico();

                medico.setCrm(resultSet.getInt("crm"));
                medico.setValorHora(resultSet.getDouble("valor_hora"));
                medico.setNome(resultSet.getString("nome"));

                medicos.add(medico);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return medicos;
    }

    public Medico get(int crm) {
        String query = "SELECT * FROM medicos WHERE crm = ?";
        Medico medico = new Medico();

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, crm);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getInt("crm"));
                medico.setValorHora(resultSet.getDouble("valor_hora"));
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return medico;
    }
}
