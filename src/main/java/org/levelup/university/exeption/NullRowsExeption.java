package org.levelup.university.exeption;

public class NullRowsExeption extends Exception{
    public String messageException;

    public NullRowsExeption(String messageException) {
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
