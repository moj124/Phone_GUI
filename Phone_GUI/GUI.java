import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Creates the graphical user interface for the phone, using swing library.
 * 
 * The application will allow basic entry of numbers, delete and call functions. 
 * The phone's battery is simulated to decrease in real time, per minute.
 *
 * @author Michael Jones
 * @version 2018.10.24
 */
public class GUI extends JFrame implements ActionListener
{
    // The  main frame that holds all the containers for GUI Interface.
    private JFrame frame;
    // Stores the frame's container to be used for designing the GUI Interface.
    private Container contentPane;
    // The panels used to arrange the graphical user interface.
    private JPanel panel, statusPanel, btn_panel,south_panel;
    // The phone's service provider.
    private static final String provider = "Vodafone UK";
    // The phone's battery level.
    private  static int battery= 100;
    // The labels used in showing pieces of information.
    private JLabel lbl_status, lbl_display,test_button;
    // The buttons used for calling, clearing text and accepting functionality.
    private JButton btn_ok, btn_clear, btn_call;
    // The static array holding all the number buttons for the GUI interface.
    private JButton[] buttons = new JButton[14];

    private HashMap<JButton,String[]> displayText = new HashMap<JButton, String[]>();
    // the display text string from the resulting input buttons.
    private   String  display="";
    // Strings used to get the time and battery life from the class.
    private static String time, power;

    /**
     * Constructs the phone's GUI.
     */
    public GUI()
    {
        makeFrame();
        lbl_status.setText(getStatus());
        run();
    }

    public void makeFrame()
    {

        //Creating Frame and Components
        frame = new JFrame();
        frame.setLocationRelativeTo(null) ;

        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(6,6));

        JButton btn_0 = new JButton();
        JButton btn_1 = new JButton();
        JButton btn_2 = new JButton();
        JButton btn_3 = new JButton();
        JButton btn_4 = new JButton();
        JButton btn_5 = new JButton();
        JButton btn_6 = new JButton();
        JButton btn_7 = new JButton();
        JButton btn_8 = new JButton();
        JButton btn_9 = new JButton();
        JButton btn_star = new JButton();
        JButton btn_00 = new JButton();
        JButton btn_hash = new JButton();
        btn_ok = new JButton();
        btn_clear = new JButton("X");
        btn_call = new JButton("Call");
        test_button = new JLabel();

        lbl_status = new JLabel();
        lbl_display = new JLabel();

        panel = new JPanel();
        statusPanel = new JPanel(new GridLayout());
        btn_panel = new JPanel(new FlowLayout());
        south_panel = new JPanel(new FlowLayout());

        GridLayout grid = new GridLayout(4,2);
        btn_panel.setLayout(grid);
        
        // Creates buttons for the numberpad and adds their according display text.
              for(int i = 1; i<buttons.length-1;i++){
            switch(i){  
                default:
                if(buttons[i].getClass() != null){
                    buttons[i].setText(""+i);
                }
                break;
                case 10: 
                buttons[i] = new JButton("*");
                break;
                case 11:
                buttons[i] = btn_0;
                btn_0.setText("0");
                break;
                case 12:
                buttons[i] = new JButton("#");
                break;

            }  
            btn_panel.add(buttons[i]);  
            buttons[i].addActionListener(this);
        }
        
        // Action listeners for buttons
        
        btn_clear.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(!display.isEmpty()){
                        display = "";
                        System.out.println("Clearing");
                        btn_clear.setVisible(false);
                    }

                }
            });

        btn_call.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //changeFrame();
                }
            });
            
        // Adding text to buttons

        buttons[11]  = btn_0;
        buttons[1]  = btn_1;
        buttons[2]  = btn_2;
        buttons[3]  = btn_3;
        buttons[4]  = btn_4;
        buttons[5]  = btn_5;
        buttons[6]  = btn_6;
        buttons[7]  = btn_7;
        buttons[8]  = btn_8;
        buttons[9]  = btn_9;
        buttons[10] = btn_star;
        //buttons[11] = btn_00;
        buttons[12] = btn_hash;

        String[] text0 = {"0", "+"};
        String[] text1 = {"1"};
        String[] text2 = {"2","ABC"};
        String[] text3 = {"3","DEF"};
        String[] text4 = {"4","GHI"};
        String[] text5 = {"5","JKL"};
        String[] text6 = {"6","MNO"};
        String[] text7 = {"7","PQRS"};
        String[] text8 = {"8","TUV"};
        String[] text9 = {"9","WXYZ"};
        String[] text10 = {"*"};
        String[] text11 = {"#"};

        displayText.put(buttons[0], text0);
        displayText.put(buttons[1], text1);
        displayText.put(buttons[2], text2);
        displayText.put(buttons[3], text3);
        displayText.put(buttons[4], text4);
        displayText.put(buttons[5], text5);
        displayText.put(buttons[6], text6);
        displayText.put(buttons[7], text7);
        displayText.put(buttons[8], text8);
        displayText.put(buttons[9], text9);
        displayText.put(buttons[10],text10);
        //displayText.put(buttons[11],text0);
        displayText.put(buttons[12],text11);

  

        //Adding components to the main frame's container.
        contentPane.setPreferredSize(new Dimension(400,500));
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(lbl_status, BorderLayout.NORTH);
        contentPane.add(south_panel, BorderLayout.SOUTH);
        south_panel.add(btn_call);
        south_panel.add(test_button);

        panel.setLayout(new GridLayout(2,0));
        panel.add(lbl_display);
        panel.add(btn_panel);
        south_panel.add(btn_clear);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int i=0;i<buttons.length;i++){
            if(e.getSource() == buttons[i]){
                String text = buttons[i].getText().substring(0,1);
                display+=buttons[i].getText();
                //System.out.println(buttons[i].getText());
                btn_clear.setVisible(true);
                break;
            }
        }
    }

    public static String getStatus()
    {

        power = battery + "%";
        Date date = new Date();
        if (date.getMinutes() < 10){
            time = date.getHours()+":0"+date.getMinutes() ;
        }
        else{

            time = date.getHours()+":"+date.getMinutes() ;
        }

        return  provider +"    "+ time +"    "+ power;
    }

    public void resetStatus()
    {
        if(!lbl_status.getText().equals(getStatus())){
            battery --;
            if(battery <= 0){
                battery = 0;
                System.out.println("Working.");
                frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
                frame.dispose();
            }
        }
        lbl_status.setText(getStatus());
        lbl_display.setText(display);
    }

    private void showing()
    {
        for(int i=1;i<buttons.length;i++){
            JButton button = new JButton(""+i);
            btn_panel.add(button);  
            buttons[i]= button;
            button.addActionListener(this);
        }
    }

    public void changeFrame()
    {
        int i = 1;
        for(JButton button : buttons){

            button.setText(displayText.get(button)[i]);
            System.out.println(displayText.get(button)[i]);
        }

    }

    /**
    * Continually updates the phone's status while the simulation is going.
    */
    public void run()
    {
        display = "";
        resetStatus();
        while(frame.isVisible()){
            resetStatus();
        }   
    }
}

