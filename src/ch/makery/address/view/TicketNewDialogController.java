package ch.makery.address.view;
import ch.makery.address.MainApp;
import ch.makery.address.util.AlertMsg;
import com.sun.org.apache.xpath.internal.functions.FuncSubstring;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ch.makery.address.model.Ticket;
import ch.makery.address.util.DateUtil;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Dialog to edit details of a person.
 *
 * @author
 */
public class TicketNewDialogController {
    @FXML
    private TextField ticketNumberField;
    @FXML
    private TextField ticketRepByField;
    @FXML
    private TextField ticketTitleField;
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
    private ComboBox ClusterCombo = new ComboBox();
    @FXML
    private ListView attachList;
    @FXML
    private TableView<Ticket> relatedTicketTab;
    @FXML
    private TableColumn<Ticket, String> ticketNOColumn;
    @FXML
    private TableColumn<Ticket, String> ticketTitleColumn;
    @FXML
    private TableColumn<Ticket, String> ticketDescCol;
    @FXML
    private TableColumn<Ticket, String> ticketInsDateCol;
    @FXML
    private ObservableList<Ticket> masterdataNew = FXCollections.observableArrayList();
    private ObservableList<Ticket> filteredDataNew = FXCollections.observableArrayList();



    private Stage dialogStage;
    private Ticket ticket;
    private boolean okClicked = false;
    private String attachString = "";
    private LocalDateTime localDate;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_kkmmss");
    private static final DateTimeFormatter INS_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private RootLayoutController rootLayout;
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
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        try {
            impactAllCombo.getItems().addAll(comboYNValues);
            impactOpsCombo.getItems().addAll(comboYNValues);
            impactORCombo.getItems().addAll(comboYNValues);
            impactTspCombo.getItems().addAll(comboYNValues);
            impactEBSCombo.getItems().addAll(comboYNValues);
            impactSAPCombo.getItems().addAll(comboYNValues);
            impactDBCombo.getItems().addAll(comboYNValues);
            impactXDCombo.getItems().addAll(comboYNValues);
            impactFinCombo.getItems().addAll(comboYNValues);
            priorityCombo.getItems().addAll(comboPriorityValues);
            ClusterCombo.getItems().addAll(comboClusterValues);

            ticketNOColumn.setCellValueFactory(cellData -> cellData.getValue().ticketNOProperty());
            ticketTitleColumn.setCellValueFactory(cellData -> cellData.getValue().ticketTitleProperty());
            ticketInsDateCol.setCellValueFactory(cellData -> cellData.getValue().ticketInsDateProperty());
            ticketDescCol.setCellValueFactory(cellData -> cellData.getValue().ticketDescProperty());

            relatedTicketTab.setItems(masterdataNew);

           // relatedTicketTab.setItems(rootLayout.getMasterdata());
        }
        catch (Exception exc){System.out.println("Load data error " + exc.toString());}
    }
    //Is called by the main application to give a reference back to itself.
    /**
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        masterdataNew.addAll(mainApp.getTicketData("SELECT ID, TICKET_NO, TICKET_TITLE, TICKET_OWNER, OPEN_DATE," +
                "CLOSE_DATE, ACC_OWNER, IMPACT_ALL,IMPACT_OPS,IMPACT_OR,IMPACT_TSP,IMPACT_EBS,IMPACT_SAP_USR," +
                "IMPACT_DB,IMPACT_XD,IMPACT_FINANCE,DESCRIPTION,PRIORITY,REPORTED_BY," +
                "STATUS,INSERT_DATE,INSERT_USER,CLUSTER FROM  tickets ORDER BY ID DESC"));

        relatedTicketTab.setItems(masterdataNew);
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
        localDate = LocalDateTime.now();
        ticketNumberField.setText(System.getProperty("user.name") + "_" + localDate.format(DATE_FORMATTER));
        ticketRepByField.setText(System.getProperty("user.name"));
        ticketTitleField.setText("");
        ticketDescriptionArea.setText("");


//        try {
//            ticketsSimilarAcc.getPanes().addAll(new TitledPane("T1", new Button("B1")));
//        }
//        catch(Exception exc){System.out.println(exc.getMessage().toString());}

//        impactAllCombo = new ComboBox(comboValues);


    }
    @FXML
    private void updateFilteredData() {
        filteredDataNew.clear();

        try {
            for (Ticket p : masterdataNew) {
                if (matchesFilter(p)) {
                    filteredDataNew.add(p);
                }
            }
            relatedTicketTab.setItems(filteredDataNew);
        }
        catch(Exception exc){System.out.println(exc.toString());}
    }
    /**
     * is ignored.
     *
     * @param ticket
     * @return
     */
    private boolean matchesFilter(Ticket ticket) {
        String filterString = ticketTitleField.getText();
        //System.out.println("filter string " + filterString);

        if (ticket.getticketTitle() == null){
            return false;
        }
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (ticket.getticketTitle().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }
    @FXML
    private void handleAttach(){
        try {
            FileChooser attachChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "files: ", "*.png", "*.png", "*.xls", "*.xlsx", "*.jpg", "*.zip", "*.xlsb", "*.msg");
            attachChooser.getExtensionFilters().add(extFilter);

            File file = attachChooser.showOpenDialog(dialogStage);
            if (file == null){
                System.out.println("No file selected");
            }
            else {
                attachString = attachString + file.getPath() + ",";
                attachList.getItems().add(file.getName());
            }
        }
        catch(Exception exc){
            System.out.println("File choose error" + exc.getMessage().toString());
        }
    }

    /**
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            ticket.setticketNO(ticketNumberField.getText());
            ticket.setticketRepBy(ticketRepByField.getText());
            ticket.setticketTitle(ticketTitleField.getText());
            ticket.setticketDesc(ticketDescriptionArea.getText());
            ticket.setticketCluster(ClusterCombo.getValue().toString());
            if (attachString.length() > 0) {
                ticket.setticketAttachments(attachString.substring(0, attachString.length() - 1));
            }
            if (impactAllCombo.getValue() != null) {
                ticket.setticketImpactAll(impactAllCombo.getValue().toString());
            }
            else ticket.setticketImpactAll("NO");

            if (impactOpsCombo.getValue() != null) {
                ticket.setticketImpactOps(impactOpsCombo.getValue().toString());
            }
            else ticket.setticketImpactOps("NO");

            if (impactORCombo.getValue() != null) {
                ticket.setticketImpactOR(impactORCombo.getValue().toString());
            }
            else ticket.setticketImpactOR("NO");

            if (impactTspCombo.getValue() != null) {
                ticket.setticketImpactTsp(impactTspCombo.getValue().toString());
            }
            else ticket.setticketImpactTsp("NO");

            if (impactEBSCombo.getValue() != null) {
                ticket.setticketImpactEBS(impactEBSCombo.getValue().toString());
            }
            else ticket.setticketImpactEBS("NO");

            if (impactSAPCombo.getValue() != null) {
                ticket.setticketImpactSAP(impactSAPCombo.getValue().toString());
            }
            else ticket.setticketImpactSAP("NO");

            if (impactDBCombo.getValue() != null) {
                ticket.setticketImpactDB(impactDBCombo.getValue().toString());
            }
            else ticket.setticketImpactDB("NO");

            if (impactXDCombo.getValue() != null) {
                ticket.setticketImpactXD(impactXDCombo.getValue().toString());
            }
            else ticket.setticketImpactXD("NO");

            if (impactFinCombo.getValue() != null) {
                ticket.setticketImpactFin(impactFinCombo.getValue().toString());
            }
            else ticket.setticketImpactFin("NO");

            if (priorityCombo.getValue() != null) {
                ticket.setticketPriority(priorityCombo.getValue().toString());
            }
            else ticket.setticketPriority("Low");

            ticket.setticketInsDate(localDate.format(INS_DATE_FORMAT));

            ticket.setticketStatus("AWAITING");
            ticket.setticketInsUser(System.getProperty("user.name"));

            okClicked = true;
            dialogStage.close();
        }
    }
    public String getAttachList(){
        return attachString;
    }
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (ticketRepByField.getText() == null || ticketRepByField.getText().length() == 0) {
            errorMessage += "Ticket ID can't be empty!\n";
        }
        if (ticketTitleField.getText() == null || ticketTitleField.getText().length() == 0) {
            errorMessage += "Ticket title can't be empty!\n";
        }
        if (ticketDescriptionArea.getText() == null || ticketDescriptionArea.getText().length() == 0) {
            errorMessage += "Ticket description can't be empty!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            AlertMsg.ShowMsg("Invalid Fields","Please correct invalid fields",errorMessage,AlertType.ERROR);

            return false;
        }
    }
}