package ezbnb;

public class RoomRegistered extends AbstractEvent {

    private Long id;

    public RoomRegistered(){
        super();

        System.out.println("RoomRegistered.RoomRegistered called=================================================");

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
