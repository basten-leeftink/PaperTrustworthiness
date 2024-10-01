// plan
goal(report).
subplan(report, research).
subplan(report, writing).

// Initialize agents
agent(paula).
agent(tom).


// Belief-base Paula 
principle(paula, honisty, 0.72).
principle(paula, promiseKeeping, 0.44).

intention(paula, honisty, 0.38).
intention(paula, promiseKeeping, 0.28).


// Belief-base Tom
principle(tom, honisty, 0.72).
principle(tom, promiseKeeping, 0.44).

intention(tom, honisty, 0.68).
intention(tom, promiseKeeping, 0.42).



// Initializing variables 
sum(0.0).
weightSum(0.0).
dMax(0).
threshold(0.8).

// Initializing benevolence program
!init.

// Initializing integrity program 
!getnames().

+!init: goal(X) =>

    #println(Self + " Who is sufficiently benevolent to make a report?  ");
    TOMAgentName = "tom";
    #println(Self + " checking whether " + TOMAgentName + " is sufficiently benevolent");
    #coms.ask(TOMAgentName, benevolent(Y,X));
    #println("question asked");
    TOM2AgentName = "paula";
    #println(Self + " checking whether " + TOM2AgentName + " is sufficiently benevolent");
    #coms.ask(TOM2AgentName, benevolent(Y,X));
    #println("question asked").

    +benevolent(Y,X) =>
    #println("agent " + Y + " is benevolent to perform " + X).




// Looping through all the agents
+!getnames() =>
    for (Name in agent(Name)) {
        !initt(Name);
    }.

// Looping through principles 
+!initt(Agent) =>
    for (X in principle(Agent, X, P)) {
        !distanceSum(Agent,X);
        !distanceMax(Agent,1);
    };

    !normalizedDistance(Agent).

// Calulate sum of distance 
+!distanceSum(Agent, X) :
    principle(Agent, X, P) &&
    intention(Agent, X, I) &&
    sum(CurrentSum) &&
    D is (((P-I))**2) &&
    NewSum is CurrentSum + D =>

    // Updating sum in belief-base
    -sum(CurrentSum);
    +sum(NewSum);

// Calculate max distance 
+!distanceMax(Agent, M):
    dMax(CurrentDMax) &&
    NewCurrentDMax is CurrentDMax + M =>

    // Updating sum in belief-base
    -dMax(CurrentDMax);
    +dMax(NewCurrentDMax);

// Calculate normalized distance 
+!normalizedDistance(Agent) :
    sum(D) && 
    dMax(M) && 
    threshold(T)=> 

    // Taking square root and invert distance 
    Alpha = (1 - (#nl.uva.sqrt.RootCalculator.calculateRoot(D,2) / M));
    if (Alpha > T) {
    #println("The perceived integrity of " + Agent + " is: " + Alpha + ". And is thus integer.");
    } else {
    #println("The perceived integrity of " + Agent + " is: " + Alpha + ". And is thus not integer.");
    };

    // Resetting belief base
    -sum(X);
    -dMax(M);
    +dMax(0);
    +sum(0.0).


