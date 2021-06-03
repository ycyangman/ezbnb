package ezbnb;

import ezbnb.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired MsgRepository msgRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBooked_SendMsg(@Payload Booked booked){

        if(!booked.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + booked.toJson() + "\n\n");

        // Sample Logic //
        Msg msg = new Msg();
        msgRepository.save(msg);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookingCanceled_SendMsg(@Payload BookingCanceled bookingCanceled){

        if(!bookingCanceled.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + bookingCanceled.toJson() + "\n\n");

        // Sample Logic //
        Msg msg = new Msg();
        msgRepository.save(msg);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayApproved_SendMsg(@Payload PayApproved payApproved){

        if(!payApproved.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + payApproved.toJson() + "\n\n");

        // Sample Logic //
        Msg msg = new Msg();
        msgRepository.save(msg);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCanceled_SendMsg(@Payload PayCanceled payCanceled){

        if(!payCanceled.validate()) return;

        System.out.println("\n\n##### listener SendMsg : " + payCanceled.toJson() + "\n\n");

        // Sample Logic //
        sendMsg("", "");
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


    private void sendMsg(String receiver, String message) {
        Msg msg = new Msg();
        //msg.setReceiver(String.valueOf(receiver));
        //msg.setMessage(message);
        msgRepository.save(msg);
    }

}
