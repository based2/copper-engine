/*
 * Copyright 2002-2011 SCOOP Software GmbH
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
package de.scoopgmbh.copper.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import de.scoopgmbh.copper.ProcessingEngine;

public class DefaultProcessorPoolManager<T extends ProcessorPool> implements ProcessorPoolManager<T> {
	
	private static final Logger logger = Logger.getLogger(DefaultProcessorPoolManager.class);
	
	private final Map<String, T> pools = new ConcurrentHashMap<String, T>();
	private boolean started = false;
	private boolean stopped = false;
	private ProcessingEngine engine;
	
	@Override
	public T getProcessorPool(String poolId) {
		return pools.get(poolId);
	}

	@Override
	public void addProcessorPool(T pool) {
		logger.info("addProcessorPool("+pool.getId()+")");
		
		if (pool == null) 
			throw new NullPointerException();
		
		if (stopped)
			throw new IllegalStateException();
		
		if (started) {
			pool.setEngine(engine);
			pool.startup();
		}
		pools.put(pool.getId(), pool);
	}

	@Override
	public List<String> getProcessorPoolIds() {
		return new ArrayList<String>(pools.keySet());
	}

	@Override
	public void removeProcessorPool(String poolId) {
		logger.info("removeProcessorPool("+poolId+")");

		if (poolId == null)
			throw new NullPointerException();

		if (stopped)
			throw new IllegalStateException();

		final T pool = pools.remove(poolId);
		if (pool != null && started) {
			pool.shutdown();
		}
	}

	public synchronized void shutdown() {
		if (stopped)
			return;
		logger.info("Shutting down...");
		
		for (ProcessorPool pool : pools.values()) {
			pool.shutdown();
		}
		
		stopped = true;
	}

	@Override
	public void setProcessorPools(List<T> processorPools) {
		for (T pp : processorPools) {
			addProcessorPool(pp);
		}
	}

	@Override
	public synchronized void startup() {
		if (started)
			return;
		if (engine == null)
			throw new NullPointerException();
		
		for (ProcessorPool pp : pools.values()) {
			pp.setEngine(engine);
			pp.startup();
		}
		started = true;
	}

	@Override
	public void setEngine(ProcessingEngine engine) {
		this.engine = engine;
	}

}
