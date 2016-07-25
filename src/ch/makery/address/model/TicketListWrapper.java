package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class TicketListWrapper {

    private List<Ticket> tickets;

    @XmlElement(name = "person")
    public List<Ticket> getPersons() {
        return tickets;
    }

    public void setPersons(List<Ticket> persons) {
        this.tickets = persons;
    }
}