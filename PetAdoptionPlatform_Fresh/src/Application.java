public class Application {
    private int id;
    private int petId;
    private int adopterId;
    private String message;
    private String status;

    public Application(int id, int petId, int adopterId,
                       String message, String status) {
        this.id = id;
        this.petId = petId;
        this.adopterId = adopterId;
        this.message = message;
        this.status = status;
    }

    public int getId() { return id; }
    public int getPetId() { return petId; }
    public int getAdopterId() { return adopterId; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
}
