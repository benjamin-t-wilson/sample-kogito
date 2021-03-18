/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme;

import java.util.Map;
import org.kie.kogito.UserTask;
import org.kie.kogito.UserTaskParam.ParamType;
import org.kie.kogito.UserTaskParam;

@UserTask(taskName = "CreditRemediation", processName = "applicants")
public class Applicants_3_TaskInput {

    private String _id;

    private String _name;

    public void setId(String id) {
        this._id = id;
    }

    public String getId() {
        return this._id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public static Applicants_3_TaskInput from(org.kie.kogito.process.WorkItem workItem) {
        Applicants_3_TaskInput item = new Applicants_3_TaskInput();
        item._id = workItem.getId();
        item._name = workItem.getName();
        Map<String, Object> params = workItem.getParameters();
        item.applicant = (org.acme.Applicant) params.get("applicant");
        return item;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private org.acme.Applicant applicant;

    public org.acme.Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(org.acme.Applicant applicant) {
        this.applicant = applicant;
    }
}
// Task input model for user task 'Credit Remediation' in process 'applicants'
