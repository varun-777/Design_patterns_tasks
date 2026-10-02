```markdown
# VM Instance Management System
## Prototype Design Pattern + Registry

## Problem Statement

You are building a system that manages Virtual Machine (VM) instances.

Creating a VM from scratch every time can be repetitive because a VM contains several configuration details such as:

- Operating System
- Runtime
- Monitoring Agent
- Hostname
- IP Address

For example, a company may already have a configured Ubuntu VM with Docker and Datadog installed.

If another VM with almost the same configuration is required, we should not create the entire VM configuration from scratch.

Instead, we can create a copy of an existing VM and modify only the properties that are different.

To implement this, use the **Prototype Design Pattern**.

To manage multiple predefined VM prototypes, use a **Registry**.

---

# Requirements

## 1. Prototype Interface

Create a generic `Prototype<T>` interface.

It should define a `clone()` method that returns a copy of the current object.

Example:

```java
public interface Prototype<T> {
    T clone();
}
```

---

# 2. VMInstance

Create a `VMInstance` class that implements:

```java
Prototype<VMInstance>
```

The VM should contain the following fields:

```text
os
runtime
monitoringAgent
hostname
ipAddress
```

For example:

```text
OS              : Ubuntu 22.4
Runtime         : Docker 1.2
Monitoring      : Datadog
Hostname        : Adity.com
IP Address      : 123.41.23.12
```

The class should provide:

- Parameterized constructor
- Copy constructor
- Getters
- Setters
- `clone()` method

The `clone()` method should create a new `VMInstance` using the existing instance.

Example:

```java
@Override
public VMInstance clone() {
    return new VMInstance(this);
}
```

---

# 3. GPU VM Instance

Create a specialized VM called:

```text
GpuVMInstance
```

It should extend:

```java
VMInstance
```

In addition to the VM fields, it should contain:

```text
gpuType
```

For example:

```text
GPU Type : Nvidia
```

It should provide:

- Parameterized constructor
- Copy constructor
- Getter for `gpuType`
- `clone()` method

The copy constructor should copy both the inherited VM properties and the GPU-specific property.

Example:

```java
public GpuVMInstance(GpuVMInstance other) {
    this(
        other.getOs(),
        other.getRuntime(),
        other.getMonitoringAgent(),
        other.getHostname(),
        other.getIpAddress(),
        other.getGpuType()
    );
}
```

---

# 4. VM Instance Registry

Create a:

```text
VMInstanceRegistry
```

The registry should maintain predefined VM prototypes using a `Map`.

The structure should be:

```text
Key                  Prototype

backend-server-v1 → VMInstance
gpu-instance-v2   → GpuVMInstance
```

The registry should provide:

```java
addVmInstance(String key, VMInstance vmInstance)
```

to register a prototype.

It should also provide:

```java
getVmInstance(String key)
```

to retrieve a registered prototype.

The registry should use a `HashMap`.

---

# 5. Client

Create a `Client` class that prepares the VM prototypes.

Create a normal VM prototype:

```text
Key: backend-server-v1

OS: Ubuntu 22.4
Runtime: Docker 1.2
Monitoring Agent: Datadog
```

Register it in the registry.

Then create a GPU VM prototype:

```text
Key: gpu-instance-v2

OS: Ubuntu 22.4
Runtime: Docker 1.2
Monitoring Agent: Datadog
Hostname: Adity.com
IP: 123.41.23.12
GPU: Nvidia
```

Register it in the registry.

---

# 6. Creating a New VM

The client should not create another VM from scratch when a similar VM is required.

Instead:

1. Retrieve a prototype from the registry.
2. Clone the prototype.
3. Modify the properties that need to be different.

Example:

```java
VMInstance newInstance =
    vmInstanceRegistry
        .getVmInstance("backend-server-v1")
        .clone();
```

The important idea is:

```text
Registry
    ↓
Find existing prototype
    ↓
Clone prototype
    ↓
Create new VM
    ↓
Modify required properties
```

---

# Example Scenario

Suppose the company already has this VM:

```text
OS              : Ubuntu 22.4
Runtime         : Docker 1.2
Monitoring      : Datadog
Hostname        : Adity.com
IP Address      : 123.41.23.12
```

Now the company needs another VM with the same configuration but a different:

```text
Hostname
IP Address
```

Instead of doing:

```java
new VMInstance(
    "Ubuntu 22.4",
    "Docker 1.2",
    "Datadog",
    "new-host.com",
    "192.168.1.10"
);
```

we can clone the existing VM:

```java
VMInstance newInstance =
    vmInstanceRegistry
        .getVmInstance("backend-server-v1")
        .clone();
```

Then change only the required properties:

```java
newInstance.setHostname("new-host.com");
newInstance.setIpAddress("192.168.1.10");
```

---

# Why Prototype?

Without Prototype, the client would have to know all the configuration details required to create every VM.

For example:

```text
OS
Runtime
Monitoring Agent
Hostname
IP Address
GPU Type
...
```

With Prototype:

```text
Existing VM
     ↓
   clone()
     ↓
New VM
```

The existing configuration is reused.

---

# Why Registry?

The Registry provides a central place to store predefined prototypes.

For example:

```text
VMInstanceRegistry

"backend-server-v1"
        ↓
   VM Prototype

"gpu-instance-v2"
        ↓
 GPU VM Prototype
```

The client can retrieve a prototype using its key.

---

# Design Pattern Responsibilities

## Prototype

Responsible for:

> Creating a new object by copying an existing object.

Main method:

```java
clone()
```

---

## Registry

Responsible for:

> Storing and retrieving predefined prototype objects.

Main methods:

```java
addVmInstance()
getVmInstance()
```

---

## Client

Responsible for:

> Using the registry to retrieve prototypes and cloning them when a new VM is required.

---

# Important Design Goal

The client should avoid creating similar VM objects from scratch.

Instead of:

```text
Create VM
Create VM
Create VM
Create VM
```

the system should work like:

```text
              VM Registry
                   |
       ┌───────────┴───────────┐
       ↓                       ↓
backend-server-v1        gpu-instance-v2
       ↓                       ↓
   VM Prototype          GPU VM Prototype
       ↓
     clone()
       ↓
   New VM Instance
```

---

# Expected Outcome

The final system should demonstrate:

1. Prototype interface
2. VM object cloning
3. Copy constructors
4. Inheritance using `GpuVMInstance`
5. Registry using `HashMap`
6. Registering predefined prototypes
7. Retrieving prototypes by key
8. Creating new VM instances using `clone()`
9. Modifying cloned instances without modifying the original prototype


