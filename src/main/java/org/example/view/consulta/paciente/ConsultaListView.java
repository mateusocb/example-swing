package org.example.view.consulta.paciente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.example.dao.ConsultaDAO;
import org.example.entity.Consulta;


public class ConsultaListView extends JFrame {
    private final DefaultTableModel tableModel;
    private final ConsultaDAO consultaDAO;

    public ConsultaListView() {
        setTitle("Listagem de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(2, 1));

        consultaDAO = new ConsultaDAO();

        JButton btnShowConsultaCreateView = new JButton("Adicionar");
        btnShowConsultaCreateView.addActionListener(this::showConsultaCreateView);
        add(btnShowConsultaCreateView);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Médico");
        tableModel.addColumn("Paciente");
        tableModel.addColumn("Valor");
        JTable consultaTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(consultaTable);
        add(scrollPane, BorderLayout.CENTER);

        listConsulta();
    }

    private void listConsulta() {
        var consultas = consultaDAO.getList();

        tableModel.setRowCount(0);

        for (Consulta p : consultas) {
            tableModel.addRow(new Object[]{p.getMedico().getNome(), p.getPaciente().getNome(), p.getValor()});
        }
    }

    private void showConsultaCreateView(ActionEvent actionEvent) {
        ConsultaCreateView consultaCreateView = new ConsultaCreateView();

        consultaCreateView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                listConsulta();
            }
        });

        consultaCreateView.setVisible(true);
    }
}