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
package org.acme;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import org.jbpm.util.JsonSchemaUtil;
import org.kie.kogito.Application;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessInstanceReadMode;
import org.kie.kogito.process.impl.Sig;
import org.kie.kogito.process.ProcessInstanceExecutionException;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.process.workitem.Policies;
import org.kie.kogito.services.uow.UnitOfWorkExecutor;
import org.jbpm.process.instance.impl.humantask.HumanTaskTransition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.acme.GreetingsModelOutput;

@RestController
@RequestMapping("/greetings")
@org.springframework.stereotype.Component()
public class GreetingsResource {

    @org.springframework.beans.factory.annotation.Autowired()
    @org.springframework.beans.factory.annotation.Qualifier("greetings")
    Process<GreetingsModel> process;

    @org.springframework.beans.factory.annotation.Autowired()
    Application application;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GreetingsModelOutput> createResource_greetings(@RequestHeader HttpHeaders httpHeaders, @RequestParam(value = "businessKey", required = false) String businessKey, @RequestBody(required = false) @javax.validation.Valid() @javax.validation.constraints.NotNull() GreetingsModelInput resource, UriComponentsBuilder uriComponentsBuilder) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> {
            GreetingsModelInput inputModel = resource != null ? resource : new GreetingsModelInput();
            ProcessInstance<GreetingsModel> pi = process.createInstance(businessKey, inputModel.toModel());
            List<String> startFromNode = httpHeaders.get("X-KOGITO-StartFromNode");
            if (startFromNode != null && !startFromNode.isEmpty()) {
                pi.startFrom(startFromNode.get(0));
            } else {
                pi.start();
            }
            UriComponents uriComponents = uriComponentsBuilder.path("/greetings/{id}").buildAndExpand(pi.id());
            URI location = uriComponents.toUri();
            return ResponseEntity.created(location).body(pi.checkError().variables().toOutput());
        });
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GreetingsModelOutput> getResources_greetings() {
        return process.instances().values().stream().map(pi -> pi.variables().toOutput()).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GreetingsModelOutput> getResource_greetings(@PathVariable("id") String id) {
        return process.instances().findById(id, ProcessInstanceReadMode.READ_ONLY).map(m -> ResponseEntity.ok(m.variables().toOutput())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GreetingsModelOutput> deleteResource_greetings(@PathVariable("id") final String id) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.abort();
            return pi.checkError().variables().toOutput();
        }).map(m -> ResponseEntity.ok(m))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GreetingsModelOutput> updateModel_greetings(@PathVariable("id") String id, @RequestBody(required = false) GreetingsModel resource) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> pi.updateVariables(resource).toOutput()).map(m -> ResponseEntity.ok(m))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkItem>> getTasks_greetings(@PathVariable("id") String id, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false, defaultValue = "") final List<String> groups) {
        return process.instances().findById(id, ProcessInstanceReadMode.READ_ONLY).map(pi -> pi.workItems(Policies.of(user, groups))).map(m -> ResponseEntity.ok(m)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
