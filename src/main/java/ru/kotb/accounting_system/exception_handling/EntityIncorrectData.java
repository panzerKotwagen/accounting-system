package ru.kotb.accounting_system.exception_handling;


/**
 * Class that provides the JSON message that should be sent when an
 * exception was occurred.
 */
public class EntityIncorrectData {

    /**
     * The information message.
     */
    private String info;

    /**
     * Constructs the object with specified message.
     * @param info the info message
     */
    public EntityIncorrectData(String info) {
        this.info = info;
    }

    /**
     * Sets the message.
     * @param info the info message
     */
    public void setInfo(String info) {
        this.info = info;
    }
}
