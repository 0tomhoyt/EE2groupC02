import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame{
    private JButton button1;
    private JButton Button1;
    private JButton storageList;
    private JButton button4;
    private JButton button6;
    private JButton co2;
    private JButton customer;
    private JPanel homepagePanel;
    private JButton bar;

    public Homepage(String title)
    {
        super(title);
        homepagePanel.setBackground(Color.orange);
        bar. setIcon(new ImageIcon(Homepage.class.getResource("/com/Bar_image.jpg")));
        button4. setIcon(new ImageIcon(Homepage.class.getResource("/com/notification_icon.png")));
        button6. setIcon(new ImageIcon(Homepage.class.getResource("/com/Notes_icon.jpg")));
        button1. setIcon(new ImageIcon(Homepage.class.getResource("/com/settings_icon.png")));
        button1.setBackground(Color.WHITE);
        button4.setBackground(Color.WHITE);
        button6.setBackground(Color.WHITE);
        storageList.setIcon(new ImageIcon(Homepage.class.getResource("/com/list_icon.png")));
        co2.setIcon(new ImageIcon(Homepage.class.getResource("/com/Co2_carbon_dioxide_icon.png")));
        customer.setIcon(new ImageIcon(Homepage.class.getResource("/com/People_icon.jpg")));
        button4.setSize(80,80);
        storageList.setText("Storage List");
        co2.setText("co2 info");
        customer.setText("customer info");
        setContentPane(homepagePanel);


        //check low stock
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        JSONObject obj = new JSONObject();

        for (int i = 0; i < 3; i++) {
            obj =array.getJSONObject(i);
            if (obj.getInt("number")<=5)
            {
                JFrame lowStock = new LowStock();
                lowStock.setSize(400,200);
                lowStock.setLocationRelativeTo(storageList);
                lowStock.setVisible(true);
                lowStock.setAlwaysOnTop(true);
                break;
            }
        }


        storageList.addActionListener(new ActionListener() {
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
        JFrame ui = new Homepage("homepage");
        ui.setSize(600,800);
        ui.setLocation(500,0);
        ui.setVisible(true);
    }

}
