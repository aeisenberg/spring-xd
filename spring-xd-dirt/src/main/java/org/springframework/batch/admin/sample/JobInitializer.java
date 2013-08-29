/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.batch.admin.sample;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Bean for initializing jobs. Should be done automatically. but isn't
 * 
 * @author andrew
 */
public class JobInitializer {

	@Autowired
	private JobRegistry jobRegistry;

	@Autowired
	@Qualifier("job1")
	Job job1;

	@Autowired
	@Qualifier("job2")
	Job job2;

	@Autowired
	@Qualifier("job3")
	Job job3;

	@Autowired
	@Qualifier("job4")
	Job job4;

	@Autowired
	@Qualifier("infinite")
	Job infinite;

	@Autowired(required = true)
	@Qualifier("step1")
	private Step step1;

	@PostConstruct
	public void initialize() throws DuplicateJobException {
		jobRegistry.register(new JobFactory() {

			@Override
			public String getJobName() {
				return "job1";
			}

			@Override
			public Job createJob() {
				return job1;
			}
		});
		jobRegistry.register(new JobFactory() {

			@Override
			public String getJobName() {
				return "job2";
			}

			@Override
			public Job createJob() {
				return job2;
			}
		});
		jobRegistry.register(new JobFactory() {

			@Override
			public String getJobName() {
				return "job3";
			}

			@Override
			public Job createJob() {
				return job3;
			}
		});
		jobRegistry.register(new JobFactory() {

			@Override
			public String getJobName() {
				return "job4";
			}

			@Override
			public Job createJob() {
				return job4;
			}
		});
		jobRegistry.register(new JobFactory() {

			@Override
			public String getJobName() {
				return "infinite";
			}

			@Override
			public Job createJob() {
				return infinite;
			}
		});
	}
}
