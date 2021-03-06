/*
 * Copyright 2002-2015 SCOOP Software GmbH
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
package org.copperengine.regtest.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.sql.DataSource;

import org.copperengine.core.audit.AbstractAuditTrail;
import org.copperengine.core.audit.AuditTrailEvent;
import org.copperengine.core.audit.BatchInsertIntoAutoTrail.Command;
import org.copperengine.core.audit.BatchInsertIntoAutoTrail.Executor;
import org.copperengine.core.audit.BatchingAuditTrail;
import org.copperengine.core.audit.AbstractAuditTrail.Property2ColumnMapping;
import org.copperengine.core.batcher.BatchCommand;
import org.copperengine.core.batcher.NullCallback;
import org.copperengine.regtest.persistent.DataSourceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchingAuditTrailTest extends AuditTrailTestBase{

    private static final Logger logger = LoggerFactory.getLogger(BatchingAuditTrailTest.class);

    @Override
    AbstractAuditTrail getTestAuditTrail() throws Exception{
        return new BatchingAuditTrail();
    }

}
