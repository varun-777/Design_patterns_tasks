
public class Client {
    // violates OCP
    // public static void creatCopy(VMInstance vmInstance){
    // VMInstance copy = null;
    // if(vmInstance instanceof VMInstance){
    // copy = new VMInstance(vmInstance);
    // }
    // else if(vmInstance instanceof GpuVMInstance){
    // copy = new GpuVMInstance((GpuVMInstance)vmInstance);
    // }
    //
    // }
    public static void fillRegistry(VMInstanceRegistry vmInstanceRegistry) {
        VMInstance ubuntuInstance = new VMInstance("Ubuntu 22.4", "Docker 1.2", "Datadog", null, null);
        vmInstanceRegistry.addVmInstance("backend-server-v1", ubuntuInstance);

        VMInstance gpuInstance = new GpuVMInstance(
                new GpuVMInstance("Ubuntu 22.4", "Docker 1.2", "Datadog", "Adity.com", "123.41.23.12", "Nvidia"));
        vmInstanceRegistry.addVmInstance("gpu-instance-v2", gpuInstance);

    }

    public static void main(String[] args) {
        // VMInstance instance1 = new VMInstance("Ubuntu 22.4", "Docker 1.2",
        // "Datadog","Adity.com","123.41.23.12");
        // if someone want to use the same machine then what will change hostname and ip
        // address and rest are same
        // now instide of creating from scratch copy it and change what ever you need

        // VMInstance copyInstance = new VMInstance(instance1);
        // VMInstance copyInstance = instance1.clone();

        // GpuVMInstance gpuVMInstance = new GpuVMInstance("Ubuntu 22.4", "Docker 1.2",
        // "Datadog","Adity.com","123.41.23.12","Nvidia");

        // VMInstance obj = gpuVMInstance.clone();

        VMInstanceRegistry vmInstanceRegistry = new VMInstanceRegistry();
        fillRegistry(vmInstanceRegistry);

        VMInstance varuninstance = vmInstanceRegistry.getVmInstance("backend-server-v1").clone();
        System.out.println(varuninstance);
        System.out.println(varuninstance.getOs());

    }
}
