package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.DbDeploy
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile

/**
 * Gradle task for creating the apply and undo scripts.
 * @author Sion Williams
 */
class CreateDatabaseScriptsTask extends DbDeployTask {

    @OutputFile File outputfile
    @Input String dbms
    @OutputFile File undoOutputfile
    @Optional File templateDirectory

    CreateDatabaseScriptsTask(){
        super('Create the apply and undo scripts.')
    }

    @Override
    void executeAction(){
        DbDeploy dbDeploy = getConfiguredDbDeploy()
        dbDeploy.go()
    }

    @Override
    protected DbDeploy getConfiguredDbDeploy() {
        DbDeploy dbDeploy = super.getConfiguredDbDeploy()
        dbDeploy.setOutputfile(getOutputfile())
        dbDeploy.setUndoOutputfile(getUndoOutputfile())
        dbDeploy.setDbms(getDbms())

        if (templateDirectory != null) {
            dbDeploy.setTemplatedir(getTemplateDirectory())
        }

        return dbDeploy
    }
}
