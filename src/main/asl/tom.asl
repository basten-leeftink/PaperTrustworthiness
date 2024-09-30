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
wrongplan(X,V):- phi(V,X,Y) && benevolence(V,Z) && initial(Plan) && phi(V,Plan,T) && Ben is (Y+Z) && T>Ben.
acceptable(X):- not wrongplan(X,_).



+?benevolent(Y,X): inplan(Z,X) && acceptable(Z) => #println("acceptable plan " + Z); #coms.inform(jane, benevolent(tom,X)).




