/*
 *
 * CODENVY CONFIDENTIAL
 * ________________
 *
 * [2012] - [2014] Codenvy, S.A.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.analytics.metrics.users;

import com.codenvy.analytics.metrics.*;

import javax.annotation.security.RolesAllowed;

import static com.codenvy.analytics.Utils.getFilterAsSet;
import static com.codenvy.analytics.Utils.isAnonymousExist;

/**
 * @author Alexander Reshetnyak
 */
@RolesAllowed({})
@OmittedFilters({MetricFilter.WS})
public class UsersStatisticsListPrecomputed extends AbstractListValueResulted implements PrecomputedDataMetric {

    public UsersStatisticsListPrecomputed() {
        super(MetricType.USERS_STATISTICS_LIST_PRECOMPUTED);
    }

    @Override
    public String getDescription() {
        return "Users' statistics data";
    }

    @Override
    public String[] getTrackedFields() {
        return new String[]{USER,
                            PROJECTS,
                            RUNS,
                            DEBUGS,
                            BUILDS,
                            DEPLOYS,
                            FACTORIES,
                            TIME,
                            SESSIONS,
                            INVITES,
                            LOGINS,
                            RUN_TIME,
                            BUILD_TIME,
                            PAAS_DEPLOYS,
                            USER_FIRST_NAME,
                            USER_LAST_NAME,
                            USER_COMPANY,
                            USER_JOB
        };
    }

    @Override
    public Context getContextForBasedMetric() {
        Context.Builder builder = new Context.Builder();
        builder.put(MetricFilter.USER, Parameters.USER_TYPES.REGISTERED.name());
        return builder.build();
    }

    @Override
    public MetricType getBasedMetric() {
        return MetricType.USERS_STATISTICS_LIST;
    }

    @Override
    public boolean canReadPrecomputedData(Context context) {
        String value = context.getAsString(MetricFilter.USER);
        return value == null || !isAnonymousExist(getFilterAsSet(value));
    }
}