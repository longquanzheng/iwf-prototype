# iWorkflow  

See also in proposal https://github.com/uber/cadence/issues/4785

## Background

One main pain point of using/writing Cadence workflow is that it requires developers to write code to be replayable. This is against the existing pattern of human programming. 
Most people don't know their code must be replayable. On the other hand, software must be soft. We have to change code all the time for business changes and new requirements. 
These two factors together have made things extremely difficult for engineers to use Cadence. 

## Proposal

Instead of writing the Cadence workflow, we let users write workflow in code by defining a set of **[WorkflowState](https://github.com/longquanzheng/iwf/blob/main/src/com/iwf/WorkflowState.java)**. 
Each **WorkflowState** defines two things: **execute** and **decide** 

**execute** will send command for signals, timers, activity, etc 
When requested commands are finished, the **decide** is invoked to get the next states to jump into. 

As implementation, one Cadence workflow to execute the **WorkflowState**. 
This workflow will call **execute** and **decide**  using Cadence activities, so that those user workflow code can always be replayed safe. 


It preserves all most the full power of Cadence like 
* Schedule timer
* Execute activity
* Waiting for signal
* Providing query
* Reset workflow 
* Upsert Search Attributes
* Writing unit tests for workflow code(in a much easier way) 
* etc

And there is no need to worry about non-deterministic errors. 

## Limitation

* We may lose some readability of the workflow code. However, 
  * Workflow can be better visulized. A flow diagram can be generated using a tool. 
  * In real world use cases, the business logic could get more readable because we don't have to use versioning anymore. 
* We will lose some flexibility of mutating internal workflow states compared to native Cadence workflow code. 
* We will lose some strongly typing(use type casting instead) when writing workflow code

## Benefits

**With the same power of the native/original Cadence workflow...**

* No more [versioning](https://stackoverflow.com/questions/65007136/uber-cadence-workflow-versioning/65029001#65029001) in workflow code when making code change. This will make maintainence a lot easier and the production code looks much cleaner.
  * Every code change in workflow will take affect immediately for existing started workflow runs. 
  * No need to write any replay code anymore to protect the determinism
* It will be super easy to learn and understand. Everything is straightfoward and no trciky things at all
  * There is no restriction anymore for writing your workflow code. You can call RPC in workflow without using activity if you like. 
* It will be super easy to debug when things go wrong. The workflow history will preseve the input/output of each call of **execute** and **decide** 
* It will be super easy to monitor and operate. 
  * The monitoring is simplifed. There is no need to monitoring decision task timeout/failure etc.
  * There is no decision task concept anymore. The runtime environment is simplifed to request-response manner so debugging is extremely straightfoward. 
* It will be a lot easier to write unit tests. 
  * Only traditional framework(mockito/Junit) is needed.
  * No need to use extra imbedded SDK to write testing code. 
* Better memory/threading usage with lightweight threading implementation 
  * This is a big problem for Cadence Java SDK as the Workflow thread is JVM thread. With this approach, all languages will always be able to use lightweight threading -- because the actual workflow can be implemented in Golang Cadence SDK. 


## Example
See this [example](https://github.com/longquanzheng/iwf/blob/main/src/com/iwf/demo/subscription) as a translation
of the [Subscription workflow](https://cadenceworkflow.io/docs/concepts/workflows/#example)

Note that the SDK in this repo is just a prototype. There are lots of improvement can be done to make the API more
friendly. 
