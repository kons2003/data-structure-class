package Btrees;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BTreeViewer {
    static JFrame frame = new JFrame("Btree Viewer");
    static BTreePanel treePanel = new BTreePanel();
    static JScrollPane scrollPane = new JScrollPane(treePanel);

    public static void main(String[] args) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
        JTextField txtNodeSize = new JTextField(3);
        txtNodeSize.setText("2");
        JButton btnNodeSize = new JButton("노드 최대 크기 변경");
        JButton btnPrev = new JButton("previous");
        JButton btnNext = new JButton("next");
        JTextField txtValue = new JTextField(20);
        JButton btnAdd = new JButton("add");
        JButton btnRemove = new JButton("remove");
        btnNodeSize.addActionListener(e -> {
            try {
                Node.MAX_COUNT = Math.max(2, Integer.parseInt(txtNodeSize.getText()));
                Node.MIN_COUNT = Node.MAX_COUNT / 2;
                treePanel.tree = new BTree();
                treePanel.index = -1;
                treePanel.images.clear();
                btnNext.setEnabled(false);
                btnPrev.setEnabled(false);
                btnAdd.setEnabled(true);
                btnRemove.setEnabled(true);
                scrollPane.setViewportView(treePanel);
                txtValue.requestFocus();
            } catch (Exception ex) {
            }
        });
        ActionListener action = e -> {
            try {
                String[] a = txtValue.getText().split("[ ,]+");
                for (String s : a) {
                    int value = Integer.parseInt(s);
                    if (e.getSource() == btnAdd) {
                        treePanel.tree.add(value);
                        treePanel.title = "ADD " + txtValue.getText();
                    } else {
                        treePanel.tree.remove(value);
                        treePanel.title = "REMOVE " + txtValue.getText();
                    }
                }
                treePanel.capture();
                scrollPane.setViewportView(treePanel);
                txtValue.setText("");
                txtValue.requestFocus();
                btnNext.setEnabled(false);
                btnPrev.setEnabled(treePanel.images.size() >= 2);
            } catch (Exception ex) {
            }
        };
        ActionListener action2 = e -> {
            if (e.getSource() == btnPrev)
                treePanel.index = Math.max(treePanel.index - 1, 0);
            else
                treePanel.index = Math.min(treePanel.index + 1, treePanel.images.size() - 1);
            boolean b = treePanel.index == treePanel.images.size() - 1;
            btnAdd.setEnabled(b);
            btnRemove.setEnabled(b);
            btnNext.setEnabled(treePanel.index < treePanel.images.size() - 1);
            btnPrev.setEnabled(treePanel.index > 0);
            treePanel.repaint();
            txtValue.requestFocus();
        };
        btnAdd.addActionListener(action);
        btnRemove.addActionListener(action);
        btnPrev.addActionListener(action2);
        btnNext.addActionListener(action2);
        btnNext.setEnabled(false);
        btnPrev.setEnabled(false);
        buttonPanel.add(txtValue);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRemove);
        buttonPanel.add(txtNodeSize);
        buttonPanel.add(btnNodeSize);
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.setMaximumSize(new Dimension(2000, 60));
        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);
        frame.setSize(1024, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add("Center", mainPanel);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(btnAdd);
    }
}

