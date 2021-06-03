package ezbnb;

import ezbnb.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired RoomRepository roomRepository;

	private Room getRoomByRoomId(long roomId) {
			
		// Room 테이블에서 roomId의 Data 조회 -> room
		Optional<Room> rooms = roomRepository.findById(roomId);

		if(!rooms.isPresent()) {

			System.out.println("\n\n##### room table no data found by : " + roomId + "\n\n");
			return null;

		}

		return rooms.get();
	}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewRegistered_RegisterReview(@Payload ReviewRegistered reviewRegistered){

        if(!reviewRegistered.validate()) return;

		// ----------------------------------------
		// 리뷰 등록 시 -> Room의 리뷰 카운트 증가
		//-----------------------------------------
		
		System.out.println("\n\n##### listener RegisterReview : " + reviewRegistered.toJson() + "\n\n");

		long roomId = reviewRegistered.getRoomId()== null ? 0 : reviewRegistered.getRoomId(); // 등록된 리뷰의 RoomID
		double score = reviewRegistered.getScore() == null ? 0 : reviewRegistered.getScore();
		
		Room room = getRoomByRoomId(roomId);
		if(room == null) {
			return;
		}

		int reviewCnt = (room.getReviewCount() == null ?0 : room.getReviewCount()) +1;
		room.setReviewCount(reviewCnt); // 리뷰건수 증가/감소
		
		double roomScore = room.getScore() == null ?0 : room.getScore();
		
		if( score >0) {
			
			score = (roomScore + score)/reviewCnt;
			room.setScore(score);
		}
		roomRepository.save(room);

            
    }

	@StreamListener(KafkaProcessor.INPUT)
    public void wheneverBooked_UpdateStatus(@Payload Booked booked){

        if(!booked.validate()) return;

        System.out.println("\n\n##### listener UpdateStatus : " + booked.toJson() + "\n\n");

		long roomId = booked.getRoomId()== null ? 0 : booked.getRoomId(); 
		
		Room room = getRoomByRoomId(roomId);
		if(room == null) {
			return;
		}

		room.setStatus("booked");
        roomRepository.save(room);
            
    }

	@StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCanceled_UpdateStatus(@Payload PayCanceled payCanceled){

        if(!payCanceled.validate()) return;

        System.out.println("\n\n##### listener UpdateStatus : " + payCanceled.toJson() + "\n\n");
       
		long roomId = payCanceled.getRoomId()== null ? 0 : payCanceled.getRoomId(); 
		
		Room room = getRoomByRoomId(roomId);
		if(room == null) {
			return;
		}

		room.setStatus("empty");
        roomRepository.save(room);
            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
