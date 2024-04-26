# openai-client-java

[![](https://jitpack.io/v/com.salvadormontiel/openai-client-java.svg)](https://jitpack.io/#com.salvadormontiel/openai-client-java)

OpenAI client for Java, replicates the official Python client.

> This library does not support legacy and/or deprecated APIs by OpenAI

## Download

#### Step 1. Add the JitPack repository to your build file

Add it in your root `build.gradle` at the end of repositories:

``` groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

#### Step 2. Add the dependency

``` groovy
dependencies {
  compile 'com.salvadormontiel:openai-client-java:0.0.6'
}
```

## How to use it?

### Chat completion

``` java
var client = new OpenAI(System.getenv("OPENAI_API_KEY"));

var messages = new ArrayList<ChatMessage>();
messages.add(
        new ChatMessage.Builder()
                .asSystemRole()
                .setContent("You are a helpful assistant.")
                .build()
);
messages.add(
        new ChatMessage.Builder()
                .asUserRole()
                .setContent("count to 10, each number in a new line")
                .build()
);

var input = new ChatCompletionInput.Builder()
        .setModel("gpt-3.5-turbo")
        .setMessages(messages)
        .build();

var completion = client.chat().completions().create(input);
System.out.println(completion.choices.get(0).message.content);
```

### Streaming chat completion

``` java
var client = new OpenAI(System.getenv("OPENAI_API_KEY"));

var messages = new ArrayList<ChatMessage>();
messages.add(
        new ChatMessage.Builder()
                .asSystemRole()
                .setContent("You are a helpful assistant.")
                .build()
);
messages.add(
        new ChatMessage.Builder()
                .asUserRole()
                .setContent("count to 10, each number in a new line")
                .build()
);

var input = new ChatCompletionInput.Builder()
        .setModel("gpt-3.5-turbo")
        .setMessages(messages)
        .setStream(true)
        .build();

var completion = client.chat().completions().createAsStream(input);

completion.subscribe(new Flow.Subscriber<>() {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(ChatCompletionChunk chunk) {
        String content = chunk.choices.get(0).delta.content;
        if (content != null) System.out.print(content);

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {}
});
```

## Supported APIs

- Audio
  - [x] Create speech
  - [x] Create transcription
  - [x] Create translation
  - [x] Function calling
- Chat
  - [x] Create chat completion
  - [x] Stream chat completion
- Embeddings
  - [x] Create embeddings
- Fine-tuning
  - [ ] Create fine-tuning job
  - [ ] List fine-tuning jobs
  - [ ] List fine-tuning events
  - [ ] List fine-tuning checkpoints
  - [ ] Retrieve fine-tuning job
  - [ ] Cancel fine-tuning
- Batch
  - [ ] Create batch
  - [ ] Retrieve batch
  - [ ] Cancel batch
  - [ ] List batch
- Files
  - [ ] Upload file
  - [ ] List files
  - [ ] Retrieve file
  - [ ] Delete file
- Images
  - [x] Create image
  - [x] Create image edit
  - [x] Create image variation
- Models
  - [x] List models
  - [x] Retrieve model
  - [x] Delete a fine-tuned model
- Moderations
  - [ ] Create moderation

## Beta APIs

- Assistants
  - [ ] Create assistant
  - [ ] List assistants
  - [ ] Retrieve assistant
  - [ ] Modify assistant
  - [ ] Delete assistant
- Threads
  - [ ] Create thread
  - [ ] Retrieve thread
  - [ ] Modify thread
  - [ ] Delete thread
- Messages
  - [ ] Create message
  - [ ] List messages
  - [ ] Retrieve message
  - [ ] Modify message
- Runs
  - [ ] Create run
  - [ ] Create thread and run
  - [ ] List runs
  - [ ] Retrieve run
  - [ ] Modify run
  - [ ] Submit tool outputs to run
  - [ ] Cancel a run
- Run Steps
  - [ ] List run steps
  - [ ] Retrieve run step
- Vector Stores
  - [ ] Create vector store
  - [ ] List vector stores
  - [ ] Retrieve vector store
  - [ ] Modify vector store
  - [ ] Delete vector store
- Vector Store Files
  - [ ] Create vector store file
  - [ ] List vector store files
  - [ ] Delete vector store file
- Vector Store File Batches
  - [ ] Create vector store file batch
  - [ ] Retrieve vector store file batch
  - [ ] Cancel vector store file batch
  - [ ] List vector store files in a batch

## Contributing

Do you want to contribute or give me a hand a hand with the documentation? I will appreciate that!
