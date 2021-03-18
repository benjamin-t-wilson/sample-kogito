package org.acme;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.drools.core.util.KieFunctions;

@org.springframework.stereotype.Component("greetings")
public class GreetingsProcess extends org.kie.kogito.process.impl.AbstractProcess<org.acme.GreetingsModel> {

    @org.springframework.beans.factory.annotation.Autowired()
    public GreetingsProcess(org.kie.kogito.app.Application app) {
        super(app, java.util.Arrays.asList());
        activate();
    }

    public GreetingsProcess() {
    }

    @Override()
    public org.acme.GreetingsProcessInstance createInstance(org.acme.GreetingsModel value) {
        return new org.acme.GreetingsProcessInstance(this, value, this.createProcessRuntime());
    }

    public org.acme.GreetingsProcessInstance createInstance(java.lang.String businessKey, org.acme.GreetingsModel value) {
        return new org.acme.GreetingsProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    @Override()
    public org.acme.GreetingsModel createModel() {
        return new org.acme.GreetingsModel();
    }

    public org.acme.GreetingsProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((org.acme.GreetingsModel) value);
    }

    public org.acme.GreetingsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (org.acme.GreetingsModel) value);
    }

    public org.acme.GreetingsProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.GreetingsProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public org.acme.GreetingsProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new org.acme.GreetingsProcessInstance(this, this.createModel(), wpi);
    }

    public org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("greetings");
        factory.name("greetings");
        factory.packageName("org.acme");
        factory.dynamic(false);
        factory.version("1.0");
        factory.visibility("Public");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode1 = factory.endNode(1);
        endNode1.name("End");
        endNode1.terminate(false);
        endNode1.metaData("UniqueId", "_A6902151-5E9D-48F7-95E9-375E41CF3E6F");
        endNode1.metaData("x", 538);
        endNode1.metaData("width", 56);
        endNode1.metaData("y", 198);
        endNode1.metaData("height", 56);
        endNode1.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory actionNode2 = factory.actionNode(2);
        actionNode2.name("Hello");
        actionNode2.action(kcontext -> {
            System.out.println("Hello World");
        });
        actionNode2.metaData("UniqueId", "_3CDC6E61-DCC5-4831-8BBB-417CFF517CB0");
        actionNode2.metaData("elementname", "Hello");
        actionNode2.metaData("NodeType", "ScriptTask");
        actionNode2.metaData("x", 315);
        actionNode2.metaData("width", 143);
        actionNode2.metaData("y", 188);
        actionNode2.metaData("height", 76);
        actionNode2.done();
        org.jbpm.ruleflow.core.factory.StartNodeFactory startNode3 = factory.startNode(3);
        startNode3.name("Start");
        startNode3.interrupting(false);
        startNode3.metaData("UniqueId", "_75AC8C0C-CFBD-4ADF-A3B4-83AB90581A73");
        startNode3.metaData("x", 176);
        startNode3.metaData("width", 56);
        startNode3.metaData("y", 198);
        startNode3.metaData("height", 56);
        startNode3.done();
        factory.connection(2, 1, "_D764D3B4-1533-4962-A9E3-739A6DD9AEAB");
        factory.connection(3, 2, "_00AB4A77-D70F-4086-8BA6-57DD017A5323");
        factory.validate();
        return factory.getProcess();
    }
}
