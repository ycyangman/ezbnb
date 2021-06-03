
package ezbnb;

public class ReviewRegistered extends AbstractEvent {

    private Long id;
	private Long roomId;
	private Double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
	
	public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

