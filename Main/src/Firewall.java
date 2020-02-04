import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Firewall {
    HashMap<String, ArrayList<FirewallRules>> map;


    public Firewall(String filename) throws IOException {
        map = new HashMap<>();
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            String[] temp = line.split(",");
            String direction = temp[0];
            String protocol = temp[1];
            String keyPair = direction +"-" + protocol;
            String port = temp[2];
            String IP = temp[3];
            FirewallRules rule = new FirewallRules(direction,protocol,port,IP);
            if (!map.containsKey(keyPair)) {
                map.put(keyPair, new ArrayList<>());
            }
            map.get(keyPair).add(rule);
            line = br.readLine();
        }
        br.close();
    }

    // Check if the provided args is accepted by the fire wall.
    public boolean acceptPacket(String direction, String protocol, int port, String IP) {
        String candidateKeyPair = direction + "-" + protocol;
        long ip = FirewallRules.ipToLong(IP);
        if (this.map.containsKey(candidateKeyPair)) {
            ArrayList<FirewallRules> result = map.get(candidateKeyPair);
            for (FirewallRules fr : result) {
                if (port >= fr.getLowerBoundPort() && port <= fr.getUpperBoundPort()) {
                    if ( ip >= fr.getLowerBoundIP() && ip <= fr.getUpperBoundIP()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // inbound,tcp,80,192.168.1.2
    public static void main(String[] args) throws IOException {
        String direction = "inbound";
        String protocol =  "tcp";
        int port = 80;
        String IP = "192.168.1.2";
        Firewall fw = new Firewall("data/TestFile");
        System.out.println(fw.acceptPacket(direction, protocol,port,IP));
    }
}
