# Factory Design Pattern – AI Client Integration

## Problem Statement

### Question: How can you design an extensible AI client system that supports multiple AI providers and different types of AI clients using the Factory Design Pattern?

Suppose you are developing an application that interacts with different AI providers, such as OpenAI and Anthropic. Initially, your application supports only chat functionality, but later, you decide to introduce vector-related functionality as well.

As the application grows, you want to switch between AI providers without modifying the business logic. You also want to avoid repeatedly writing provider-selection conditions whenever a new client type or provider is introduced.

Your task is to evolve the initial implementation into a flexible, maintainable design using interfaces and the Factory Design Pattern.

---

## Phase 1: Implement the Initial Chat Service

Initially, the application supports only chat functionality. You decide to use OpenAI as the AI provider.

### Requirements

1. Create an `AiChatClient` interface that defines a common `chat(String prompt)` method.
2. Implement the interface using an `OpenAiChatClient` class.
3. Create a `ChatService` class that uses the OpenAI chat client to process user prompts.
4. Demonstrate the application by sending a sample prompt and displaying the response.

### Problem to Consider

What changes would be required if you later wanted to replace OpenAI with Anthropic?

Would you need to modify the `ChatService` implementation to use a different concrete class?

---

## Phase 2: Introduce Multiple AI Providers

Now, the application needs to support Anthropic in addition to OpenAI.

Although both providers offer chat functionality, their internal implementation methods may differ.

For example:

- `OpenAiChatClient` uses a `completePrompt()` method internally.
- `AnthropicChatClient` uses a `getResponse()` method internally.
- Both classes expose the same `chat(String prompt)` method through the `AiChatClient` interface.

### Requirements

1. Create an `AnthropicChatClient` that implements `AiChatClient`.
2. Ensure both providers follow the same public interface.
3. Modify the application so that the selected provider can be changed without changing how the chat operation is invoked.

### Problem to Consider

How can you select the required provider without making `ChatService` directly dependent on concrete implementations?

How can you encapsulate the object creation logic so that the service only interacts with the `AiChatClient` interface?

---

## Phase 3: Introduce Vector Clients

The application now needs to support vector-related functionality in addition to chat functionality.

You must introduce a separate abstraction for vector operations.

### Requirements

1. Create an `AiVectorClient` interface defining the required vector-related operations.
2. Implement the interface for OpenAI and Anthropic.
3. Ensure that chat and vector functionality remain separate.
4. Allow the application to use both chat and vector clients for the selected AI provider.

### Problem to Consider

If chat and vector clients are created independently, how can you ensure that both clients belong to the same provider?

For example, when OpenAI is selected, the application should obtain both `OpenAiChatClient` and `OpenAiVectorClient`. Similarly, selecting Anthropic should provide `AnthropicChatClient` and `AnthropicVectorClient`.

---

## Phase 4: Implement Separate Factories for Client Types

Initially, consider creating separate factories for different client types.

For example:

- A chat client factory creates an `AiChatClient`.
- A vector client factory creates an `AiVectorClient`.

### Requirements

1. Create factory abstractions for creating chat and vector clients.
2. Implement the corresponding factories.
3. Use the factories to separate object creation from the service layer.

### Problem to Consider

As the number of AI providers and client types increases, where should the provider-selection logic reside?

If every factory or service contains its own `if-else` or `switch` statements for selecting providers, how much duplication will this introduce?

What happens when a new provider or additional provider-specific requirements are introduced?

---

## Phase 5: Refactor Using Provider-Specific Factories

To address the limitations of the previous approach, redesign the factory structure so that each provider has its own factory.

Instead of having independent factories responsible for creating different client types for different providers, introduce a common `AiClientFactory` abstraction.

### Requirements

1. Create an `AiClientFactory` abstraction that declares methods for creating:
   - An `AiChatClient`
   - An `AiVectorClient`
2. Implement an `OpenAiClientFactory` that creates only OpenAI clients.
3. Implement an `AnthropicClientFactory` that creates only Anthropic clients.
4. Create a `ClientFactoryProvider` that selects the appropriate provider-specific factory based on the provider name.
5. Ensure that provider-specific object creation is encapsulated within the corresponding factory.
6. Ensure that `ChatService` obtains both clients through the selected factory rather than directly instantiating concrete implementations.

### Problem to Consider

How can you avoid modifying `ChatService` whenever a new AI provider is introduced?

How can you ensure that adding a new provider does not require adding provider-specific object creation logic throughout the application?

Consider the Open/Closed Principle (OCP): software entities should be open for extension but closed for modification.

---

## Phase 6: Integrate the Factory with ChatService

Implement the final `ChatService` using the factory abstraction.

### Requirements

1. Accept the provider name through the constructor.
2. Use `ClientFactoryProvider` to obtain the appropriate factory.
3. Obtain the chat client and vector client from that factory.
4. Implement a `chat(String prompt)` method that delegates the prompt to the selected chat client.
5. Keep the service independent of provider-specific concrete classes.
6. Demonstrate the application by selecting different providers in the main program.

### Expected Behavior

When the provider is OpenAI:

- The factory provider returns an `OpenAiClientFactory`.
- The factory creates an `OpenAiChatClient` and an `OpenAiVectorClient`.
- `ChatService` uses the OpenAI chat client to process the prompt.

When the provider is Anthropic:

- The factory provider returns an `AnthropicClientFactory`.
- The factory creates an `AnthropicChatClient` and an `AnthropicVectorClient`.
- `ChatService` uses the Anthropic chat client to process the prompt.

The service should use the same public interface regardless of the selected provider.

---

## Final Project Structure

Implement the solution using the following structure:

```text
Factory/
└── src/
    ├── chatClients/
    │   ├── AiChatClient.java
    │   ├── AnthropicChatClient.java
    │   └── OpenAiChatClient.java
    │
    ├── factory/
    │   ├── simpleFactory/
    │   ├── AiClientFactory.java
    │   ├── AnthropicClientFactory.java
    │   ├── ClientFactoryProvider.java
    │   └── OpenAiClientFactory.java
    │
    ├── vectorClients/
    │   ├── AiVectorClient.java
    │   ├── AnthropicVectorClient.java
    │   └── OpenAiVectorClient.java
    │
    ├── ChatService.java
    ├── Client.java
    └── Main.java
```

## Expected Learning Outcomes

After completing this assignment, you should be able to:

- Understand how a design can evolve from a direct client dependency to a factory-based approach.
- Use interfaces to hide provider-specific implementation details.
- Separate object creation from business logic.
- Understand the limitations of distributing provider-selection conditions across multiple classes.
- Apply the Open/Closed Principle when designing extensible systems.
- Use provider-specific factories to create related objects belonging to the same provider.
- Understand the relationship between Simple Factory and Abstract Factory approaches.
- Add new providers with minimal changes to existing business logic.

## Conclusion

The objective is not merely to implement a Factory Design Pattern, but to understand why the design becomes necessary as application requirements evolve.

Start with a simple OpenAI chat client, introduce Anthropic, add vector functionality, identify the limitations of independent client factories, and refactor the implementation into provider-specific factories.

The final design should allow the application to switch providers while keeping `ChatService` focused on its primary responsibility: coordinating the chat operation rather than deciding how provider-specific objects are created.
