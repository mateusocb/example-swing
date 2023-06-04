package org.example.view.medico;

import org.example.dao.MedicoDAO;
import org.example.entity.Medico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MedicoListView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable medicoTable;
    private MedicoDAO medicoDAO;

    public MedicoListView() {
        setTitle("Listagem de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(2, 1));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("CRM");
        tableModel.addColumn("Editar");
        tableModel.addColumn("Deletar");

        medicoTable = new JTable(tableModel);
        medicoTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        medicoTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));

        medicoTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        medicoTable.getColumnModel().getColumn(3).setCellEditor(new ButtonDelete(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(medicoTable);

        JButton btnShowMedicoCreateView = new JButton("Adicionar");
        btnShowMedicoCreateView.addActionListener(this::showMedicoCreateView);

        add(btnShowMedicoCreateView);

        add(scrollPane, BorderLayout.CENTER);

        medicoDAO = new MedicoDAO();

        listMedicos();
    }

    private void showMedicoCreateView(ActionEvent actionEvent) {
        MedicoCreateView medicoCreateView = new MedicoCreateView();

        medicoCreateView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                listMedicos();
            }
        });

        medicoCreateView.setVisible(true);
    }

    private void listMedicos(){
        var medicos = medicoDAO.getList();

        tableModel.setRowCount(0);

        for (Medico m : medicos){
            tableModel.addRow(new Object[]{ m.getNome(), m.getCrm(), "Editar", "Deletar" });
        }
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
                int modelRow = medicoTable.convertRowIndexToModel(medicoTable.getEditingRow());
                String crm = tableModel.getValueAt(modelRow, 1).toString();

                var medicoEditView = new MedicoEditView(crm);

                medicoEditView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        listMedicos();
                    }
                });

                medicoEditView.setVisible(true);
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
                int modelRow = medicoTable.convertRowIndexToModel(medicoTable.getEditingRow());
                var crm = tableModel.getValueAt(modelRow, 1).toString();

                medicoDAO.delete(Integer.valueOf(crm));

                listMedicos();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText(value != null ? value.toString() : "");
            return button;
        }
    }
}