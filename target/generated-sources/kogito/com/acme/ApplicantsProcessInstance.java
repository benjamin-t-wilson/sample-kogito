package com.acme;

public class ApplicantsProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<ApplicantsModel> {

    public ApplicantsProcessInstance(com.acme.ApplicantsProcess process, ApplicantsModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public ApplicantsProcessInstance(com.acme.ApplicantsProcess process, ApplicantsModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    public ApplicantsProcessInstance(com.acme.ApplicantsProcess process, ApplicantsModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, processRuntime, wpi);
    }

    public ApplicantsProcessInstance(com.acme.ApplicantsProcess process, ApplicantsModel value, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, wpi);
    }

    protected java.util.Map<String, Object> bind(ApplicantsModel variables) {
        return variables.toMap();
    }

    protected void unbind(ApplicantsModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
