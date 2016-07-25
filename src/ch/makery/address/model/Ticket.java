package ch.makery.address.model;

import java.time.LocalDate;

import ch.makery.address.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Ticket {

//    private final StringProperty firstName;
//    private final StringProperty lastName;

    private final StringProperty ticketID;
    private final StringProperty ticketNO;
    private final StringProperty ticketTitle;
    private final StringProperty ticketOpenDate;
    private final StringProperty ticketCloseDate;
    private final StringProperty ticketOwner;
    private final StringProperty ticketAccOwner;
    private final StringProperty ticketImpactAll;
    private final StringProperty ticketImpactOps;
    private final StringProperty ticketImpactOR;
    private final StringProperty ticketImpactTsp;
    private final StringProperty ticketImpactEBS;
    private final StringProperty ticketImpactSAP;
    private final StringProperty ticketImpactDB;
    private final StringProperty ticketImpactXD;
    private final StringProperty ticketImpactFin;
    private final StringProperty ticketDesc;
    private final StringProperty ticketPriority;
    private final StringProperty ticketRepBy;
    private final StringProperty ticketStatus;
    private final StringProperty ticketInsDate;
    private final StringProperty ticketInsUser;
    private final StringProperty ticketCluster;

    private final StringProperty ticketAttachments;
//    private final ObjectProperty<LocalDate> birthday;



    /**
     * Default constructor.
     */
    public Ticket() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param ticketID
     * @param ticketNO
     * @param ticketOwner
     */
    public Ticket(String ticketID, String ticketNO,String ticketTitle, String ticketOwner, String ticketOpen, String ticketClose,
                  String ticketAccOwner, String ticketImpactAll, String ticketImpactOps, String ticketImpactOR, String ticketImpactTsp,
                  String ticketImpactEBS, String ticketImpactSAP, String ticketImpactDB, String ticketImpactXD, String ticketImpactFin
    ,String ticketDesc, String ticketPriority, String ticketRepBy, String ticketStatus, String ticketInsDate, String ticketInsUser, String ticketAtt, String ticketCluster) {
        //this.firstName = new SimpleStringProperty(firstName);
        //this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.

        this.ticketID = new SimpleStringProperty(ticketID);
        this.ticketNO = new SimpleStringProperty(ticketNO);
        this.ticketTitle = new SimpleStringProperty(ticketTitle);
        this.ticketOpenDate = new SimpleStringProperty(ticketOpen);
        this.ticketCloseDate = new SimpleStringProperty(ticketClose);
        this.ticketOwner = new SimpleStringProperty(ticketOwner);
        this.ticketAccOwner = new SimpleStringProperty(ticketAccOwner);
        this.ticketImpactAll = new SimpleStringProperty(ticketImpactAll);
        this.ticketImpactOps = new SimpleStringProperty(ticketImpactOps);
        this.ticketImpactOR = new SimpleStringProperty(ticketImpactOR);
        this.ticketImpactTsp = new SimpleStringProperty(ticketImpactTsp);
        this.ticketImpactEBS = new SimpleStringProperty(ticketImpactEBS);
        this.ticketImpactSAP = new SimpleStringProperty(ticketImpactSAP);
        this.ticketImpactDB = new SimpleStringProperty(ticketImpactDB);
        this.ticketImpactXD = new SimpleStringProperty(ticketImpactXD);
        this.ticketImpactFin = new SimpleStringProperty(ticketImpactFin);
        this.ticketDesc = new SimpleStringProperty(ticketDesc);
        this.ticketPriority = new SimpleStringProperty(ticketPriority);
        this.ticketRepBy = new SimpleStringProperty(ticketRepBy);
        this.ticketStatus = new SimpleStringProperty(ticketStatus);
        this.ticketInsDate = new SimpleStringProperty(ticketInsDate);
        this.ticketInsUser = new SimpleStringProperty(ticketInsUser);
        this.ticketAttachments = new SimpleStringProperty(ticketAtt);
        this.ticketCluster = new SimpleStringProperty(ticketCluster);
//        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));


    }

    public String getticketID() {
        return ticketID.get();
    }

    public void setticketID(String ticketID) {
        this.ticketID.set(ticketID);
    }

    public StringProperty ticketIDProperty() {
        return ticketID;
    }


    public String getticketNO() {
        return ticketNO.get();
    }

    public void setticketNO(String ticketNO) {
        this.ticketNO.set(ticketNO);
    }

    public StringProperty ticketNOProperty() {
        return ticketNO;
    }

    public String getticketTitle() {
        return ticketTitle.get();
    }

    public void setticketTitle(String ticketTitle) {
        this.ticketTitle.set(ticketTitle);
    }
    public StringProperty ticketTitleProperty() {
        return ticketTitle;
    }


    public String getticketOwner() {
        return ticketOwner.get();
    }

    public void setticketOwner(String ticketOwner) {
        this.ticketOwner.set(ticketOwner);
    }
    public StringProperty ticketOwnerProperty() {
        return ticketOwner;
    }


    public String getticketOpenDate() {
        return ticketOpenDate.get();
    }

    public void setTicketOpenDate(String ticketOpen) {
        this.ticketOpenDate.set(ticketOpen);
    }

    public StringProperty OpenDateProperty() {
        return ticketOpenDate;
    }


    public String getticketCloseDate() {
        return ticketCloseDate.get();
    }

    public void setTicketCloseDate(String ticketClose) {
        this.ticketCloseDate.set(ticketClose);
    }

    public StringProperty CloseDateProperty() {
        return ticketCloseDate;
    }


    public String getticketAccOwner() {
        return ticketAccOwner.get();
    }

    public void setticketAccOwner(String ticketAcc) {
        this.ticketAccOwner.set(ticketAcc);
    }

    public StringProperty ticketAccOwnerProperty() {
        return ticketAccOwner;
    }


    public String getticketImpactAll() {
        return ticketImpactAll.get();
    }

    public void setticketImpactAll(String ticketimpact) {
        this.ticketImpactAll.set(ticketimpact);
    }

    public StringProperty ticketImpactAllProperty() {
        return ticketImpactAll;
    }


    public String getticketticketImpactOps() {
        return ticketImpactOps.get();
    }

    public void setticketImpactOps(String ticketimpact) {
        this.ticketImpactOps.set(ticketimpact);
    }

    public StringProperty ticketImpactOpsProperty() {
        return ticketImpactOps;
    }


    public String getticketImpactOR() {
        return ticketImpactOR.get();
    }

    public void setticketImpactOR(String ticketimpact) {
        this.ticketImpactOR.set(ticketimpact);
    }

    public StringProperty ticketImpactORProperty() {
        return ticketImpactOR;
    }


    public String getticketImpactTsp() {
        return ticketImpactTsp.get();
    }

    public void setticketImpactTsp(String ticketimpact) {
        this.ticketImpactTsp.set(ticketimpact);
    }

    public StringProperty ticketImpactTspProperty() {
        return ticketImpactTsp;
    }


    public String getticketImpactEBS() {
        return ticketImpactEBS.get();
    }

    public void setticketImpactEBS(String ticketimpact) {
        this.ticketImpactEBS.set(ticketimpact);
    }

    public StringProperty ticketImpactEBSProperty() {
        return ticketImpactEBS;
    }


    public String getticketImpactSAP() {
        return ticketImpactSAP.get();
    }

    public void setticketImpactSAP(String ticketimpact) {
        this.ticketImpactSAP.set(ticketimpact);
    }

    public StringProperty ticketImpactSAPProperty() {
        return ticketImpactSAP;
    }


    public String getticketImpactDB() {
        return ticketImpactDB.get();
    }

    public void setticketImpactDB(String ticketimpact) {
        this.ticketImpactDB.set(ticketimpact);
    }

    public StringProperty ticketImpactDBProperty() {
        return ticketImpactDB;
    }


    public String getticketImpactXD() {
        return ticketImpactXD.get();
    }

    public void setticketImpactXD(String ticketimpact) {
        this.ticketImpactXD.set(ticketimpact);
    }

    public StringProperty ticketImpactXDProperty() {
        return ticketImpactXD;
    }


    public String getticketImpactFin() {
        return ticketImpactFin.get();
    }

    public void setticketImpactFin(String ticketimpact) {
        this.ticketImpactFin.set(ticketimpact);
    }

    public StringProperty ticketImpactFinProperty() {
        return ticketImpactFin;
    }


    public String getticketDesc() {
        return ticketDesc.get();
    }

    public void setticketDesc(String ticketDesc) {
        this.ticketDesc.set(ticketDesc);
    }

    public StringProperty ticketDescProperty() {
        return ticketDesc;
    }


    public String getticketPriority() {
        return ticketPriority.get();
    }

    public void setticketPriority(String ticketPriority) {
        this.ticketPriority.set(ticketPriority);
    }

    public StringProperty ticketPriorityProperty() {
        return ticketPriority;
    }


    public String getticketRepBy() {
        return ticketRepBy.get();
    }

    public void setticketRepBy(String ticketRepBy) {
        this.ticketRepBy.set(ticketRepBy);
    }

    public StringProperty ticketRepByProperty() {
        return ticketRepBy;
    }


    public String getticketStatus() {
        return ticketStatus.get();
    }

    public void setticketStatus(String ticketStatus) {
        this.ticketStatus.set(ticketStatus);
    }

    public StringProperty ticketStatusProperty() {
        return ticketStatus;
    }


    public String getticketInsDate() {
        return ticketInsDate.get();
    }

    public void setticketInsDate(String ticketInsDate) {
        this.ticketInsDate.set(ticketInsDate);
    }

    public StringProperty ticketInsDateProperty() {
        return ticketInsDate;
    }


    public String getticketInsUser() {
        return ticketInsUser.get();
    }

    public void setticketInsUser(String ticketInsUser) {
        this.ticketInsUser.set(ticketInsUser);
    }

    public StringProperty ticketInsUserProperty() {
        return ticketInsUser;
    }


    public String getticketAttachments() {
        return ticketAttachments.get();
    }

    public void setticketAttachments(String ticketAttachments) {
        this.ticketAttachments.set(ticketAttachments);
    }

    public StringProperty ticketAttachmentsProperty() {
        return ticketAttachments;
    }


    public String getticketCluster() {
        return ticketCluster.get();
    }

    public void setticketCluster(String ticketInsUser) {
        this.ticketCluster.set(ticketInsUser);
    }

    public StringProperty ticketClusterProperty() {
        return ticketCluster;
    }

}