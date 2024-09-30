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

wrongplan(X,V):- phi(V,X,Y) && benevolence(V,Z) && initial(Plan) && phi(V,Plan,T) && Ben is (Y+Z) && T>Ben.

acceptable(X):- not wrongplan(X,_).



+?benevolent(Y,X): inplan(Z,X) && acceptable(Z) => #println("acceptable plan " + Z); #coms.inform(jane, benevolent(agentZ, X)).




