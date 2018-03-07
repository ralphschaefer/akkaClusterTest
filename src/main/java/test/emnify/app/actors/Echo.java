package test.emnify.app.actors;


import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.UnreachableMember;
import java.io.Serializable;
import akka.cluster.ClusterEvent.MemberUp;
import test.emnify.app.AbstractGlobals;

/**
 * echo actor
 *
 * it echos any incomming "EchoMessage" to stdout and notifies if
 * an other node joins the cluster
 */
public class Echo extends AbstractActor {

    /**
     * message class for Echo actor
     */
    static public class EchoMessage implements Serializable {
        private String msg = null;
        public EchoMessage(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }
    }

    Cluster cluster = Cluster.get(getContext().getSystem());

    AbstractGlobals akkaGloblas = null;

    /**
     * constructor
     *
     * @param akkaGlobals DI for Akka artifacts
     */
    public Echo(AbstractGlobals akkaGlobals) {
        this.akkaGloblas = akkaGlobals;
    }

    @Override
    public void preStart() {
        cluster.subscribe(getSelf(), ClusterEvent.initialStateAsEvents(),
            MemberEvent.class, UnreachableMember.class);
    }

    @Override
    public void postStop() {
        cluster.unsubscribe(getSelf());
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(MemberUp.class, mUp -> {
                    if (akkaGloblas.getSenderActor()!=null)
                        akkaGloblas.getSenderActor().tell(new Echo.EchoMessage("joining chat ->" + mUp.toString()), getSelf());
                })
                .match(EchoMessage.class, e-> {
                    System.out.println("(" + getSender().toString() + ") -> " + e.getMsg());
                }).build();

    }

    /**
     * akka property factory
     *
     * @param globals DI for Akka artifacts
     * @return property for Echo actor
     */
    static public Props props(AbstractGlobals globals) {
        return Props.create(
                Echo.class,
                () -> new Echo(globals)
        );
    }

}
