package ezbnb;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="Booking_table")
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long roomId;
    private String roomName;
    private String address;
    private Double price;
    private Long guestId;
    private String guestName;
    private String guestEmail;
    private String bookingDate;
    private String status;
    private String host;

    //해당 엔티티를 저장한 이후
    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        //실시간 호출
        ezbnb.external.Payment payment = new ezbnb.external.Payment();
        // mappings goes here

        payment.setBookId(getId());
        payment.setRoomId(getRoomId());
        payment.setGuestName(getGuestName());
        payment.setPayAmt(getPrice());
        payment.setGuestName(getGuestName());
        payment.setPayDate(new SimpleDateFormat("YYYYMMdd").format(new Date()));
        payment.setStatus("payRequested");

        //TODO
        try {
            BookingApplication.applicationContext.getBean(ezbnb.external.PaymentService.class)
                .makePay(payment);
        }catch(Exception e) {
            //throw new RuntimeException("결제서비스 호출 실패입니다."+e.getLocalizedMessage());

            e.printStackTrace();
        }

        // 결제까지 완료되면 최종적으로 예약 완료 이벤트 발생
        Booked booked = new Booked();
        BeanUtils.copyProperties(this, booked);
        booked.setStatus("booked");
        booked.publishAfterCommit();

        System.out.println("-------------------onPostPersist called!!------------------------");
    }

    @PostRemove
    public void onPostRemove(){

        BookingCanceled bookingCanceled = new BookingCanceled();
        BeanUtils.copyProperties(this, bookingCanceled);
        bookingCanceled.setStatus("BookCanceled");
        bookingCanceled.publishAfterCommit();
    }

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
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
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
}
