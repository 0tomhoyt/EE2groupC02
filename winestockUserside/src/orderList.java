import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class orderList extends JFrame{
    private JPanel panel1;
    private JLabel nr1;
    private JLabel nr2;
    private JLabel nr3;
    private JLabel wine1;
    private JLabel wine2;
    private JLabel wine3;
    private JButton homeButton;

    private int int1;
    private int int2;
    private int int3;



    public orderList(String title, int T, int V, int G) {

        super(title);

        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array1 = new JSONArray(response);
        JSONObject obj1 = array1.getJSONObject(0);
        JSONObject obj2 = array1.getJSONObject(1);
        JSONObject obj3 = array1.getJSONObject(2);
        wine1.setText(obj1.getString("winetype"));
        wine2.setText(obj2.getString("winetype"));
        wine3.setText(obj3.getString("winetype"));
        homeButton.setText("home");

        String response2 = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getorderlist");
        JSONArray array2 = new JSONArray(response2);
        JSONObject obj4 = array2.getJSONObject(0);
        JSONObject obj5 = array2.getJSONObject(1);
        JSONObject obj6 = array2.getJSONObject(2);

        int1 = obj4.getInt("ordernum");
        int2 = obj5.getInt("ordernum");
        int3 = obj6.getInt("ordernum");



        nr1.setText(String.valueOf(int1));
        nr2.setText(String.valueOf(int2));
        nr3.setText(String.valueOf(int3));
        setContentPane(panel1);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new Homepage("homepage");
                ui.setVisible(true);
                dispose();
            }
        });

    }
//    public void setNrTequila(int number){
//        this.nrTe += number;
//    }
//    public void setNrVodka(int number){
//        nrVo += number;
//    }
//    public void setNrGin(int number){
//        nrGi += number;
//    }
//    public void setNumbers(){
//        nrT.setText(String.valueOf(nrTe));
//        nrV.setText(String.valueOf(nrVo));
//        nrG.setText(String.valueOf(nrGi));
//    }

    public static void main(String[] args)
    {
        JFrame ui = new orderList("orderList",0,0,0);
        ui.setVisible(true);
    }


   /*public  void setNrTequila(String number)
    {
        nrT.setText(String.valueOf(Integer.parseInt(number)));
    }
    public void setNrVodka(String number)
    {
        nrV.setText(String.valueOf(Integer.parseInt(number)));
    }
    public void setNrGin(String number)
    {
        nrG.setText(String.valueOf(Integer.parseInt(number)));
    }*/
}
