package Controller;

public class MediaNotFoundException extends RuntimeException {
    protected String illegalName;

    public MediaNotFoundException(String illegalName) {
        super(" was not found");
        this.illegalName = illegalName;
    }

    public String getIllegalName() {
        return illegalName;
    }
}
