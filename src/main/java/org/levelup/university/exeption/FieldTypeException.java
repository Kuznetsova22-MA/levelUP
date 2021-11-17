package org.levelup.university.exeption;

public class FieldTypeException extends Exception{
    private String messageException;

    public FieldTypeException(String messageException) {
        super(messageException);
        this.messageException = messageException;
    }

    public String getMessageException() {
        return messageException;
    }

    public void setMessageException(String messageException) {
        this.messageException = messageException;
    }
}
