import org.json.JSONArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class order extends JFrame{
    //private orderList oList;
    private JButton confirmOrderButton;
    private JTextField textField1;
    private JButton cancelButton;
    private JPanel panel1;
    private JLabel name;
    private JLabel amount;
    private JButton homeButton;


    public order(String title) {

        super(title);
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        name.setText(array.getJSONObject(0).getString("winetype"));
        homeButton.setText("home");
        //this.oList = new orderList("orderList",0,0,0);
        setContentPane(panel1);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new Homepage("homepage");
                ui.setVisible(true);
                dispose();
            }
        });



        confirmOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //oList.setNrTequila(Integer.parseInt(amount.getText()));
                //oList.setNumbers();
                int number = Integer.parseInt(textField1.getText());
                String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getorderlist");
                JSONArray array = new JSONArray(response);
                number += array.getJSONObject(0).getInt("ordernum");
                String urlname = "https://studev.groept.be/api/a21ib2c02/updateorderlist/"+number +"/1";
                db.makeGETRequest(urlname);
                JFrame ui = new orderList("orderList",Integer.parseInt(textField1.getText()),0,0);
                ui.setVisible(true);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame stock = new stock("stock");
                stock.setVisible(true);
                dispose();
            }
        });

    }
    public static void main(String[] args)
    {
        JFrame ui = new order("order");
        ui.setVisible(true);
    }
}
