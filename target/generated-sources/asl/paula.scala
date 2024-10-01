package asl
 import _root_.scala.collection.mutable.HashMap

 import _root_.akka.actor.typed.{ActorRef, Behavior, SupervisorStrategy}
 import _root_.akka.actor.typed.scaladsl.{ActorContext, Behaviors, Routers}
 import java.util.logging.Logger
 import _root_.scala.util.Failure
 import _root_.scala.util.Success
 import _root_.akka.util.Timeout
 import _root_.scala.concurrent.duration._
 import _root_.akka.actor.typed.scaladsl.AskPattern._
 import _root_.scala.language.implicitConversions
 import _root_.scala.concurrent.{Await, Future}
 import _root_.scala.jdk.CollectionConverters._
 import std.converters._

 import scala.util.Random
 import bb._
 import infrastructure._
 import bb.expstyla.exp._
 import std.{AgentCommunicationLayer, DefaultCommunications}

 class paula  (coms: AgentCommunicationLayer = new  DefaultCommunications,
                                     beliefBaseFactory: IBeliefBaseFactory = new StylaBeliefBaseFactory)
                      extends IntentionalAgentFactory {


 object Intention {

       def apply(p_executionContext: ExecutionContext): Behavior[ISubGoalMessage] = Behaviors.setup { context =>

         Behaviors.receive {
         (context, message) =>

          implicit val executionContext = p_executionContext.copy(intention = context, src = message.source)

         message match {
            case SubGoalMessage(_,_,r) =>
               message.goal match {

                   case paula.this.adopt_test_benevolent_2 =>
                     paula.this.adopt_test_benevolent_2.execute(message.params.asInstanceOf[Parameters])

                   case paula.this.adopt_achievement_competent_3 =>
                     paula.this.adopt_achievement_competent_3.execute(message.params.asInstanceOf[Parameters])

                   case paula.this.adopt_achievement_comp_conditions_3 =>
                     paula.this.adopt_achievement_comp_conditions_3.execute(message.params.asInstanceOf[Parameters])

                   case paula.this.adopt_achievement_comp_subplans_2 =>
                     paula.this.adopt_achievement_comp_subplans_2.execute(message.params.asInstanceOf[Parameters])

                   case paula.this.adopt_achievement_comp_total_2 =>
                     paula.this.adopt_achievement_comp_total_2.execute(message.params.asInstanceOf[Parameters])


           case _ =>
             context.log.error("This actor can not handle goal of type {}", message.goal)
         }
           r match {
                 case a : AkkaMessageSource => 
                   a.src ! IntentionDoneMessage(AkkaMessageSource(executionContext.agent.self))
                 case DummyMessageSource(_) => 
                   context.log.error("Intention Done!")
               }

               Behaviors.same
             case InitEndMessage(r) =>
               Behaviors.stopped
       }
      }
     }
     }

 override def agentBuilder: Agent = new Agent()
 class Agent extends IAgent {

         override def agent_type: String = "paula"

         var vars = VarMap()

         def initGoals()(implicit executionContext: ExecutionContext) = List[StructTerm](
                     StructTerm("competent",Seq[GenericTerm]( StructTerm("agentT",Seq[GenericTerm]()) , StructTerm("paula",Seq[GenericTerm]()) , StructTerm("report",Seq[GenericTerm]())  ))


         )

         def initBeliefs()(implicit executionContext: ExecutionContext) = List[StructTerm](
                     StructTerm("inplan",Seq[GenericTerm](StructTerm("planN",Seq[GenericTerm]()),StructTerm("research",Seq[GenericTerm]())))
           ,
            StructTerm("inplan",Seq[GenericTerm](StructTerm("planN",Seq[GenericTerm]()),StructTerm("report",Seq[GenericTerm]())))
           ,
            StructTerm("inplan",Seq[GenericTerm](StructTerm("planI",Seq[GenericTerm]()),StructTerm("audit",Seq[GenericTerm]())))
           ,
            StructTerm("initial",Seq[GenericTerm](StructTerm("planI",Seq[GenericTerm]())))
           ,
            StructTerm("phi",Seq[GenericTerm](StructTerm("honesty",Seq[GenericTerm]()),StructTerm("planN",Seq[GenericTerm]()),DoubleTerm(0.38)))
           ,
            StructTerm("phi",Seq[GenericTerm](StructTerm("honesty",Seq[GenericTerm]()),StructTerm("planI",Seq[GenericTerm]()),DoubleTerm(0.38)))
           ,
            StructTerm("phi",Seq[GenericTerm](StructTerm("promiseKeep",Seq[GenericTerm]()),StructTerm("planN",Seq[GenericTerm]()),DoubleTerm(0.28)))
           ,
            StructTerm("phi",Seq[GenericTerm](StructTerm("promiseKeep",Seq[GenericTerm]()),StructTerm("planI",Seq[GenericTerm]()),DoubleTerm(0.5)))
           ,
            StructTerm("benevolence",Seq[GenericTerm](StructTerm("honesty",Seq[GenericTerm]()),DoubleTerm(0.3)))
           ,
            StructTerm("benevolence",Seq[GenericTerm](StructTerm("promiseKeep",Seq[GenericTerm]()),DoubleTerm(0.2)))
           ,
            StructTerm("goal",Seq[GenericTerm](StructTerm("report",Seq[GenericTerm]())))
           ,
            StructTerm("subplan",Seq[GenericTerm](StructTerm("report",Seq[GenericTerm]()),StructTerm("research",Seq[GenericTerm]())))
           ,
            StructTerm("subplan",Seq[GenericTerm](StructTerm("report",Seq[GenericTerm]()),StructTerm("writing",Seq[GenericTerm]())))
           ,
            StructTerm("knowledge",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),StructTerm("research",Seq[GenericTerm]()),DoubleTerm(0.9)))
           ,
            StructTerm("knowledge",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),StructTerm("writing",Seq[GenericTerm]()),DoubleTerm(0.7)))
           ,
            StructTerm("skill",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),StructTerm("research",Seq[GenericTerm]()),DoubleTerm(0.8)))
           ,
            StructTerm("skill",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),StructTerm("writing",Seq[GenericTerm]()),DoubleTerm(0.9)))
           ,
            StructTerm("resource",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),StructTerm("research",Seq[GenericTerm]()),IntTerm(1)))
           ,
            StructTerm("resource",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),StructTerm("writing",Seq[GenericTerm]()),IntTerm(1)))
           ,
            StructTerm("threshold_knowledge",Seq[GenericTerm](StructTerm("agentT",Seq[GenericTerm]()),StructTerm("research",Seq[GenericTerm]()),DoubleTerm(0.7)))
           ,
            StructTerm("threshold_knowledge",Seq[GenericTerm](StructTerm("agentT",Seq[GenericTerm]()),StructTerm("writing",Seq[GenericTerm]()),DoubleTerm(0.6)))
           ,
            StructTerm("threshold_skill",Seq[GenericTerm](StructTerm("agentT",Seq[GenericTerm]()),StructTerm("research",Seq[GenericTerm]()),DoubleTerm(0.8)))
           ,
            StructTerm("threshold_skill",Seq[GenericTerm](StructTerm("agentT",Seq[GenericTerm]()),StructTerm("writing",Seq[GenericTerm]()),DoubleTerm(0.7)))
           ,
            StructTerm("number_of_subs",Seq[GenericTerm](StructTerm("report",Seq[GenericTerm]()),IntTerm(0)))
           ,
            StructTerm("succeeded_subs",Seq[GenericTerm](StructTerm("report",Seq[GenericTerm]()),IntTerm(0)))
           ,
            StructTerm(":-",Seq[GenericTerm](StructTerm("wrongplan",Seq[GenericTerm](vars("X"),vars("V"))),StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm("phi",Seq[GenericTerm](vars("V"),vars("X"),vars("Y"))),StructTerm("benevolence",Seq[GenericTerm](vars("V"),vars("Z"))))),StructTerm("initial",Seq[GenericTerm](vars("Plan"))))),StructTerm("phi",Seq[GenericTerm](vars("V"),vars("Plan"),vars("T"))))),StructTerm("is",Seq[GenericTerm](vars("Ben"),StructTerm("+",Seq[GenericTerm](vars("Y"),vars("Z"))))))),StructTerm(">",Seq[GenericTerm](vars("T"),vars("Ben")))))))
           ,
            StructTerm(":-",Seq[GenericTerm](StructTerm("acceptable",Seq[GenericTerm](vars("X"))),StructTerm("not",Seq[GenericTerm](StructTerm("wrongplan",Seq[GenericTerm](vars("X"),vars("_")))))))


         )

         def planApplicabilities()(implicit executionContext: ExecutionContext) = List[StructTerm](

                 )



      def apply(name: String, yellowPages: IYellowPages, MAS: ActorRef[IMessage], parent: IMessageSource): Behavior[IMessage] = {
           Behaviors.setup { context =>
             val yp: IYellowPages = yellowPages
             val bb: IBeliefBase[GenericTerm] = beliefBaseFactory()
             implicit val executionContext: ExecutionContext = ExecutionContext(
                            name = name,
                            agentType = agent_type,
                            agent = context,
                            yellowPages = yp,
                            beliefBase = bb,
                            logger = context.log,
                            goalParser = GoalParser,
                            parent = parent
                          )
             bb.assert(initBeliefs)
             bb.assert(planApplicabilities)

         val initiator = context.spawn(Intention(executionContext), "initiator")

         MAS ! ReadyMessage(context.self)
         Behaviors.receive {
           (context, message) =>
             message match {
               case StartMessage() =>


                 implicit val timeout: Timeout = 99999.seconds
                 implicit val ec = context.executionContext
                 implicit val scheduler = context.system.scheduler


                 //              initGoals.foreach( tuple => initiator ! SubGoalMessage(tuple._1,tuple._2,context.self))
                 initGoals.foreach(struct => {


                   val result: Future[IMessage] = initiator.ask[IMessage](ref => {
                     val subGoal = GoalParser.create_goal_message(struct, AkkaMessageSource(ref))
                     if (subGoal.isDefined)
                       subGoal.get
                     else
                       throw new RuntimeException(s"No plan for initial goal $struct")
                     }
                   )


                   //result.onComplete {
                   //  case Success(IntentionDoneMessage(r)) => IntentionDoneMessage(r)
                   //  case Failure(_) => IntentionErrorMessage(src = null)
                   //}

                   //Await.result(result, timeout.duration)

                   val res = Await.result(result, timeout.duration)

                   if(!res.isInstanceOf[IntentionDoneMessage]) {
                     throw new RuntimeException(s"Issue with initial goal $struct")
                     context.system.terminate()
                   }

                   //                context.ask[ISubGoalMessage, IMessage](initiator, ref => SubGoalMessage(tuple._1, tuple._2,name, ref)) {
                   //                  case Success(IntentionDoneMessage(_, _)) => IntentionDoneMessage()
                   //                  case Failure(_) => IntentionErrorMessage()
                   //                }
                 }
                 )

                 initiator ! InitEndMessage(context.self)
                 normal_behavior(MAS)

               //            case InitEndMessage(_) =>
               //              context.log.debug(f"$name: I have started, switching behavior")
               //              normal_behavior()
             }

         }
       }
     }

     def normal_behavior(MAS: ActorRef[IMessage])(implicit executionContext: ExecutionContext): Behavior[IMessage] = {
       Behaviors.setup { context =>

         val pool = Routers.pool(poolSize = 8)(
           Behaviors.supervise(Intention(executionContext)).onFailure[Exception](SupervisorStrategy.restart))

         val router = context.spawn(pool, "intention-pool")
         //MAS ! ReadyMessage(context.self)
         Behaviors.receive {
           (context, message) =>
             message match {
               case IntentionDoneMessage(s) =>
                 context.log.debug(f"${executionContext.name}: an intention was done by $s")
               case IntentionErrorMessage(c,s) =>
                 context.log.debug(f"${executionContext.name}: an intention was done by $s: $c")
               case SubGoalMessage(_, _, _) =>
                 router ! message.asInstanceOf[SubGoalMessage]
               case GoalMessage(m, ref) =>
                 m match {
                   case t: StructTerm =>
                     val subGoal = GoalParser.create_goal_message(t, ref)

                     if (subGoal.isDefined)
                       context.self ! subGoal.get
                     else
                       ref.asInstanceOf[AkkaMessageSource].src ! IntentionErrorMessage(NoPlanMessage(),AkkaMessageSource(executionContext.agent.self))


                 }

                case AskMessage(m, ref) =>
                                m match {
                                  case t: StructTerm =>
                                    val subGoal = GoalParser.create_test_message(t, ref)

                                    if (subGoal.isDefined)
                                      context.self ! subGoal.get
                                    else
                                      ref.asInstanceOf[AkkaMessageSource].src ! IntentionErrorMessage(NoPlanMessage(),AkkaMessageSource(executionContext.agent.self))
                                }
             case BeliefMessage(m, ref) =>
                  m match {
                    case t: StructTerm =>
                    if(executionContext.beliefBase.assertOne(t)) {
                      val subGoal = GoalParser.create_belief_message(t, ref)

                      if (subGoal.isDefined)
                        context.self ! subGoal.get
                    }
                  }

              case UnBeliefMessage(m, ref) =>
                   m match {
                     case t: StructTerm =>
                     if(executionContext.beliefBase.retractOne(t)) {
                       val subGoal = GoalParser.create_unbelief_message(t, ref)

                       if (subGoal.isDefined)
                         context.self ! subGoal.get
                     }
                   }
             }
             Behaviors.same
         }
       }
     }
   }



   object GoalParser extends IAgentGoalParser {
        override def create_goal_message(t: StructTerm, ref: IMessageSource) (implicit executionContext: ExecutionContext): Option[SubGoalMessage] = {
                   
                                   if(t.matchOnlyFunctorAndArity("competent",3)) {
                                     val args: Parameters = Parameters(t.terms.toList)
                                     Option(SubGoalMessage(adopt_achievement_competent_3, args, ref))
                                   } else  
                                   if(t.matchOnlyFunctorAndArity("comp_conditions",3)) {
                                     val args: Parameters = Parameters(t.terms.toList)
                                     Option(SubGoalMessage(adopt_achievement_comp_conditions_3, args, ref))
                                   } else  
                                   if(t.matchOnlyFunctorAndArity("comp_subplans",2)) {
                                     val args: Parameters = Parameters(t.terms.toList)
                                     Option(SubGoalMessage(adopt_achievement_comp_subplans_2, args, ref))
                                   } else  
                                   if(t.matchOnlyFunctorAndArity("comp_total",2)) {
                                     val args: Parameters = Parameters(t.terms.toList)
                                     Option(SubGoalMessage(adopt_achievement_comp_total_2, args, ref))
                                   } else   {
                    Option.empty[SubGoalMessage]
                    }

                }

        override def create_belief_message(t: StructTerm, ref: IMessageSource) (implicit executionContext: ExecutionContext): Option[SubGoalMessage] = {
                        {
                    Option.empty[SubGoalMessage]
                    }

                }

         override def create_test_message(t: StructTerm, ref: IMessageSource) (implicit executionContext: ExecutionContext): Option[SubGoalMessage] = {
                                                   if(t.matchOnlyFunctorAndArity("benevolent",2)) {
                                                     val args: Parameters = Parameters(t.terms.toList)
                                                     Option(SubGoalMessage(adopt_test_benevolent_2, args, ref))
                                                   } else       {
                            Option.empty[SubGoalMessage]
                            }
                        }
          override def create_unbelief_message(t: StructTerm, ref: IMessageSource) (implicit executionContext: ExecutionContext): Option[SubGoalMessage] = {
                                         {
                                     Option.empty[SubGoalMessage]
                                     }
                                 }



        }


      object adopt_test_benevolent_2 extends IGoal {

        def execute(params: Parameters) (implicit executionContext: ExecutionContext) : Unit = {
         var vars = VarMap()

         vars("Self").bind_to(StringTerm(executionContext.name))
         vars("Source").bind_to(StringTerm(executionContext.src.name))
         vars("Parent").bind_to(StringTerm(executionContext.parent.name))






                 //plan 0 start

                         vars.clear()
                         vars("Self").bind_to(StringTerm(executionContext.name))
                         vars("Source").bind_to(StringTerm(executionContext.src.name))
                         vars("Parent").bind_to(StringTerm(executionContext.parent.name))
                         vars +=(   "Y" -> params.l_params(0))
                          vars +=(   "X" -> params.l_params(1))

                         val r0 = executionContext.beliefBase.query(StructTerm(",",Seq[GenericTerm](StructTerm("inplan",Seq[GenericTerm](vars("Z"),vars("X"))),StructTerm("acceptable",Seq[GenericTerm](vars("Z"))))))

                         if (r0.result) {
                             r0.bindings foreach { case (k, v) =>
                            // vars += (k -> v.asInstanceOf[GenericTerm])
                                      vars(k).bind_to(v)
                             }
                             plan0(vars)
                             return
                          }

                          // plan 0 end


             executionContext.src.asInstanceOf[AkkaMessageSource].address() ! IntentionErrorMessage(NoApplicablePlanMessage(),AkkaMessageSource(executionContext.agent.self))

        }


                      def plan0(vars: VarMap)(implicit executionContext: ExecutionContext): Unit = {

                                          PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( (StringTerm("acceptable plan ") + vars("Z")) )))
                                          PrimitiveAction.execute(PrimitiveAction.Parameters(() => coms.inform(StructTerm("jane",Seq[GenericTerm]()),StructTerm("benevolent",Seq[GenericTerm](StructTerm("agentZ",Seq[GenericTerm]()),vars("X"))))))


                     }


      }

      object adopt_achievement_competent_3 extends IGoal {

        def execute(params: Parameters) (implicit executionContext: ExecutionContext) : Unit = {
         var vars = VarMap()

         vars("Self").bind_to(StringTerm(executionContext.name))
         vars("Source").bind_to(StringTerm(executionContext.src.name))
         vars("Parent").bind_to(StringTerm(executionContext.parent.name))






                 //plan 0 start

                         vars.clear()
                         vars("Self").bind_to(StringTerm(executionContext.name))
                         vars("Source").bind_to(StringTerm(executionContext.src.name))
                         vars("Parent").bind_to(StringTerm(executionContext.parent.name))
                         vars +=(   "AgentT" -> params.l_params(0))
                          vars +=( "1" -> params.l_params(1))
                          vars +=(   "Goal" -> params.l_params(2))

                         val m0 = executionContext.beliefBase.matchTerms(StructTerm("competent",Seq[GenericTerm](vars("AgentT"),StructTerm("paula",Seq[GenericTerm]()),vars("Goal"))),StructTerm("competent",params.l_params));
                         if(m0.result)
                         {
                          m0.bindings foreach { case (k, v) =>
                          //vars += (k -> v.asInstanceOf[GenericTerm])
                          if(v.is_bound) vars(k).bind_to(v)
                          else vars += ( k -> VarTerm(k) )
                          }

                             plan0(vars)
                             return
                          }
                          // plan 0 end


             executionContext.src.asInstanceOf[AkkaMessageSource].address() ! IntentionErrorMessage(NoApplicablePlanMessage(),AkkaMessageSource(executionContext.agent.self))

        }


                      def plan0(vars: VarMap)(implicit executionContext: ExecutionContext): Unit = {

                                          PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( ( (StringTerm("Evaluating the competence of ") + StructTerm("paula",Seq[GenericTerm]()))  + StringTerm(" for the goal: "))  + vars("Goal")) )))
                                          PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( (StringTerm("The goal ") + vars("Goal"))  + StringTerm(" consists of the subplans: ")) )))
                                               val ex_L97571 = executionContext.beliefBase.bufferedQuery( StructTerm("subplan",Seq[GenericTerm](vars("Goal"),vars("L97571"))) )
                                               while (ex_L97571.hasNext) {
                                                   val sol_L97571 = ex_L97571.next
                                                   if(sol_L97571.result) {
                                                   vars += ("Subplan" -> sol_L97571.bindings("L97571").asInstanceOf[GenericTerm])
                                                                       PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( (StringTerm("- ") + vars("Subplan")) )))

                                                   }
                                               }
                                           vars -= ("Subplan")
                                               val ex_L65399 = executionContext.beliefBase.bufferedQuery( StructTerm("subplan",Seq[GenericTerm](vars("Goal"),vars("L65399"))) )
                                               while (ex_L65399.hasNext) {
                                                   val sol_L65399 = ex_L65399.next
                                                   if(sol_L65399.result) {
                                                   vars += ("Subplan" -> sol_L65399.bindings("L65399").asInstanceOf[GenericTerm])
                                                                       PrimitiveAction.execute(PrimitiveAction.Parameters(() => println(StringTerm(" "))))
                                                                       PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( (StringTerm("Evaluating the subplan: ") + vars("Subplan")) )))
                                                                       adopt_achievement_comp_conditions_3.execute(Parameters(List( vars("AgentT") , StructTerm("paula",Seq[GenericTerm]()) , vars("Subplan")  )))
                                                                       adopt_achievement_comp_subplans_2.execute(Parameters(List( vars("Goal") , vars("Subplan")  )))

                                                   }
                                               }
                                           vars -= ("Subplan")
                                          adopt_achievement_comp_total_2.execute(Parameters(List( StructTerm("paula",Seq[GenericTerm]()) , vars("Goal")  )))


                     }


      }

      object adopt_achievement_comp_conditions_3 extends IGoal {

        def execute(params: Parameters) (implicit executionContext: ExecutionContext) : Unit = {
         var vars = VarMap()

         vars("Self").bind_to(StringTerm(executionContext.name))
         vars("Source").bind_to(StringTerm(executionContext.src.name))
         vars("Parent").bind_to(StringTerm(executionContext.parent.name))






                 //plan 0 start

                         vars.clear()
                         vars("Self").bind_to(StringTerm(executionContext.name))
                         vars("Source").bind_to(StringTerm(executionContext.src.name))
                         vars("Parent").bind_to(StringTerm(executionContext.parent.name))
                         vars +=(   "AgentT" -> params.l_params(0))
                          vars +=( "1" -> params.l_params(1))
                          vars +=(   "Subplan" -> params.l_params(2))

                         val m0 = executionContext.beliefBase.matchTerms(StructTerm("comp_conditions",Seq[GenericTerm](vars("AgentT"),StructTerm("paula",Seq[GenericTerm]()),vars("Subplan"))),StructTerm("comp_conditions",params.l_params));
                         if(m0.result)
                         {
                          m0.bindings foreach { case (k, v) =>
                          //vars += (k -> v.asInstanceOf[GenericTerm])
                          if(v.is_bound) vars(k).bind_to(v)
                          else vars += ( k -> VarTerm(k) )
                          }

                         val r0 = executionContext.beliefBase.query(StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm("knowledge",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),vars("Subplan"),vars("K"))),StructTerm("skill",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),vars("Subplan"),vars("S"))))),StructTerm("resource",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),vars("Subplan"),vars("R"))))),StructTerm("threshold_knowledge",Seq[GenericTerm](vars("AgentT"),vars("Subplan"),vars("TK"))))),StructTerm("threshold_skill",Seq[GenericTerm](vars("AgentT"),vars("Subplan"),vars("TS"))))))

                         if (r0.result) {
                             r0.bindings foreach { case (k, v) =>
                            // vars += (k -> v.asInstanceOf[GenericTerm])
                                      vars(k).bind_to(v)
                             }
                             plan0(vars)
                             return
                          }

                          }
                          // plan 0 end


             executionContext.src.asInstanceOf[AkkaMessageSource].address() ! IntentionErrorMessage(NoApplicablePlanMessage(),AkkaMessageSource(executionContext.agent.self))

        }


                      def plan0(vars: VarMap)(implicit executionContext: ExecutionContext): Unit = {

                                          if(( ( ( (vars("K") >= vars("TK"))  &&  (vars("S") >= vars("TS")) )  &&  (vars("R") == IntTerm(1)) ) ).holds) {
                                                                  PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( ( (StringTerm("- ") + StructTerm("paula",Seq[GenericTerm]()))  + StringTerm(" is competent for the subplan: "))  + vars("Subplan")) )))
                                                                   BeliefUpdateAction.execute(BeliefUpdateAction.Parameters("+", StructTerm("sub_comp",Seq[GenericTerm](vars("Subplan"),IntTerm(1)))),GoalParser)

                                          }
                                           else {
                                                                  PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( ( (StringTerm("- ") + StructTerm("paula",Seq[GenericTerm]()))  + StringTerm(" is incompetent for the subplan: "))  + vars("Subplan")) )))
                                                                   BeliefUpdateAction.execute(BeliefUpdateAction.Parameters("+", StructTerm("sub_comp",Seq[GenericTerm](vars("Subplan"),IntTerm(0)))),GoalParser)

                                           }
                                          if(( (vars("K") < vars("TK")) ).holds) {
                                                                  PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( ( ( ( (StringTerm("-> ") + StructTerm("paula",Seq[GenericTerm]()))  + StringTerm(" misses "))  +  (vars("TK") - vars("K")) )  + StringTerm(" knowledge for the subplan: "))  + vars("Subplan")) )))

                                          }
                                          if(( (vars("S") < vars("TS")) ).holds) {
                                                                  PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( ( ( ( (StringTerm("-> ") + StructTerm("paula",Seq[GenericTerm]()))  + StringTerm(" misses "))  +  (vars("TS") - vars("S")) )  + StringTerm(" skills for the subplan: "))  + vars("Subplan")) )))

                                          }
                                          if(( (vars("R") == IntTerm(0)) ).holds) {
                                                                  PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( ( (StringTerm("-> ") + StructTerm("paula",Seq[GenericTerm]()))  + StringTerm(" misses the resources for the subplan: "))  + vars("Subplan")) )))

                                          }


                     }


      }

      object adopt_achievement_comp_subplans_2 extends IGoal {

        def execute(params: Parameters) (implicit executionContext: ExecutionContext) : Unit = {
         var vars = VarMap()

         vars("Self").bind_to(StringTerm(executionContext.name))
         vars("Source").bind_to(StringTerm(executionContext.src.name))
         vars("Parent").bind_to(StringTerm(executionContext.parent.name))






                 //plan 0 start

                         vars.clear()
                         vars("Self").bind_to(StringTerm(executionContext.name))
                         vars("Source").bind_to(StringTerm(executionContext.src.name))
                         vars("Parent").bind_to(StringTerm(executionContext.parent.name))
                         vars +=(   "Goal" -> params.l_params(0))
                          vars +=(   "Subplan" -> params.l_params(1))

                         val r0 = executionContext.beliefBase.query(StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm(",",Seq[GenericTerm](StructTerm("sub_comp",Seq[GenericTerm](vars("Subplan"),vars("SubComp"))),StructTerm("number_of_subs",Seq[GenericTerm](vars("Goal"),vars("NS"))))),StructTerm("succeeded_subs",Seq[GenericTerm](vars("Goal"),vars("SS"))))),StructTerm("is",Seq[GenericTerm](vars("NewNS"),StructTerm("+",Seq[GenericTerm](vars("NS"),IntTerm(1))))))),StructTerm("is",Seq[GenericTerm](vars("NewSS"),StructTerm("+",Seq[GenericTerm](vars("SS"),vars("SubComp"))))))))

                         if (r0.result) {
                             r0.bindings foreach { case (k, v) =>
                            // vars += (k -> v.asInstanceOf[GenericTerm])
                                      vars(k).bind_to(v)
                             }
                             plan0(vars)
                             return
                          }

                          // plan 0 end


             executionContext.src.asInstanceOf[AkkaMessageSource].address() ! IntentionErrorMessage(NoApplicablePlanMessage(),AkkaMessageSource(executionContext.agent.self))

        }


                      def plan0(vars: VarMap)(implicit executionContext: ExecutionContext): Unit = {

                                           BeliefUpdateAction.execute(BeliefUpdateAction.Parameters("-", StructTerm("number_of_subs",Seq[GenericTerm](vars("Goal"),vars("NS")))),GoalParser)
                                           BeliefUpdateAction.execute(BeliefUpdateAction.Parameters("+", StructTerm("number_of_subs",Seq[GenericTerm](vars("Goal"),vars("NewNS")))),GoalParser)
                                           BeliefUpdateAction.execute(BeliefUpdateAction.Parameters("-", StructTerm("succeeded_subs",Seq[GenericTerm](vars("Goal"),vars("SS")))),GoalParser)
                                           BeliefUpdateAction.execute(BeliefUpdateAction.Parameters("+", StructTerm("succeeded_subs",Seq[GenericTerm](vars("Goal"),vars("NewSS")))),GoalParser)


                     }


      }

      object adopt_achievement_comp_total_2 extends IGoal {

        def execute(params: Parameters) (implicit executionContext: ExecutionContext) : Unit = {
         var vars = VarMap()

         vars("Self").bind_to(StringTerm(executionContext.name))
         vars("Source").bind_to(StringTerm(executionContext.src.name))
         vars("Parent").bind_to(StringTerm(executionContext.parent.name))






                 //plan 0 start

                         vars.clear()
                         vars("Self").bind_to(StringTerm(executionContext.name))
                         vars("Source").bind_to(StringTerm(executionContext.src.name))
                         vars("Parent").bind_to(StringTerm(executionContext.parent.name))
                         vars +=( "0" -> params.l_params(0))
                          vars +=(   "Goal" -> params.l_params(1))

                         val m0 = executionContext.beliefBase.matchTerms(StructTerm("comp_total",Seq[GenericTerm](StructTerm("paula",Seq[GenericTerm]()),vars("Goal"))),StructTerm("comp_total",params.l_params));
                         if(m0.result)
                         {
                          m0.bindings foreach { case (k, v) =>
                          //vars += (k -> v.asInstanceOf[GenericTerm])
                          if(v.is_bound) vars(k).bind_to(v)
                          else vars += ( k -> VarTerm(k) )
                          }

                         val r0 = executionContext.beliefBase.query(StructTerm(",",Seq[GenericTerm](StructTerm("number_of_subs",Seq[GenericTerm](vars("Goal"),vars("NS"))),StructTerm("succeeded_subs",Seq[GenericTerm](vars("Goal"),vars("SS"))))))

                         if (r0.result) {
                             r0.bindings foreach { case (k, v) =>
                            // vars += (k -> v.asInstanceOf[GenericTerm])
                                      vars(k).bind_to(v)
                             }
                             plan0(vars)
                             return
                          }

                          }
                          // plan 0 end


             executionContext.src.asInstanceOf[AkkaMessageSource].address() ! IntentionErrorMessage(NoApplicablePlanMessage(),AkkaMessageSource(executionContext.agent.self))

        }


                      def plan0(vars: VarMap)(implicit executionContext: ExecutionContext): Unit = {

                                          PrimitiveAction.execute(PrimitiveAction.Parameters(() => println(StringTerm(" "))))
                                          if(( (vars("NS") == vars("SS")) ).holds) {
                                                                  PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( (StructTerm("paula",Seq[GenericTerm]()) + StringTerm(" is competent for the goal: "))  + vars("Goal")) )))

                                          }
                                           else {
                                                                  PrimitiveAction.execute(PrimitiveAction.Parameters(() => println( ( (StructTerm("paula",Seq[GenericTerm]()) + StringTerm(" is incompetent for the goal: "))  + vars("Goal")) )))

                                           }


                     }


      }





 }
object paula_companion { 
   def create() = new paula().agentBuilder 
   def create(in_coms : AgentCommunicationLayer) = new paula(coms = in_coms).agentBuilder 
   def create(in_beliefBaseFactory: IBeliefBaseFactory) = new paula(beliefBaseFactory = in_beliefBaseFactory).agentBuilder 
   def create(in_coms : AgentCommunicationLayer, in_beliefBaseFactory: IBeliefBaseFactory) = new paula(coms = in_coms, beliefBaseFactory = in_beliefBaseFactory).agentBuilder 
} 
