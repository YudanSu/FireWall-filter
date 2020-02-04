public class FirewallRules {
    private String direction;
    private String protocol;
    private int lowerBoundPort;
    private int upperBoundPort;
    private long lowerBoundIP;
    private long upperBoundIP;

    public FirewallRules(String dir, String proto, String port, String ipAddress) {
        this.direction = dir;
        this.protocol = proto;
        if (port.contains("-")) {
            String eles[] = port.split("-");
            lowerBoundPort = Integer.parseInt(eles[0]);
            upperBoundPort = Integer.parseInt(eles[1]);
        } else {
            lowerBoundPort = upperBoundPort = Integer.parseInt(port);
        }
        if (ipAddress.contains("-")) {
            String eles[] = ipAddress.split("-");
            lowerBoundIP = ipToLong(eles[0]);
            upperBoundIP = ipToLong(eles[1]);
        } else {
            lowerBoundIP = upperBoundIP = ipToLong(ipAddress);
        }

    }

    public static long ipToLong(String ipAddress) {
        String[] nums = ipAddress.split("\\.");
        long result = Long.parseLong(nums[0]) * 256*256*256 + Long.parseLong(nums[1]) *256*256
                + Long.parseLong(nums[2]) *256 + Long.parseLong(nums[3]);
        return result;
    }

    public int getLowerBoundPort() {
        return this.lowerBoundPort;
    }
    public int getUpperBoundPort() {
        return this.upperBoundPort;
    }
    public long getLowerBoundIP() {
        return this.lowerBoundIP;
    }
    public long getUpperBoundIP() {
        return this.upperBoundIP;
    }

}
