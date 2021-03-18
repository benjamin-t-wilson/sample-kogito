package com.acme;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.drools.core.util.KieFunctions;

@org.springframework.stereotype.Component("applicants")
public class ApplicantsProcess extends org.kie.kogito.process.impl.AbstractProcess<com.acme.ApplicantsModel> {

    @org.springframework.beans.factory.annotation.Autowired()
    public ApplicantsProcess(org.kie.kogito.app.Application app) {
        super(app, java.util.Arrays.asList());
        activate();
    }

    public ApplicantsProcess() {
    }

    @Override()
    public com.acme.ApplicantsProcessInstance createInstance(com.acme.ApplicantsModel value) {
        return new com.acme.ApplicantsProcessInstance(this, value, this.createProcessRuntime());
    }

    public com.acme.ApplicantsProcessInstance createInstance(java.lang.String businessKey, com.acme.ApplicantsModel value) {
        return new com.acme.ApplicantsProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    @Override()
    public com.acme.ApplicantsModel createModel() {
        return new com.acme.ApplicantsModel();
    }

    public com.acme.ApplicantsProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((com.acme.ApplicantsModel) value);
    }

    public com.acme.ApplicantsProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (com.acme.ApplicantsModel) value);
    }

    public com.acme.ApplicantsProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new com.acme.ApplicantsProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public com.acme.ApplicantsProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new com.acme.ApplicantsProcessInstance(this, this.createModel(), wpi);
    }

    public org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("applicants");
        factory.variable("applicant", new ObjectDataType("org.acme.Applicant"), "customTags", null);
        factory.variable("IsApproved", new ObjectDataType("java.lang.Boolean"), "customTags", null);
        factory.name("Applicant Process");
        factory.packageName("com.acme");
        factory.dynamic(false);
        factory.version("1.0");
        factory.visibility("Public");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode1 = factory.endNode(1);
        endNode1.name("End");
        endNode1.terminate(false);
        endNode1.metaData("UniqueId", "_7068F509-08F9-4545-92AB-2B239B71BABD");
        endNode1.metaData("x", 921);
        endNode1.metaData("width", 56);
        endNode1.metaData("y", 659);
        endNode1.metaData("height", 56);
        endNode1.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory endNode2 = factory.endNode(2);
        endNode2.name("End");
        endNode2.terminate(false);
        endNode2.metaData("UniqueId", "_B13C206E-29D6-4B1C-9516-CD1BCF4FF26F");
        endNode2.metaData("x", 921);
        endNode2.metaData("width", 56);
        endNode2.metaData("y", 508);
        endNode2.metaData("height", 56);
        endNode2.done();
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory humanTaskNode3 = factory.humanTaskNode(3);
        humanTaskNode3.name("Credit Remediation");
        humanTaskNode3.workParameter("TaskName", "CreditRemediation");
        humanTaskNode3.workParameter("Skippable", "false");
        humanTaskNode3.workParameter("NodeName", "Credit Remediation");
        humanTaskNode3.inMapping("applicant", "applicant");
        humanTaskNode3.done();
        humanTaskNode3.metaData("UniqueId", "_8CF14156-B6F1-4912-9B9E-F54889769E86");
        humanTaskNode3.metaData("elementname", "Credit Remediation");
        humanTaskNode3.metaData("x", 684);
        humanTaskNode3.metaData("width", 154);
        humanTaskNode3.metaData("y", 485);
        humanTaskNode3.metaData("height", 102);
        org.jbpm.ruleflow.core.factory.SplitFactory splitNode4 = factory.splitNode(4);
        splitNode4.name("Split");
        splitNode4.type(2);
        splitNode4.metaData("UniqueId", "_552A2C47-F2AB-490C-B739-C63A16D2EEF4");
        splitNode4.metaData("x", 556);
        splitNode4.metaData("width", 56);
        splitNode4.metaData("y", 508);
        splitNode4.metaData("height", 56);
        splitNode4.constraint(1, "_C5942AB9-1D72-4EEA-991B-DA51ED7584C0", "DROOLS_DEFAULT", "java", kcontext -> {
            org.acme.Applicant applicant = (org.acme.Applicant) kcontext.getVariable("applicant");
            java.lang.Boolean IsApproved = (java.lang.Boolean) kcontext.getVariable("IsApproved");
            return applicant.IsApproved == true;
        }, 0);
        splitNode4.constraint(3, "_93A8C6C4-DF2D-49BF-AFD0-5C9DE5D5B7E6", "DROOLS_DEFAULT", "java", kcontext -> {
            org.acme.Applicant applicant = (org.acme.Applicant) kcontext.getVariable("applicant");
            java.lang.Boolean IsApproved = (java.lang.Boolean) kcontext.getVariable("IsApproved");
            return applicant.IsApproved == false;
        }, 0);
        splitNode4.done();
        org.jbpm.ruleflow.core.factory.RuleSetNodeFactory ruleSetNode5 = factory.ruleSetNode(5);
        ruleSetNode5.name("Evaluate Credit Score");
        ruleSetNode5.inMapping("Applicant", "applicant");
        ruleSetNode5.outMapping("IsApproved", "IsApproved");
        ruleSetNode5.decision("https://kiegroup.org/dmn/_DB9BBCA2-3EC3-4B8F-8F4D-B5B598882AAB", "ApplicantDecisions", "IsApproved", () -> {
            return app.get(org.kie.kogito.decision.DecisionModels.class).getDecisionModel("https://kiegroup.org/dmn/_DB9BBCA2-3EC3-4B8F-8F4D-B5B598882AAB", "ApplicantDecisions");
        });
        ruleSetNode5.metaData("UniqueId", "_21C71CA6-EF94-4060-A3D3-D8058225AA63");
        ruleSetNode5.metaData("elementname", "Evaluate Credit Score");
        ruleSetNode5.metaData("x", 344);
        ruleSetNode5.metaData("width", 154);
        ruleSetNode5.metaData("y", 485);
        ruleSetNode5.metaData("height", 102);
        ruleSetNode5.done();
        org.jbpm.ruleflow.core.factory.StartNodeFactory startNode6 = factory.startNode(6);
        startNode6.name("Start");
        startNode6.interrupting(false);
        startNode6.metaData("UniqueId", "_99578A4E-8BBD-4FD5-9938-2E7175593BB0");
        startNode6.metaData("x", 177);
        startNode6.metaData("width", 56);
        startNode6.metaData("y", 508);
        startNode6.metaData("height", 56);
        startNode6.done();
        factory.connection(4, 1, "_C5942AB9-1D72-4EEA-991B-DA51ED7584C0");
        factory.connection(3, 2, "_86AB2CE3-3D93-47A8-B6BA-EFCEB9E4174E");
        factory.connection(4, 3, "_93A8C6C4-DF2D-49BF-AFD0-5C9DE5D5B7E6");
        factory.connection(5, 4, "_7A74BFF1-C1C1-4D4D-869D-E4BCAE093422");
        factory.connection(6, 5, "_F58CF3D0-90B5-412E-9B91-DA3126DB3BFD");
        factory.validate();
        return factory.getProcess();
    }
}
