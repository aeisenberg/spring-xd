/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.xd.dirt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.xd.dirt.stream.JobDefinition;
import org.springframework.xd.dirt.stream.JobDefinitionRepository;
import org.springframework.xd.dirt.stream.JobDeployer;
import org.springframework.xd.rest.client.domain.JobDefinitionResource;

/**
 * Handles all Job related interactions.
 * 
 * @author Glenn Renfro
 * @author Gunnar Hillert
 * @since 1.0
 */
@Controller
// jobs should be handled by spring batch admin
@RequestMapping("/jobsXXXXX")
@ExposesResourceFor(JobDefinitionResource.class)
public class JobsController extends XDController<JobDefinition, JobDefinitionResourceAssembler, JobDefinitionResource> {

	@Autowired
	public JobsController(JobDeployer jobDeployer, JobDefinitionRepository jobDefinitionRepository) {
		super(jobDeployer, new JobDefinitionResourceAssembler());
	}

	/**
	 * List job definitions.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public PagedResources<JobDefinitionResource> list(Pageable pageable,
			PagedResourcesAssembler<JobDefinition> assembler) {
		return listValues(pageable, assembler);
	}

	@Override
	protected JobDefinition createDefinition(String name, String definition) {
		return new JobDefinition(name, definition);
	}

}
