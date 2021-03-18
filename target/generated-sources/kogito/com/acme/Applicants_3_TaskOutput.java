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
import org.kie.kogito.UserTask;

@UserTask(taskName = "CreditRemediation", processName = "applicants")
public class Applicants_3_TaskOutput implements org.kie.kogito.MapOutput {

    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap();
        return params;
    }
}
// Task output model for user task 'Credit Remediation' in process 'applicants'
