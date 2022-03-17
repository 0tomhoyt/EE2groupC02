import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stock extends JFrame {
    private JButton orderTequilaButton;
    private JButton orderVodkaButton;
    private JButton orderGinButton;
    private JPanel panel1;
    private JLabel Tequila;
    private JLabel Vodka;
    private JLabel Gin;
    private JLabel nrTequila;
    private JLabel nrVodka;
    private JLabel nrGin;
    private JButton homeButton;


    public stock(String title) {

        super(title);
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        JSONObject obj1 = array.getJSONObject(0);
        JSONObject obj2 = array.getJSONObject(1);
        JSONObject obj3 = array.getJSONObject(2);
        Tequila.setText(obj1.getString("winetype"));
        nrTequila.setText(obj1.getString("number"));
        orderTequilaButton.setText("order ");
        Vodka.setText(obj2.getString("winetype"));
        nrVodka.setText(obj2.getString("number"));
        orderVodkaButton.setText("order");
        Gin.setText(obj3.getString("winetype"));
        nrGin.setText(obj3.getString("number"));
        orderGinButton.setText("order");
        homeButton.setText("home");


        setContentPane(panel1);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new Homepage("homepage");
                ui.setVisible(true);
                dispose();
            }
        });
        orderTequilaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new order(obj1.getString("winetype"));
                ui.setVisible(true);
                dispose();
            }
        });
        orderVodkaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new order2(obj2.getString("winetype"));
                ui.setVisible(true);
                dispose();
            }
        });

        orderGinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new order3(obj3.getString("winetype"));
                ui.setVisible(true);
                dispose();
            }
        });


    }


    public static void main(String[] args)
    {
        JFrame ui = new stock("stock");
        ui.setVisible(true);
    }


}

