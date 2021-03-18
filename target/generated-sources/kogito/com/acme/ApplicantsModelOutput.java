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
import java.util.HashMap;

@org.kie.kogito.codegen.Generated(value = "kogito-codegen", reference = "applicants", name = "Applicants", hidden = true)
public class ApplicantsModelOutput implements org.kie.kogito.Model {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap();
        params.put("applicant", this.applicant);
        params.put("IsApproved", this.IsApproved);
        return params;
    }

    @Override
    public void fromMap(Map<String, Object> params) {
        fromMap(null, params);
    }

    @Override
    public void update(Map<String, Object> params) {
        fromMap(getId(), params);
    }

    public void fromMap(String id, Map<String, Object> params) {
        this.id = id;
        this.applicant = (org.acme.Applicant) params.get("applicant");
        this.IsApproved = (java.lang.Boolean) params.get("IsApproved");
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "applicant")
    @javax.validation.Valid()
    private org.acme.Applicant applicant;

    public org.acme.Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(org.acme.Applicant applicant) {
        this.applicant = applicant;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "IsApproved")
    @javax.validation.Valid()
    private java.lang.Boolean IsApproved;

    public java.lang.Boolean getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(java.lang.Boolean IsApproved) {
        this.IsApproved = IsApproved;
    }
}
