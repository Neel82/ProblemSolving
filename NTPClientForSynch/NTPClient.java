package NTPClientForSynch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;



public final class NTPClient{
    public static void main(String[] args){

        if(args.length==0){
            System.err.println("usage: ntp client hostage name  or addres details...");
            System.exit(1);
        }
        final NTPUDPClient client = new NTPUDPClient();
        // timeout is set if the response is taking more than 10 seconds to complete
        client.setDefaultTimeout(10000);
        try {
            client.open();
            for(final String arg: args){
                System.out.println();
                try {
                    final InetAddress hostAddr = InetAddress.getByName(arg);
                    System.out.println("> " + hostAddr.getHostName() + "/" + hostAddr.getHostAddress());
                    final TimeInfo info = client.getTime(hostAddr);
                    processResponse(info);
                } catch (IOException eiIoException) {
                    
                    eiIoException.printStackTrace();
                }

            }
        } catch (SocketException exception) {
            
            exception.printStackTrace();
        }
        client.close();

    }

    public static void processResponse(TimeInfo info) {
        NtpV3Packet message = info.getMessage();
       System.out.println("Reference TimeStamp:"+message.getReferenceTimeStamp().toDateString());
       // Originate Time is time request sent by client (t1)
       final TimeStamp originateTimestamp = message.getOriginateTimeStamp();
        System.out.println("Originate Time sent by client(t1) :"+originateTimestamp.toDateString());
        // Receive Time is time request received by server (t2)
        final TimeStamp receiveTimestamp = message.getReceiveTimeStamp();
        System.out.println("Receive time request received by server (t2): "+receiveTimestamp.toDateString());
        // Transmit time is time reply sent by server (t3)
        final TimeStamp transmiTimeStamp = message.getTransmitTimeStamp();
        System.out.println("Transmit Time is reply sent by server(t3): "+transmiTimeStamp.toDateString());
        // Destination time is time reply received by client (t4)
        final TimeStamp destinationTimeStamp = TimeStamp.getNtpTime(info.getReturnTime());
        System.out.println("Destination time is reply recevied by client(t4): "+destinationTimeStamp.toDateString());

        //Note that the local clock offset (or time drift) is calculated with respect to the local clock 
        //and the NTP server's clock according to this standard NTP equation.
       // LocalClockOffset = ((ReceiveTimestamp - OriginateTimestamp) + (TransmitTimestamp - DestinationTimestamp)) / 2

       info.computeDetails(); // compute offset/delay if not already done
        final Long offsetMillis = info.getOffset();
        final Long delayMillis = info.getDelay();
        final String delay = delayMillis == null ? "N/A" : delayMillis.toString();
        final String offset = offsetMillis == null ? "N/A" : offsetMillis.toString();

        System.out.println(" Roundtrip delay(ms)=" + delay + ", clock offset(ms)=" + offset); // offset in ms

    }
}