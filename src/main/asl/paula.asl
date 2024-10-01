inplan(planN,research).
inplan(planN,report).
inplan(planI,audit).

initial(planI).

phi(honesty,planN,0.38).
phi(honesty,planI,0.38).
phi(promiseKeep,planN,0.28).
phi(promiseKeep,planI,0.50).

benevolence(honesty,0.30).
benevolence(promiseKeep,0.20).

// plan
goal(report).
subplan(report, research).
subplan(report, writing).

// facts about agent
// knowledge(agent, subplan, value)
knowledge(paula, research, 0.9).
knowledge(paula, writing, 0.7).

// skill(agent, subplan, value)
skill(paula, research, 0.8).
skill(paula, writing, 0.9).

// resource(agent, subplan, value)
resource(paula, research, 1).
resource(paula, writing, 1).

// thresholds  of trustor
// threshold_knowledge(trustor, subplan, threshold)
threshold_knowledge(agentT, research, 0.7).
threshold_knowledge(agentT, writing, 0.6).

// threshold_skill(trustor, subplan, threshold)
threshold_skill(agentT, research, 0.8).
threshold_skill(agentT, writing, 0.7).

// initialisers for the counter
number_of_subs(report, 0).
succeeded_subs(report, 0).


wrongplan(X,V):- phi(V,X,Y) && benevolence(V,Z) && initial(Plan) && phi(V,Plan,T) && Ben is (Y+Z) && T>Ben.

acceptable(X):- not wrongplan(X,_).

!competent(agentT, paula, report).

+?benevolent(Y,X): inplan(Z,X) && acceptable(Z) => #println("acceptable plan " + Z); #coms.inform(jane, benevolent(agentZ, X)).


// main function which uses the helperfunctions to determine if the agent
// is competent for the goal
+!competent(AgentT, paula, Goal) =>

    #println("Evaluating the competence of " + paula + " for the goal: " + Goal);
    #println("The goal " + Goal + " consists of the subplans: ");
    for (Subplan in subplan(Goal, Subplan)) {
        #println("- " + Subplan);
    };
    for (Subplan in subplan(Goal, Subplan)) {
        #println(" ");
        #println("Evaluating the subplan: " + Subplan);
        !comp_conditions(AgentT, paula, Subplan);
        !comp_subplans(Goal, Subplan);
    };
    !comp_total(paula, Goal).

// function which takes a subplan and returns if the agent is competent for
// the subplan, if not the function also returns the reason
+!comp_conditions(AgentT, paula, Subplan) :
    knowledge(paula, Subplan, K) &&
    skill(paula, Subplan, S) &&
    resource(paula, Subplan, R) &&
    threshold_knowledge(AgentT, Subplan, TK) &&
    threshold_skill(AgentT, Subplan, TS) =>
    if (K >= TK && S >= TS && R == 1) {
        #println("- " + paula + " is competent for the subplan: " + Subplan);
        +sub_comp(Subplan, 1);
    } else {
        #println("- " + paula + " is incompetent for the subplan: " + Subplan);
        +sub_comp(Subplan, 0);
    };
    if (K < TK) {
        #println("-> " + paula + " misses " + (TK - K) + " knowledge for the subplan: " + Subplan);
    };
    if (S < TS) {
        #println("-> " + paula + " misses " + (TS - S) + " skills for the subplan: " + Subplan);
    };
    if (R == 0) {
        #println("-> " + paula + " misses the resources for the subplan: " + Subplan);
    }.

// helper function to count the number of subplans
// and the number of succeeded subplans
+!comp_subplans(Goal, Subplan) :
    sub_comp(Subplan, SubComp) &&
    number_of_subs(Goal, NS) &&
    succeeded_subs(Goal, SS) &&
    NewNS is (NS + 1) &&
    NewSS is (SS + SubComp) =>
    -number_of_subs(Goal, NS);
    +number_of_subs(Goal, NewNS);
    -succeeded_subs(Goal, SS);
    +succeeded_subs(Goal, NewSS).

// helper function which returns if the agent is competent for the goal
+!comp_total(paula, Goal) :
    number_of_subs(Goal, NS) &&
    succeeded_subs(Goal, SS) =>
    #println(" ");
    if (NS == SS) {
        #println(paula + " is competent for the goal: " + Goal);
    } else {
        #println(paula + " is incompetent for the goal: " + Goal);
    }.



