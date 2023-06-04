package org.example.view.paciente;

import org.example.dao.PacienteDAO;
import org.example.entity.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;


public class PacienteListView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable cpfTable;
    private PacienteDAO pacienteDAO;

    public PacienteListView() {
        setTitle("Listagem de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(2, 1));

        JButton btnShowPacienteCreateView = new JButton("Adicionar");
        btnShowPacienteCreateView.addActionListener(this::showPacienteCreateView);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Editar");
        tableModel.addColumn("Deletar");

        cpfTable = new JTable(tableModel);

        cpfTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        cpfTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));

        cpfTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        cpfTable.getColumnModel().getColumn(3).setCellEditor(new ButtonDelete(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(cpfTable);

        add(btnShowPacienteCreateView);
        add(scrollPane, BorderLayout.CENTER);

        pacienteDAO = new PacienteDAO();

        listPaciente();
    }

    private void listPaciente(){
        var pacientes = pacienteDAO.getList();

        tableModel.setRowCount(0);

        for (Paciente p : pacientes){
            tableModel.addRow(new Object[]{p.getNome(), p.getCpf(), "Editar", "Deletar"});
        }
    }

    private void showPacienteCreateView(ActionEvent actionEvent) {
        PacienteCreateView pacienteCreateView = new PacienteCreateView();

        pacienteCreateView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                listPaciente();
            }
        });

        pacienteCreateView.setVisible(true);
    }
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBorderPainted(false);
            setForeground(Color.BLUE);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(e -> {
                int modelRow = cpfTable.convertRowIndexToModel(cpfTable.getEditingRow());
                String cpf = tableModel.getValueAt(modelRow, 1).toString();
                var pacienteEdit = new PacienteEditView(cpf);

                pacienteEdit.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        listPaciente();
                    }
                });

                pacienteEdit.setVisible(true);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText(value != null ? value.toString() : "");
            return button;
        }
    }

    private class ButtonDelete extends DefaultCellEditor {
        private JButton button;

        public ButtonDelete(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(e -> {
                int modelRow = cpfTable.convertRowIndexToModel(cpfTable.getEditingRow());
                String cpf = tableModel.getValueAt(modelRow, 1).toString();

                pacienteDAO.delete(cpf);

                listPaciente();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText(value != null ? value.toString() : "");
            return button;
        }
    }
}