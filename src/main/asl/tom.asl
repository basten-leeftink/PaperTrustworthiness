inplan(planN,research).
inplan(planN,report).
inplan(planN,researchOld).
inplan(planN,reportOld).
inplan(planI,researchOld).
inplan(planI,reportOld).

initial(planI).

phi(honesty,planN,0.68).
phi(promiseKeep,planN,0.42).
phi(honesty,planI,0.70).
phi(promiseKeep,planI,0.50).

benevolence(honesty,0.2).
benevolence(promiseKeep,0.3).

// plan
goal(report).
subplan(report, research).
subplan(report, writing).

// facts about agent
// knowledge(agent, subplan, value)
knowledge(tom, research, 0.6).
knowledge(tom, writing, 0.6).

// skill(agent, subplan, value)
skill(tom, research, 0.8).
skill(tom, writing, 0.5).

// resource(agent, subplan, value)
resource(tom, research, 0).
resource(tom, writing, 1).

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

!competent(agentT, tom, report).

+?benevolent(Y,X): inplan(Z,X) && acceptable(Z) => #println("acceptable plan " + Z); #coms.inform(jane, benevolent(tom,X)).

// main function which uses the helperfunctions to determine if the agent
// is competent for the goal
+!competent(AgentT, tom, Goal) =>

    #println("Evaluating the competence of " + tom + " for the goal: " + Goal);
    #println("The goal " + Goal + " consists of the subplans: ");
    for (Subplan in subplan(Goal, Subplan)) {
        #println("- " + Subplan);
    };
    for (Subplan in subplan(Goal, Subplan)) {
        #println(" ");
        #println("Evaluating the subplan: " + Subplan);
        !comp_conditions(AgentT, tom, Subplan);
        !comp_subplans(Goal, Subplan);
    };
    !comp_total(tom, Goal).

// function which takes a subplan and returns if the agent is competent for
// the subplan, if not the function also returns the reason
+!comp_conditions(AgentT, tom, Subplan) :
    knowledge(tom, Subplan, K) &&
    skill(tom, Subplan, S) &&
    resource(tom, Subplan, R) &&
    threshold_knowledge(AgentT, Subplan, TK) &&
    threshold_skill(AgentT, Subplan, TS) =>
    if (K >= TK && S >= TS && R == 1) {
        #println("- " + tom + " is competent for the subplan: " + Subplan);
        +sub_comp(Subplan, 1);
    } else {
        #println("- " + tom + " is incompetent for the subplan: " + Subplan);
        +sub_comp(Subplan, 0);
    };
    if (K < TK) {
        #println("-> " + tom + " misses " + (TK - K) + " knowledge for the subplan: " + Subplan);
    };
    if (S < TS) {
        #println("-> " + tom + " misses " + (TS - S) + " skills for the subplan: " + Subplan);
    };
    if (R == 0) {
        #println("-> " + tom + " misses the resources for the subplan: " + Subplan);
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
+!comp_total(tom, Goal) :
    number_of_subs(Goal, NS) &&
    succeeded_subs(Goal, SS) =>
    #println(" ");
    if (NS == SS) {
        #println(tom + " is competent for the goal: " + Goal);
    } else {
        #println(tom + " is incompetent for the goal: " + Goal);
    }.
