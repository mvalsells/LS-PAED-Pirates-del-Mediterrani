package paedS2.taules;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JFHistograma extends JFrame {
    private final String[] columnas = {"Edad","Numero piratas"};
    public JFHistograma(int[] edats){
        setTitle("Histograma");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600,350));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tabla.setDefaultRenderer(String.class, centerRenderer);

        JScrollPane jScrollPane = new JScrollPane(tabla);
        add(jScrollPane);

        for(int i = 0; i < edats.length; i++) {
            if (edats[i]!=0) {
                int edad = i + 13;
                Object[] fila = {String.valueOf(edad), String.valueOf(edats[i])};
                tableModel.addRow(fila);
            }
        }
        setVisible(true);
    }
}
