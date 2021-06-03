package ezbnb;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Room_table")
public class Room {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String roomName;
    private Double price;
    private String address;
    private String status;
    private String host;
	private Double score;
	private Integer reviewCount;
	
    /*
    
    @PostLoad: 해당 엔티티를 새로 불러오거나 refresh 한 이후.
    @PrePersist: 해당 엔티티를 저장하기 이전
    @PostPersist: 해당 엔티티를 저장한 이후
    @PreUpdate: 해당 엔티티를 업데이트 하기 이전
    @PostUpdate: 해당 엔티티를 업데이트 한 이후
    @PreRemove: 해당 엔티티를 삭제하기 이전
    @PostRemove: 해당 엔티티를 삭제한 이후
     */

    //해당 엔티티를 저장한 이후
    @PostPersist
    public void onPostPersist(){

        System.out.println("ROOM.onPostPersist calling");
        RoomRegistered roomRegistered = new RoomRegistered();
        BeanUtils.copyProperties(this, roomRegistered);
        roomRegistered.publishAfterCommit();

    }

    //해당 엔티티를 삭제한 이후
    @PostRemove
    public void onPostRemove(){
        System.out.println("ROOM.onPostRemove calling");
        RoomDeleted roomDeleted = new RoomDeleted();
        BeanUtils.copyProperties(this, roomDeleted);
        roomDeleted.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
	
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
}
