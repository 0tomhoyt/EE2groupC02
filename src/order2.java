import org.json.JSONArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class order2 extends JFrame{
    //private orderList oList;
    private JButton confirmOrderButton;
    private JTextField textField1;
    private JButton cancelButton;
    private JPanel panel1;
    private JLabel name;
    private JButton homeButton;
    private JLabel amount;



    public order2(String title) {

        super(title);
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        name.setText(array.getJSONObject(1).getString("winetype"));
        //this.oList = new orderList("orderList",0,0,0);
        setContentPane(panel1);



        confirmOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //oList.setNrTequila(Integer.parseInt(amount.getText()));
                //oList.setNumbers();
                int number = Integer.parseInt(textField1.getText());
                String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getorderlist");
                JSONArray array = new JSONArray(response);
                number += array.getJSONObject(1).getInt("ordernum");
                String urlname = "https://studev.groept.be/api/a21ib2c02/updateorderlist/"+number +"/2";
                db.makeGETRequest(urlname);
                JFrame ui = new orderList("orderList",0,Integer.parseInt(textField1.getText()),0);
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
