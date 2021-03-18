package org.kie.kogito.app;

/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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
import java.util.Collection;

@org.springframework.stereotype.Component
public class ApplicationConfig extends org.kie.kogito.StaticConfig {

    @org.springframework.beans.factory.annotation.Autowired
    public ApplicationConfig(Collection<org.kie.kogito.KogitoConfig> configs) {
        super(new org.kie.kogito.Addons(java.util.Arrays.asList()), configs);
    }
}
