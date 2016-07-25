package ch.makery.address.view;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ch.makery.address.DBConnect;
import ch.makery.address.model.Ticket;
import ch.makery.address.util.AlertMsg;
import ch.makery.address.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;
import org.springframework.util.FileCopyUtils;


public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;
    private LocalDateTime locDate;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_kkmmss");
    private ObservableList<Ticket> filteredData = FXCollections.observableArrayList();
    private ObservableList<Ticket> masterdata = FXCollections.observableArrayList();

    @FXML
    private TableView<Ticket> ticketTable;
    @FXML
    public Label statusLabel = new Label();

    @FXML
    private TableColumn<Ticket, String> ticketIDColumn;
    @FXML
    private TableColumn<Ticket, String> ticketNOColumn;
    @FXML
    private TableColumn<Ticket, String> ticketTitleColumn;
    @FXML
    private TableColumn<Ticket, String> ticketOwnerColumn;
    @FXML
    private TableColumn<Ticket, String> ticketCloseDColumn;
    @FXML
    private TableColumn<Ticket, String> ticketAccOwnerDCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactAllCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactOpsCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactORCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactTspCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactEBSCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactSAPCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactDBCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactXDCol;
    @FXML
    private TableColumn<Ticket, String> ticketImpactFinCol;
    @FXML
    private TableColumn<Ticket, String> ticketPriorityCol;
    @FXML
    private TableColumn<Ticket, String> ticketRepByCol;
    @FXML
    private TableColumn<Ticket, String> ticketStatusCol;
    @FXML
    private TableColumn<Ticket, String> ticketInsDateCol;
    @FXML
    private TableColumn<Ticket, String> ticketInsUserCol;
    @FXML
    private TableColumn<Ticket, String> ticketDescCol;
    @FXML
    private TableColumn<Ticket, String> ticketClusterCol;

    @FXML
    private TableColumn<Ticket, String> ticketOpenDColumn;
    @FXML
    private TextField ticketNoFilter = new TextField();


    public RootLayoutController(){


//        masterdata.addListener(new ListChangeListener<Ticket>() {
//            @Override
//            public void onChanged(ListChangeListener.Change<? extends Ticket> change) {
//                updateFilteredData();
//            }
//        });
    }
//Is called by the main application to give a reference back to itself.
    /**
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        masterdata.addAll(mainApp.getTicketData("SELECT ID, TICKET_NO, TICKET_TITLE, TICKET_OWNER, OPEN_DATE," +
                "CLOSE_DATE, ACC_OWNER, IMPACT_ALL,IMPACT_OPS,IMPACT_OR,IMPACT_TSP,IMPACT_EBS,IMPACT_SAP_USR," +
                "IMPACT_DB,IMPACT_XD,IMPACT_FINANCE,DESCRIPTION,PRIORITY,REPORTED_BY," +
                "STATUS,INSERT_DATE,INSERT_USER, CLUSTER FROM  tickets ORDER BY ID DESC"));
        filteredData.addAll(masterdata);

        ticketTable.setItems(filteredData);


        statusLabel.setText(mainApp.geterrorMsg());

        //ticketTable.getColumns().remove(firstNameColumn);
    }
    @FXML
    private void updateFilteredData() {
        filteredData.clear();

        try {
            for (Ticket p : masterdata) {
                //System.out.println("Ticket number get: " + p.getticketNO());
                if (matchesFilter(p)) {
                    filteredData.add(p);
                }
            }
            ticketTable.setItems(filteredData);
            // Must re-sort table after items changed
            //reapplyTableSortOrder();
        }
        catch(Exception exc){System.out.println(exc.toString());}
    }
    /**
     * Returns true if the ticket matches the filter.
     *
     * @param ticket
     * @return
     */
    private boolean matchesFilter(Ticket ticket) {
        String filterString = ticketNoFilter.getText();
        //System.out.println("filter string " + filterString);

        if (ticket.getticketNO() == null){
            return false;
        }
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (ticket.getticketNO().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        //ArrayList<TableColumn<Ticket, ?>> sortOrder = new ArrayList<>(ticketTable.getSortOrder());
        //ticketTable.getSortOrder().clear();
        //ticketTable.getSortOrder().addAll(sortOrder);
    }

    public ObservableList<Ticket> getMasterdata(){
        return masterdata;
    }

    @FXML
    private void handleNew() {
        //mainApp.getTicketData().clear();
        //mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }
    @FXML
    private void handleCopyFile(String fileFromPath, String fileToPath, String pathTag){

        File fileFrom = new File(fileFromPath);

        if (fileFrom != null) {
            try {
                String strDirectoy = fileToPath;
                boolean fileExists = (
                        new File(strDirectoy)).exists();
                boolean success = (
                        new File(strDirectoy)).mkdir();
                if (success || fileExists) {
                    System.out.println("Directory: "+ strDirectoy + " created");

                    File fileTo = new File( strDirectoy.replace("/","\\") + "\\" + pathTag.replace("/","\\") + fileFrom.getName());

                    //AlertMsg.ShowMsg("Copy","Copy","Copying from: " + fileFrom.getPath().toString(),AlertType.INFORMATION);
                    //AlertMsg.ShowMsg("Copy","Copy","Copying to: " + fileTo.getPath().toString(),AlertType.INFORMATION);



                    FileCopyUtils.copy(fileFrom, fileTo);
                }

                //folder.mkdir();

            } catch (Exception exc) {
                AlertMsg.ShowMsg("Error","Error",exc.toString(),AlertType.ERROR);
            }
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }
    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        AlertMsg.ShowMsg("Ticket application","About","Author: Lukasz homik",AlertType.INFORMATION);

    }
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = ticketTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            ticketTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            AlertMsg.ShowMsg("No Selection","No Person Selected","Please select a person in the table.",AlertType.WARNING);
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewTicket() {
        Ticket tempTicket = new Ticket();

        if (tempTicket != null) {
            boolean okClicked = mainApp.showTicketNewDialog(tempTicket);
            if (okClicked) {
                try{
                    Connection c ;
                    c = DBConnect.connect();
                    String SQL = "INSERT INTO tickets (TICKET_NO,TICKET_TITLE,IMPACT_ALL," +
                            "IMPACT_OPS,IMPACT_OR,IMPACT_TSP,IMPACT_EBS,IMPACT_SAP_USR,IMPACT_DB,IMPACT_XD,IMPACT_FINANCE,DESCRIPTION,PRIORITY" +
                            ",REPORTED_BY,INSERT_USER, INSERT_DATE, CLUSTER) VALUES ('" + tempTicket.getticketNO() + "','" + tempTicket.getticketTitle()
                            + "','" + tempTicket.getticketImpactAll() + "','" + tempTicket.getticketticketImpactOps()
                            + "','" + tempTicket.getticketImpactOR() + "','" + tempTicket.getticketImpactTsp() + "','" + tempTicket.getticketImpactEBS()
                            + "','" + tempTicket.getticketImpactSAP() + "','" + tempTicket.getticketImpactDB() + "','" + tempTicket.getticketImpactXD()
                            + "','" + tempTicket.getticketImpactFin() + "','" + tempTicket.getticketDesc() + "','" + tempTicket.getticketPriority()
                            + "','" + tempTicket.getticketRepBy()  + "','" + tempTicket.getticketInsUser() + "','" + tempTicket.getticketInsDate()
                            + "','" + tempTicket.getticketCluster()+  "')";







                    //String SQL = "UPDATE tickets SET IMPACT_ALL = 'NO', DESCRIPTION = 'Ł' WHERE ID = 89";
                    System.out.println(SQL);
                    //System.out.println("len" + tempTicket.getticketAttachments().length());
                    c.createStatement().executeUpdate(SQL);
                    ticketTable.setItems(mainApp.getTicketData("SELECT ID, TICKET_NO, TICKET_TITLE, TICKET_OWNER, OPEN_DATE," +
                            "CLOSE_DATE, ACC_OWNER, IMPACT_ALL,IMPACT_OPS,IMPACT_OR,IMPACT_TSP,IMPACT_EBS,IMPACT_SAP_USR," +
                            "IMPACT_DB,IMPACT_XD,IMPACT_FINANCE,DESCRIPTION,PRIORITY,REPORTED_BY," +
                            "STATUS,INSERT_DATE,INSERT_USER, CLUSTER FROM  tickets ORDER BY ID DESC"));

//Copy files from attachment string----------------------------------------------
                    if (tempTicket.getticketAttachments() != null) {
                        try {
                            char ch = 0;
                            String fileToCopy = "";

                            for (int i = 0; i < tempTicket.getticketAttachments().length(); i++) {
                                ch = tempTicket.getticketAttachments().charAt(i);
                                if (ch != ',') {
                                    fileToCopy = fileToCopy + ch;
                                }
                                if (ch == ',' | i == tempTicket.getticketAttachments().length() - 1) {
                                    locDate = LocalDateTime.now();
                                    System.out.println(locDate.format(DATE_FORMATTER));
                                    handleCopyFile(fileToCopy.toString().replace("\\","/"),System.getProperty("user.dir").replace("\\","/") + "/files/" + tempTicket.getticketNO(), locDate.format(DATE_FORMATTER) + "_");
                                    fileToCopy = "";
                                }
                            }
                        } catch (Exception exc) {
                            //System.out.println(exc.getMessage().toString());
                            mainApp.seterrorMsg("File copy error");
                        }
                    }
                }
                catch(Exception exc){
                    //System.out.println("DB error insert: " + exc.toString());
                    AlertMsg.ShowMsg("Insert Error","",exc.toString(),Alert.AlertType.WARNING);
                }
                //showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            AlertMsg.ShowMsg("No Selection","No Ticket","Please fill in ticket details",Alert.AlertType.WARNING);
        }

//        Ticket tempPerson = new Ticket();
//        boolean okClicked = mainApp.showTicketNewDialog(tempPerson);
//        if (okClicked) {
//            mainApp.getTicketData().add(tempPerson);
//        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();

        if (selectedTicket != null) {
            boolean okClicked = mainApp.showTicketEditDialog(selectedTicket);
            if (okClicked) {
                try{
                    Connection c ;
                    c = DBConnect.connect();
                    String SQL = mainApp.getSqls("UPDATE");



//                    String SQL = "UPDATE tickets SET TICKET_NO = " + "'" + selectedTicket.getticketNO() + "'" +
//                            ",TICKET_OWNER = '" + selectedTicket.getticketOwner() + "'" +
//                            ",OPEN_DATE = '" + DateUtil.parse(selectedTicket.getticketOpenDate()) + "'" +
//                            ",TICKET_TITLE = '" + selectedTicket.getticketTitle() +
//                            "' WHERE ID = '" + selectedTicket.getticketID() + "'";
                    System.out.println(SQL);
//            c.createStatement().executeQuery(SQL);
                    c.createStatement().executeUpdate(SQL);

                }
                catch(Exception exc){System.out.println("DB error update: " + exc.toString());}
                //showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleFiltering(){


    }
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        ticketIDColumn.setCellValueFactory(cellData -> cellData.getValue().ticketIDProperty());
        ticketNOColumn.setCellValueFactory(cellData -> cellData.getValue().ticketNOProperty());
        ticketTitleColumn.setCellValueFactory(cellData -> cellData.getValue().ticketTitleProperty());
        ticketOwnerColumn.setCellValueFactory(cellData -> cellData.getValue().ticketOwnerProperty());
        ticketOpenDColumn.setCellValueFactory(cellData -> cellData.getValue().OpenDateProperty());
        ticketCloseDColumn.setCellValueFactory(cellData -> cellData.getValue().CloseDateProperty());
        ticketAccOwnerDCol.setCellValueFactory(cellData -> cellData.getValue().ticketAccOwnerProperty());
        ticketImpactAllCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactAllProperty());
        ticketImpactOpsCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactOpsProperty());
        ticketImpactORCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactORProperty());
        ticketImpactTspCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactTspProperty());
        ticketImpactEBSCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactEBSProperty());
        ticketImpactSAPCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactSAPProperty());
        ticketImpactDBCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactDBProperty());
        ticketImpactXDCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactXDProperty());
        ticketImpactFinCol.setCellValueFactory(cellData -> cellData.getValue().ticketImpactFinProperty());
        ticketPriorityCol.setCellValueFactory(cellData -> cellData.getValue().ticketPriorityProperty());
        ticketClusterCol.setCellValueFactory(cellData -> cellData.getValue().ticketClusterProperty());

        ticketPriorityCol.setCellFactory(column -> {
            return new TableCell<Ticket, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        setText(item);

                        // Style all dates in March with a different color.
                        if (item.contentEquals("Medium") ) {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #ffd480");
                        } else if (item.contentEquals("High")){
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #ff4d4d");
                        }
                        else {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #00b33c");
                        }
//                        else if (item.contentEquals("Low")){
//                            setTextFill(Color.BLACK);
//                            setStyle("-fx-background-color: #00b33c");
//                        }
                    }
                }
            };
        });

        ticketRepByCol.setCellValueFactory(cellData -> cellData.getValue().ticketRepByProperty());
        ticketStatusCol.setCellValueFactory(cellData -> cellData.getValue().ticketStatusProperty());

        ticketStatusCol.setCellFactory(column -> {
            return new TableCell<Ticket, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        setText(item);

                        // Style all dates in March with a different color.
                        if (item.contentEquals("IN PROGRESS") ) {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #ffd480");
                        }
                        else if (item.contentEquals("IN QUEUE")){
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #ff4d4d");
                        }
                        else if (item.contentEquals("CLOSED")){
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #00b33c");
                        }
                        else if (item.contentEquals("SENT FOR TESTING")){
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: #ffd480");
                        }
                    }
                }
            };
        });

        ticketInsDateCol.setCellValueFactory(cellData -> cellData.getValue().ticketInsDateProperty());
        ticketInsUserCol.setCellValueFactory(cellData -> cellData.getValue().ticketInsUserProperty());
        ticketDescCol.setCellValueFactory(cellData -> cellData.getValue().ticketDescProperty());



        //ticketTable.getColumns().addAll(firstNameColumn, lastNameColumn);

        //showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
//        ticketTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPersonDetails(newValue));


    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowTicketStatistics() {
        mainApp.showTicketStatistics();
    }
}