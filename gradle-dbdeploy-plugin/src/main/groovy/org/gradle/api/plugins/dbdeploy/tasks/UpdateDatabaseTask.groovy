package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.DbDeploy
import org.gradle.api.GradleException

/**
 * Apply dbdeploy change scripts directly to the database.
 * @author Sion Williams
 */
public class UpdateDatabaseTask extends AbstractDbDeployTask {

    UpdateDatabaseTask(){
        super('Apply dbdeploy change scripts to the database.')
    }

    @Override
    public void executeAction() {
        DbDeploy dbDeploy = getConfiguredDbDeploy()

        try {
            dbDeploy.go()
        } catch (Exception e) {
            logger.error(e)
            throw new GradleException("dbdeploy update failed", e)
        }
    }
}