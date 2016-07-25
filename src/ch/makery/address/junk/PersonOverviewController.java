//package ch.makery.address.junk;
//
//import ch.makery.address.DBConnect;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import ch.makery.address.MainApp;
//import ch.makery.address.model.Ticket;
//
//import java.sql.Connection;
//
//public class PersonOverviewController {
//    @FXML
//    private TableView<Ticket> personTable;
//    @FXML
//    private TableColumn<Ticket, String> firstNameColumn;
//    @FXML
//    private TableColumn<Ticket, String> lastNameColumn;
//
//    @FXML
//    private Label firstNameLabel;
//    @FXML
//    private Label lastNameLabel;
//    @FXML
//    private Label streetLabel;
//    @FXML
//    private Label postalCodeLabel;
//    @FXML
//    private Label cityLabel;
//    @FXML
//    private Label birthdayLabel;
//
//    // Reference to the main application.
//    private MainApp mainApp;
//
//    /**
//     * The constructor.
//     * The constructor is called before the initialize() method.
//     */
//    public PersonOverviewController() {
//    }
//    /**
//     * Fills all text fields to show details about the person.
//     * If the specified person is null, all text fields are cleared.
//     *
//     * @param person the person or null
//     */
//
//    /**
//     * Called when the user clicks on the delete button.
//     */
//    @FXML
//    private void handleDeletePerson() {
//        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            personTable.getItems().remove(selectedIndex);
//        } else {
//            // Nothing selected.
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
//    }
//    /**
//     * Called when the user clicks the new button. Opens a dialog to edit
//     * details for a new person.
//     */
//    @FXML
//    private void handleNewPerson() {
//        Ticket tempPerson = new Ticket();
//        boolean okClicked = mainApp.showTicketEditDialog(tempPerson);
//        if (okClicked) {
//            mainApp.getTicketData().add(tempPerson);
//        }
//    }
//
//    /**
//     * Called when the user clicks the edit button. Opens a dialog to edit
//     * details for the selected person.
//     */
//    @FXML
//    private void handleEditPerson() {
//        Ticket selectedPerson = personTable.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showTicketEditDialog(selectedPerson);
//            if (okClicked) {
//                //showPersonDetails(selectedPerson);
//            }
//
//        } else {
//            // Nothing selected.
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
//
//        try{
//            Connection c ;
//
//            c = DBConnect.connect();
//            String SQL = "UPDATE Persons SET LAST_NAME = " + "'" + selectedPerson.getLastName() + "' WHERE FIRST_NAME = '" + selectedPerson.getFirstName() + "'";
//            System.out.println(SQL);
////            c.createStatement().executeQuery(SQL);
//            c.createStatement().executeUpdate(SQL);
//
//        }
//        catch(Exception exc){System.out.println("DB error");}
//
//
//
//    }
//
//    /**
//     * Initializes the controller class. This method is automatically called
//     * after the fxml file has been loaded.
//     */
//    @FXML
//    private void initialize() {
//        // Initialize the person table with the two columns.
//        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
//        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
//
//        //showPersonDetails(null);
//
//        // Listen for selection changes and show the person details when changed.
////        personTable.getSelectionModel().selectedItemProperty().addListener(
////                (observable, oldValue, newValue) -> showPersonDetails(newValue));
//
//
//    }
//
//    /**
//     * Is called by the main application to give a reference back to itself.
//     *
//     * @param mainApp
//     */
//    public void setMainApp(MainApp mainApp) {
//        this.mainApp = mainApp;
//
//        // Add observable list data to the table
//        personTable.setItems(mainApp.getTicketData());
//    }
//}