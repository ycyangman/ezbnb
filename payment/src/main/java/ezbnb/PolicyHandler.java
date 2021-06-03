package ezbnb;

import ezbnb.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository paymentRepository;


    /**
     * 예약취소시 KafaConsumer
     * 결재취소상태 반영
    */
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookingCanceled_CancelPay(@Payload BookingCanceled bookingCanceled){

        if(!bookingCanceled.validate()) return;

        System.out.println("\n\n##### listener CancelPay : " + bookingCanceled.toJson() + "\n\n");

        long bookId = bookingCanceled.getBookId()== null ? 0 : bookingCanceled.getBookId();

        // payments 테이블에서 bookId 의 Data 조회 -> room
		List<Payment> payments = paymentRepository.findByBookId(bookId);

		if(ObjectUtils.isEmpty(payments)) {

			System.out.println("\n\n##### payments table no data found by : " + bookId + "\n\n");
			return ;

		}

		Payment payment =  payments.get(0);
        payment.setStatus("payCanceled");
        paymentRepository.save(payment);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
