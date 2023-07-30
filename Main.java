import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ItemListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
interface SuperHome{
    void operate(String value);
    void updateTime(String startHour,String startMinute,String endHour,String endMinute);
}
class Switch extends JFrame{
    private final JLabel lblTime;
    private JToggleButton btnToggle;
    private Controller controller;

    public Switch(SuperHomeController superHomeController) {

        setSize(300, 300);
        setTitle("Switch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300, 300);
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0, 0, 300, 300);
        panel1.setBackground(new Color(255, 192, 203));

        JLabel titleLbl = new JLabel("Switch");
        titleLbl.setFont(new Font("", Font.BOLD, 22));
        titleLbl.setBounds(110, 10, 100, 30);
        panel1.add(titleLbl);

        btnToggle = new JToggleButton("ON");
        btnToggle.setBounds(70, 70, 150, 30);
        btnToggle.setFont(new Font("", Font.BOLD, 18));
        btnToggle.setBackground(new Color(255, 100, 127));
        panel1.add(btnToggle);

        JButton btnSettings = new JButton("Settings");
        btnSettings.setBounds(70, 110, 150, 30);
        btnSettings.setFont(new Font("", Font.BOLD, 18));
        btnSettings.setBackground(new Color(255, 100, 127));
        panel1.add(btnSettings);

        lblTime = new JLabel();
        lblTime.setFont(new Font("", Font.BOLD, 20));
        lblTime.setBounds(55, 200, 200, 30);
        panel1.add(lblTime);

        add(panel1);

        Timer timer = new Timer(1000, e -> updateLocalTime());
        timer.start();

        ItemListener itemListener = itemEvent -> {
            if (btnToggle.isSelected()) {
                btnToggle.setText("OFF");
                superHomeController.setBtnValue(btnToggle.getText());

            } else {
                btnToggle.setText("ON");
                superHomeController.setBtnValue(btnToggle.getText());
            }
        };
        btnToggle.addItemListener(itemListener);

        btnSettings.addActionListener(evt -> {
            if (controller == null) {
                controller = new Controller(superHomeController);
            }
            controller.setVisible(true);
        });
        setVisible(true);
    }
    private void updateLocalTime() {
        // Get the current local time
        LocalTime currentTime = LocalTime.now();

        // Format the time using a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);

        // Update the JLabel text
        lblTime.setText("Time : "+formattedTime);
    }
}
class Speaker extends JFrame implements SuperHome{
    private final JLabel label;
    private final JLabel lblTimeStart;
    private final JLabel lblTimeEnd;

    public Speaker(){
        setSize(300,120);
        setTitle("Speaker");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,300);
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0,0,300,120);
        panel1.setBackground(new Color(255,192,203));

        label=new JLabel("OFF");
        label.setFont(new Font("", Font.BOLD,20));
        label.setBounds(120,2,300,50);
        panel1.add(label);

        lblTimeStart=new JLabel("Start Time:");
        lblTimeStart.setFont(new Font("", Font.BOLD,15));
        lblTimeStart.setBounds(4,45,150,50);
        panel1.add(lblTimeStart);

        lblTimeEnd=new JLabel("End Time:");
        lblTimeEnd.setFont(new Font("", Font.BOLD,15));
        lblTimeEnd.setBounds(180,45,150,50);
        panel1.add(lblTimeEnd);

        add(panel1);

        Timer timer = new Timer(1000, e -> timeChecker());
        timer.start();

        setVisible(true);
    }
    @Override
    public void updateTime(String startHour, String startMinute, String endHour, String endMinute) {

        int sh=Integer.parseInt(startHour);
        int sm=Integer.parseInt(startMinute);
        int eh=Integer.parseInt(endHour);
        int em=Integer.parseInt(endMinute);

        String startTime = (String.format("%02d:%02d:00", sh, sm));
        String endTime = String.format("%02d:%02d:00", eh, em);

        lblTimeStart.setText(startTime);
        lblTimeEnd.setText(endTime);
    }
    private void timeChecker() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeStart.getText())) {
            label.setText("ON");

        } else if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeEnd.getText())) {
            label.setText("OFF");
        }
    }
    @Override
    public void operate(String value){
        if(value.equals("ON")){
            label.setText("OFF");
        }else{
            label.setText("ON");
        }
    }
}
class TvLivingRoom extends JFrame implements SuperHome{
    private final JLabel label;
    private final JLabel lblTimeStart;
    private final JLabel lblTimeEnd;
    public TvLivingRoom(){

        setSize(300,120);
        setTitle("TV Living Room");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,420);
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0,0,300,120);
        panel1.setBackground(new Color(255,192,203));

        label=new JLabel("OFF");
        label.setFont(new Font("", Font.BOLD,20));
        label.setBounds(120,2,300,50);
        panel1.add(label);

        lblTimeStart=new JLabel("Start Time:");
        lblTimeStart.setFont(new Font("", Font.BOLD,15));
        lblTimeStart.setBounds(4,45,150,50);
        panel1.add(lblTimeStart);

        lblTimeEnd=new JLabel("End Time:");
        lblTimeEnd.setFont(new Font("", Font.BOLD,15));
        lblTimeEnd.setBounds(180,45,150,50);
        panel1.add(lblTimeEnd);

        add(panel1);

        Timer timer = new Timer(1000, e -> timeChecker());
        timer.start();

        setVisible(true);
    }
    @Override
    public void updateTime(String startHour, String startMinute, String endHour, String endMinute) {
        int sh=Integer.parseInt(startHour);
        int sm=Integer.parseInt(startMinute);
        int eh=Integer.parseInt(endHour);
        int em=Integer.parseInt(endMinute);

        String startTime = (String.format("%02d:%02d:00", sh, sm));
        String endTime = String.format("%02d:%02d:00", eh, em);

        lblTimeStart.setText(startTime);
        lblTimeEnd.setText(endTime);
    }
    private void timeChecker() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeStart.getText())) {
            label.setText("ON");

        } else if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeEnd.getText())) {
            label.setText("OFF");
        }
    }
    @Override
    public void operate(String value){
        if(value.equals("ON")){
            label.setText("OFF");
        }else{
            label.setText("ON");
        }
    }
}
class TvDiningRoom extends JFrame implements SuperHome{
    private final JLabel label;
    private final JLabel lblTimeStart;
    private final JLabel lblTimeEnd;

    public TvDiningRoom(){

        setSize(300,120);
        setTitle("TV Dining Room");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,540);
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0,0,300,120);
        panel1.setBackground(new Color(255,192,203));

        label=new JLabel("OFF");
        label.setFont(new Font("", Font.BOLD,20));
        label.setBounds(120,2,300,50);
        panel1.add(label);

        lblTimeStart=new JLabel("Start Time:");
        lblTimeStart.setFont(new Font("", Font.BOLD,15));
        lblTimeStart.setBounds(4,45,150,50);
        panel1.add(lblTimeStart);

        lblTimeEnd=new JLabel("End Time:");
        lblTimeEnd.setFont(new Font("", Font.BOLD,15));
        lblTimeEnd.setBounds(180,45,150,50);
        panel1.add(lblTimeEnd);

        add(panel1);

        Timer timer = new Timer(1000, e -> timeChecker());
        timer.start();

        setVisible(true);
    }
    @Override
    public void updateTime(String startHour, String startMinute, String endHour, String endMinute) {
        int sh=Integer.parseInt(startHour);
        int sm=Integer.parseInt(startMinute);
        int eh=Integer.parseInt(endHour);
        int em=Integer.parseInt(endMinute);

        String startTime = String.format("%02d:%02d:00", sh, sm);
        String endTime = String.format("%02d:%02d:00", eh, em);

        lblTimeStart.setText(startTime);
        lblTimeEnd.setText(endTime);
    }
    private void timeChecker() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeStart.getText())) {
            label.setText("ON");

        } else if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeEnd.getText())) {
            label.setText("OFF");
        }
    }
    @Override
    public void operate(String value){
        if(value.equals("ON")){
            label.setText("OFF");
        }else{
            label.setText("ON");
        }
    }
}
class Window extends JFrame implements SuperHome{
    private final JLabel label;
    private final JLabel lblTimeStart;
    private final JLabel lblTimeEnd;

    public Window(){
        setSize(300,120);
        setTitle("Window");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,660);
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0,0,300,120);
        panel1.setBackground(new Color(255,192,203));

        label=new JLabel("OFF");
        label.setFont(new Font("", Font.BOLD,20));
        label.setBounds(120,2,300,50);
        panel1.add(label);

        lblTimeStart=new JLabel("Start Time:");
        lblTimeStart.setFont(new Font("", Font.BOLD,15));
        lblTimeStart.setBounds(4,45,150,50);
        panel1.add(lblTimeStart);

        lblTimeEnd=new JLabel("End Time:");
        lblTimeEnd.setFont(new Font("", Font.BOLD,15));
        lblTimeEnd.setBounds(180,45,150,50);
        panel1.add(lblTimeEnd);

        add(panel1);

        Timer timer = new Timer(1000, e -> timeChecker());
        timer.start();

        setVisible(true);
    }
    @Override
    public void operate(String value){
        if(value.equals("ON")){
            label.setText("OFF");
        }else{
            label.setText("ON");
        }
    }
    @Override
    public void updateTime(String startHour, String startMinute, String endHour, String endMinute) {
        int sh=Integer.parseInt(startHour);
        int sm=Integer.parseInt(startMinute);
        int eh=Integer.parseInt(endHour);
        int em=Integer.parseInt(endMinute);

        String startTime = (String.format("%02d:%02d:00", sh, sm));
        String endTime = String.format("%02d:%02d:00", eh, em);

        lblTimeStart.setText(startTime);
        lblTimeEnd.setText(endTime);
    }
    private void timeChecker() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeStart.getText())) {
            label.setText("ON");

        } else if (currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals(lblTimeEnd.getText())) {
            label.setText("OFF");
        }
    }
}
class SuperHomeController {
    private SuperHome[] superHomeArray = new SuperHome[100];
    private int nextIndex;
    private int index;
    private String btnValue;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
    public void add(SuperHome superHome){
        superHomeArray[nextIndex++]=superHome;
    }
    public void setIndex(int index){
        this.index=index;
    }
    public void setBtnValue(String btnValue){
        if(this.btnValue!=btnValue){
            this.btnValue=btnValue;
            notifyObjects();
        }
    }
    public void notifyObjects(){
        for (int i = 0; i < nextIndex; i++) {
            superHomeArray[i].operate(btnValue);
        }
    }
    public void notifyTime(){
        superHomeArray[index].updateTime(startHour,startMinute,endHour,endMinute);
    }
    public void setTime(String startTime,String startMinute,String endHour,String endMinute){
        if(this.startMinute != startMinute | this.endHour != endHour | this.endMinute != endMinute){
            this.startHour=startTime;
            this.startMinute=startMinute;
            this.endHour=endHour;
            this.endMinute=endMinute;
            notifyTime();
        }
    }
}
class Controller extends JFrame{
    private final JList list;
    private TimeComponent timeComponent;

    public Controller(SuperHomeController superHomeController){

        setSize(300,200);
        setTitle("Controller");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,50);

        DefaultListModel model = new DefaultListModel();
        model.addElement("Speaker Living Room");
        model.addElement("TV Living Room");
        model.addElement("TV Dining Room");
        model.addElement("Window Living Room");

        list=new JList(model);

        add(list);

        list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && !list.isSelectionEmpty()) {
                    String name=""+list.getSelectedValue();
                    int index= list.getSelectedIndex();
                    superHomeController.setIndex(index);
                    if(timeComponent==null){
                        new TimeComponent(name,superHomeController).setVisible(true);
                    }


                }
            }
        });
        setVisible(true);
    }
}
class TimeSettings{
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
    public TimeSettings(String startHour,String startMinute,String endHour,String endMinute){
        this.startHour=startHour;
        this.startMinute=startMinute;
        this.endHour=endHour;
        this.endMinute=endMinute;
    }
    public void setStartHour(String startHour){
        this.startHour=startHour;
    }
    public void setEndHour(String endHour){
        this.endHour=endHour;
    }
    public void setStartMinute(String startMinute){
        this.startMinute=startMinute;
    }
    public void setEndMinute(String endMinute){
        this.endMinute=endMinute;
    }
    public String getStartHour(){
        return startHour;
    }
    public String getStartMinute(){
        return startMinute;
    }
    public String getEndHour(){
        return endHour;
    }
    public String getEndMinute(){
        return endMinute;
    }

    public String toString(){
        return "Starts at :  "+startHour+":"+startMinute+"   Ends at :  "+endHour+":"+endMinute;
    }
}
class  TimeComponent extends JFrame{
    private JLabel lblEndMinute;
    private JSpinner spinStartHour;
    private JSpinner spinStartMinute;
    private JSpinner spinEndHour;
    private JSpinner spinEndMinute;
    private JButton btnSetEdit;
    private DefaultListModel<TimeSettings> li;
    private final JList<TimeSettings> list;
    private TimeSettings timeSetting;
    public TimeComponent(String name,SuperHomeController superHomeController){
        setSize(600,300);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(900,60);

        JPanel southPnl = new JPanel();

        SpinnerModel valueStartHour=new SpinnerNumberModel(0,0,23,1);
        SpinnerModel valueStartMinute=new SpinnerNumberModel(0,0,59,1);
        SpinnerModel valueEndHour=new SpinnerNumberModel(0,0,23,1);
        SpinnerModel valueEndMinute=new SpinnerNumberModel(0,0,59,1);

        spinStartHour = new JSpinner(valueStartHour);
        spinStartMinute = new JSpinner(valueStartMinute);
        spinEndHour = new JSpinner(valueEndHour);
        spinEndMinute = new JSpinner(valueEndMinute);

        JLabel lblStartHour = new JLabel("Start: Hour:");
        lblStartHour.setFont(new Font("", Font.BOLD,15));

        JLabel lblStartMinute = new JLabel("Minute:");
        lblStartMinute.setFont(new Font("", Font.BOLD,15));

        JLabel lblEndHour = new JLabel("End: Hour:");
        lblEndHour.setFont(new Font("", Font.BOLD,15));

        lblEndMinute=new JLabel("Minute:");
        lblEndMinute.setFont(new Font("", Font.BOLD,15));

        li=new DefaultListModel<> ();
        list=new JList<>(li);

        southPnl.add(lblStartHour);
        southPnl.add(spinStartHour);
        southPnl.add(lblStartMinute);
        southPnl.add(spinStartMinute);
        southPnl.add(lblEndHour);
        southPnl.add(spinEndHour);
        southPnl.add(lblEndMinute);
        southPnl.add(spinEndMinute);

        btnSetEdit=new JButton("Set");
        btnSetEdit.addActionListener(e -> {

            String startHour= String.valueOf(spinStartHour.getValue());
            String startMinute= String.valueOf(spinStartMinute.getValue());
            String endHour= String.valueOf(spinEndHour.getValue());
            String endMinute= String.valueOf(spinEndMinute.getValue());

            li.addElement(new TimeSettings(startHour,startMinute,endHour,endMinute));
            superHomeController.setTime(startHour,startMinute,endHour,endMinute);

        });

        list.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !list.isSelectionEmpty()) {
                btnSetEdit.setText("Edit");
                timeSetting=list.getSelectedValue();

                spinStartHour.setValue(Integer.parseInt(timeSetting.getStartHour()));
                spinStartMinute.setValue((Integer.parseInt(timeSetting.getStartMinute())));
                spinEndHour.setValue(Integer.parseInt(timeSetting.getEndHour()));
                spinEndMinute.setValue(Integer.parseInt(timeSetting.getEndMinute()));

                btnSetEdit.addActionListener(e1 -> {
                    int index= list.getSelectedIndex();
                    if(index>=0){
                        li.remove(li.getSize()-1);
                        list.repaint();
                    }
                    btnSetEdit.setText("Set");
                });
            }
        });

        southPnl.add(btnSetEdit);

        add("South", southPnl);
        add("North",list);
    }

}


class Main{
    public static void main(String[] args) {
        SuperHomeController superHomeController = new SuperHomeController();
        superHomeController.add(new Speaker());
        superHomeController.add(new TvLivingRoom());
        superHomeController.add(new TvDiningRoom());
        superHomeController.add(new Window());
        new Switch(superHomeController);
    }
}