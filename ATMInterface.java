import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {
    private JFrame frame;
    private JTextField amountTextField;
    private JTextArea resultTextArea;
   //Initial Balance
    private double accountBalance = 10000.0; 

    public ATMInterface() {
        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.PINK);

        JLabel titleLabel = new JLabel("ATM", SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(400, 30));
        frame.add(titleLabel);
        JLabel amountLabel = new JLabel("Enter Amount: ", SwingConstants.CENTER);
        frame.add(amountLabel);
        amountTextField = new JTextField();
        amountTextField.setPreferredSize(new Dimension(200, 30));
        frame.add(amountTextField);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setPreferredSize(new Dimension(380, 100));
        resultTextArea.setBackground(Color.WHITE);
        frame.add(resultTextArea);

        JButton checkBalanceButton = new JButton("Check Balance");
        frame.add(checkBalanceButton);

        JButton depositButton = new JButton("Deposit");
        frame.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        frame.add(withdrawButton);

        checkBalanceButton.addActionListener(e -> displayResult("Balance:  Rs. " + accountBalance));

        depositButton.addActionListener(e -> {
            double amount = getAmountFromTextField();
            accountBalance += amount;
            displayResult("Successfully Deposited! Balance: Rs. " + accountBalance);
            clearAmountTextField();
        });

        withdrawButton.addActionListener(e -> {
            double amount = getAmountFromTextField();
            if (accountBalance >= amount) {
                accountBalance -= amount;
                displayResult("Succesfully WithDrawed!  Balance: Rs. " + accountBalance);
            } else {
                displayResult("Insufficient Money. Withdrawal failed.");
            }
            clearAmountTextField();
        });
    }

    public void display() {
        frame.setSize(420, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private double getAmountFromTextField() {
        try {
            return Double.parseDouble(amountTextField.getText());
        } catch (NumberFormatException e) {
            displayResult("Invalid amount. Please enter a valid number.");
            return 0.0;
        }
    }

    private void clearAmountTextField() {
        amountTextField.setText("");
    }

    private void displayResult(String result) {
        resultTextArea.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMInterface().display());
    }
}
