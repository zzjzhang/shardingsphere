/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.transaction.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.util.spi.ShardingSphereServiceLoader;
import org.apache.shardingsphere.transaction.api.TransactionType;
import org.apache.shardingsphere.transaction.spi.ShardingSphereTransactionManager;

import java.util.Collection;
import java.util.Optional;

/**
 * ShardingSphere transaction manager factory.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShardingSphereTransactionManagerFactory {
    
    static {
        ShardingSphereServiceLoader.register(ShardingSphereTransactionManager.class);
    }
    
    /**
     * Get all instances of ShardingSphere transaction manager.
     * 
     * @return got instances
     */
    public static Collection<ShardingSphereTransactionManager> getAllInstances() {
        return ShardingSphereServiceLoader.getServiceInstances(ShardingSphereTransactionManager.class);
    }
    
    /**
     * Get instance of ShardingSphere transaction manager.
     *
     * @param transactionType transaction type
     * @return ShardingSphere transaction manager instance
     */
    public static Optional<ShardingSphereTransactionManager> getInstance(final TransactionType transactionType) {
        Collection<ShardingSphereTransactionManager> transactionManagers = ShardingSphereTransactionManagerFactory.getAllInstances();
        if (null == transactionManagers || transactionManagers.isEmpty()) {
            return Optional.empty();
        }
        return transactionManagers.stream().filter(each -> transactionType.equals(each.getTransactionType())).findFirst();
    }
}
