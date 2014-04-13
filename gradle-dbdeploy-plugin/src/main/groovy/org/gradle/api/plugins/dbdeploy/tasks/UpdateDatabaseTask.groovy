package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.DbDeploy

/**
 * Apply dbdeploy change scripts directly to the database.
 * @author Sion Williams
 */
public class UpdateDatabaseTask extends DbDeployTask {

    UpdateDatabaseTask(){
        super('Apply dbdeploy change scripts to the database.')
    }

    @Override
    public void executeAction() {
        DbDeploy dbDeploy = configuredDbDeploy
        dbDeploy.go();
    }
}