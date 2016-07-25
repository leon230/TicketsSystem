package ch.makery.address.view;
import ch.makery.address.MainApp;
import ch.makery.address.util.AlertMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ch.makery.address.model.Ticket;
import ch.makery.address.util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TicketEditDialogController {

    @FXML
    private TextField ticketIdField;
    @FXML
    private TextField ticketNumberField;
    @FXML
    private TextField ticketRepByField;
    @FXML
    private TextField ticketTitleField;
    @FXML
    private TextField ticketOwnerField;
    @FXML
    private TextField ticketAccOwner;
    @FXML
    private TextArea ticketDescriptionArea;
    @FXML
    private ComboBox impactAllCombo = new ComboBox();
    @FXML
    private ComboBox impactOpsCombo = new ComboBox();
    @FXML
    private ComboBox impactORCombo = new ComboBox();
    @FXML
    private ComboBox impactTspCombo = new ComboBox();
    @FXML
    private ComboBox impactEBSCombo = new ComboBox();
    @FXML
    private ComboBox impactSAPCombo = new ComboBox();
    @FXML
    private ComboBox impactDBCombo = new ComboBox();
    @FXML
    private ComboBox impactXDCombo = new ComboBox();
    @FXML
    private ComboBox impactFinCombo = new ComboBox();
    @FXML
    private ComboBox priorityCombo = new ComboBox();
    @FXML
    private ComboBox statusCombo = new ComboBox();
    @FXML
    private ComboBox clusterCombo = new ComboBox();
    @FXML
    private DatePicker ticketOpenDate;
    @FXML
    private DatePicker ticketCloseDate;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    private Button attachOpen = new Button();

    private Stage dialogStage;
    private Ticket ticket;
    private boolean okClicked = false;
    String updateSql = "";
    private MainApp mainApp;

    @FXML
    ObservableList<String> comboYNValues = FXCollections.observableArrayList(
            "YES",
            "NO"
    );
    @FXML
    ObservableList<String> comboPriorityValues = FXCollections.observableArrayList(
            "High",
            "Medium",
            "Low"
    );
    @FXML
    ObservableList<String> comboStatusValues = FXCollections.observableArrayList(
            "IN QUEUE",
            "IN PROGRESS",
            "SENT FOR TESTING",
            "CLOSED"
    );
    @FXML
    ObservableList<String> comboClusterValues = FXCollections.observableArrayList(
            "Reporting",
            "SAP/XI",
            "Dockbooking",
            "System",
            "Planning",
            "Events",
            "Billing",
            "Shipment execution",
            "Shipment",
            "INTTRA",
            "Operations",
            "Other"

    );
    /**
     * Initializes the controller class.
     * Setting up listeners for each field in Edit pnael. Each text change will change SQL string
     */
    @FXML
    private void initialize() {
        priorityCombo.getItems().addAll(comboPriorityValues);
        impactAllCombo.getItems().addAll(comboYNValues);
        impactOpsCombo.getItems().addAll(comboYNValues);
        impactORCombo.getItems().addAll(comboYNValues);
        impactTspCombo.getItems().addAll(comboYNValues);
        impactEBSCombo.getItems().addAll(comboYNValues);
        impactSAPCombo.getItems().addAll(comboYNValues);
        impactDBCombo.getItems().addAll(comboYNValues);
        impactXDCombo.getItems().addAll(comboYNValues);
        impactFinCombo.getItems().addAll(comboYNValues);
        statusCombo.getItems().addAll(comboStatusValues);
        clusterCombo.getItems().addAll(comboClusterValues);

//        ticketTitleField.setOnAction((event) -> {
//            System.out.println("TextField Action");
//        });
    }
    //Is called by the main application to give a reference back to itself.
    /**
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param ticket
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;

        ticketIdField.setText(ticket.getticketID());
        ticketNumberField.setText(ticket.getticketNO());
        ticketTitleField.setText(ticket.getticketTitle());
        ticketRepByField.setText(ticket.getticketRepBy());
        ticketOwnerField.setText(ticket.getticketOwner());
        ticketAccOwner.setText(ticket.getticketAccOwner());
        priorityCombo.setValue(ticket.getticketPriority());
        statusCombo.setValue(ticket.getticketStatus());
        ticketDescriptionArea.setText(ticket.getticketDesc());
        clusterCombo.setValue(ticket.getticketCluster());

        impactAllCombo.setValue(ticket.getticketImpactAll());
        impactOpsCombo.setValue(ticket.getticketticketImpactOps());
        impactORCombo.setValue(ticket.getticketImpactOR());
        impactTspCombo.setValue(ticket.getticketImpactTsp());
        impactEBSCombo.setValue(ticket.getticketImpactEBS());
        impactSAPCombo.setValue(ticket.getticketImpactSAP());
        impactDBCombo.setValue(ticket.getticketImpactDB());
        impactXDCombo.setValue(ticket.getticketImpactXD());
        impactFinCombo.setValue(ticket.getticketImpactFin());


        if (ticket.getticketOpenDate() != null) {
            ticketOpenDate.setValue(LocalDate.parse(DATE_FORMATTER.format(DateUtil.parse(ticket.getticketOpenDate()))));
        }
        if (ticket.getticketCloseDate() != null) {
            ticketCloseDate.setValue(LocalDate.parse(DATE_FORMATTER.format(DateUtil.parse(ticket.getticketCloseDate()))));
        }

//        streetField.setText(person.getStreet());
//        postalCodeField.setText(Integer.toString(person.getPostalCode()));
//        cityField.setText(person.getCity());
//        birthdayField.setText(DateUtil.format(person.getBirthday()));
        //ticketOpenDate.setPromptText("dd.mm.yyyy");

        ticketTitleField.textProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "TICKET_TITLE");
        });
        ticketRepByField.textProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "REPORTED_BY");
        });
        ticketOpenDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "OPEN_DATE");
        });
        ticketCloseDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "CLOSE_DATE");
        });
        ticketOwnerField.textProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "TICKET_OWNER");
        });
        ticketAccOwner.textProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "ACC_OWNER");
        });
        priorityCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "PRIORITY");
        });
        statusCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "STATUS");
        });
        ticketDescriptionArea.textProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "DESCRIPTION");
        });

        impactAllCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_ALL");
        });
        impactOpsCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_OPS");
        });
        impactORCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_OR");
        });
        impactTspCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_TSP");
        });
        impactEBSCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_EBS");
        });
        impactSAPCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_SAP_USR");
        });
        impactDBCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_DB");
        });
        impactXDCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_XD");
        });
        impactFinCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "IMPACT_FINANCE");
        });
        clusterCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            TextChanged(newValue, oldValue, "CLUSTER");
        });
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    /**
     * Called when any field on the form is changed
     */
    private void TextChanged(Object newVal, Object oldVal, String colName){

        if(updateSql.contains(colName + "=")){
            updateSql = updateSql.replace(colName +"='" + oldVal + "'",colName + "='" + newVal + "'");
        }
        else{
            updateSql = updateSql + colName + "='" + newVal + "',";
        }
        //System.out.println("UPDATE tickets SET " + updateSql.substring(0,updateSql.length()-1) + " WHERE ID='" + ticketIdField.getText() + "'");

    }
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            try {
                ticket.setticketTitle(ticketTitleField.getText());
                ticket.setticketRepBy(ticketRepByField.getText());
                ticket.setticketTitle(ticketTitleField.getText());
                ticket.setTicketOpenDate(DateUtil.format(ticketOpenDate.getValue()));
                ticket.setTicketCloseDate(DateUtil.format(ticketCloseDate.getValue()));
                ticket.setticketOwner(ticketOwnerField.getText());
                ticket.setticketAccOwner(ticketAccOwner.getText());
                ticket.setticketPriority(priorityCombo.getValue().toString());
                ticket.setticketStatus(statusCombo.getValue().toString());
                ticket.setticketDesc(ticketDescriptionArea.getText());
                ticket.setticketCluster(clusterCombo.getValue().toString());

                ticket.setticketImpactAll(impactAllCombo.getValue().toString());
                ticket.setticketImpactOps(impactOpsCombo.getValue().toString());
                ticket.setticketImpactOR(impactORCombo.getValue().toString());
                ticket.setticketImpactTsp(impactTspCombo.getValue().toString());
                ticket.setticketImpactEBS(impactEBSCombo.getValue().toString());
                ticket.setticketImpactSAP(impactSAPCombo.getValue().toString());
                ticket.setticketImpactDB(impactDBCombo.getValue().toString());
                ticket.setticketImpactXD(impactXDCombo.getValue().toString());
                ticket.setticketImpactFin(impactFinCombo.getValue().toString());

                mainApp.setSqls("UPDATE tickets SET " + updateSql.substring(0, updateSql.length() - 1) + " WHERE ID='" + ticketIdField.getText() + "'", "UPDATE");


            }catch(Exception exc){System.out.println(exc.toString());}

            okClicked = true;
            dialogStage.close();
        }
    }
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    @FXML
    private void handleAttach(){
    }
    @FXML
    private void handleAttachOpen(){
        try {
            System.out.println("Explorer.exe " + System.getProperty("user.dir") + "/" + ticketNumberField.getText());
            Runtime.getRuntime().exec("Explorer.exe " + System.getProperty("user.dir") + "\\files\\" + ticketNumberField.getText());
        }
        catch(Exception exc){
            AlertMsg.ShowMsg("Error","File open Error",exc.toString(),AlertType.ERROR);
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (ticketNumberField.getText() == null || ticketNumberField.getText().length() == 0) {
            errorMessage += "Ticket number can't be empty!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            AlertMsg.ShowMsg("Invalid Fields","Please correct invalid fields",errorMessage,AlertType.ERROR);

            return false;
        }
    }
}