/*
 * Copyright 2015 Red Hat Inc. and/or its affiliates and other contributors.
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
package org.jboss.qa.jcontainer.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ContainerTest {

	private static final Properties props = new Properties();

	public static String getProperty(String key) {
		final String value = System.getProperty(key);
		return value != null && !value.isEmpty() ? value : (String) props.get(key);
	}

	static {
		final String testPropertiesFile = "src/test/resources/test.properties";
		try (InputStream is = new FileInputStream(testPropertiesFile)) {
			props.load(is);
		} catch (IOException e) {
			log.warn("File {} does not exist", testPropertiesFile);
		}
	}
}
