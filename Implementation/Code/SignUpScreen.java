import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpScreen extends JFrame{
    private JPanel SignUpPanel;
    private JTextField UsernameSignUp;
    private JPasswordField PasswordSignUp;
    private JButton createYourAccountButton;
    private JButton LoginSwitchButton;
    private JTextField textField1;
    private JPanel SignUpScreen;

    public SignUpScreen(){

        setContentPane(SignUpScreen);
        setTitle("Ucars Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);


        LoginSwitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen page = new LoginScreen();
                setVisible(false);
                page.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

}
