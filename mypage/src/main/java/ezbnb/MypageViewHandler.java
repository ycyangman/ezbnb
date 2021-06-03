package ezbnb;

import ezbnb.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBooked_then_CREATE_1 (@Payload Booked booked) {
        try {

            if (!booked.validate()) return;

            System.out.println("Booked " + booked.toJson());

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함

            // view 객체에 이벤트의 Value 를 set 함
            mypage.setBookId(booked.getId());
            mypage.setRoomId(booked.getRoomId());
            mypage.setRoomName(booked.getRoomName());
            mypage.setPrice(booked.getPrice());
            mypage.setAddress(booked.getAddress());
            mypage.setGuestName(booked.getGuestName());
            mypage.setBookingDate(booked.getBookingDate());
            mypage.setStatus(booked.getStatus());

            // view 레파지 토리에 save
            mypageRepository.save(mypage);

            System.out.println("=========================whenBooked_then_CREATE_1 called by" + mypage);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookingCanceled_then_UPDATE_1(@Payload BookingCanceled bookingCanceled) {
        try {
            if (!bookingCanceled.validate()) return;
                // view 객체 조회
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayApproved_then_UPDATE_2(@Payload PayApproved payApproved) {
        try {
            if (!payApproved.validate()) return;
                // view 객체 조회
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCanceled_then_UPDATE_3(@Payload PayCanceled payCanceled) {
        try {
            if (!payCanceled.validate()) return;
                // view 객체 조회
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}