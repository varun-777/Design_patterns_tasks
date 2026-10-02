public class VMInstance implements Prototype<VMInstance>{
    private String os;
    private String runtime;
    private String monitoringAgent;
    private String hostname;
    private String ipAddress;

    public VMInstance(String os,
                      String runtime,
                      String monitoringAgent,
                      String hostname,
                      String ipAddress) {
        this.os = os;
        this.runtime = runtime;
        this.monitoringAgent = monitoringAgent;
        this.hostname = hostname;
        this.ipAddress = ipAddress;
    }

    public VMInstance(VMInstance other){
        this.hostname = other.hostname;
        this.monitoringAgent = other.monitoringAgent;
        this.runtime = other.runtime;
        this.ipAddress = other.ipAddress;
        this.os = other.os;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getMonitoringAgent() {
        return monitoringAgent;
    }

    public void setMonitoringAgent(String monitoringAgent) {
        this.monitoringAgent = monitoringAgent;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public VMInstance clone(){
        return new VMInstance(this);
    }

}
