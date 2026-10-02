import java.util.HashMap;
import java.util.Map;

public class VMInstanceRegistry {
    private Map<String, VMInstance> vmInstanceRegistry;

    public VMInstanceRegistry() {
        vmInstanceRegistry = new HashMap<>();
    }

    public void addVmInstance(String key, VMInstance vmInstance) {
        vmInstanceRegistry.put(key, vmInstance);
    }

    public VMInstance getVmInstance(String key) {
        return vmInstanceRegistry.get(key);
    }
}
