# iWorkflow 

See also in proposal https://github.com/uber/cadence/issues/4785

## Background

One main pain point of using/writing Cadence workflow is that it requires developers to write code to be replayable. This is against the existing pattern of human programming. 
Most people don't know their code must be replayable. On the other hand, software must be soft. We have to change code all the time for business changes and new requirements. 
These two factors together have made things extremely difficult for engineers to use Cadence. 

## Proposal

Instead of writing the Cadence workflow, we let users write workflow in code by defining a set of **WorkflowState**. 
Each **WorkflowState** defines two things: **prepare** and **decide** 
**prepare** will request for signals, timers, activity, etc 
When all/any precondition is met, the **decide** is invoked to get the next states to jump into. 

As implementation, one Cadence workflow to execute the **WorkflowState**. 
This workflow will call **prepare** and **decide**  using Cadence activities, so that those user workflow code can always be replayed safe. 


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

* We may lose some readability of the workflow code. However, we can write higher lever abstraction code to make it cleaner.
* We will lose some flexibility of mutating internal workflow states compared to native Cadence workflow code. 
