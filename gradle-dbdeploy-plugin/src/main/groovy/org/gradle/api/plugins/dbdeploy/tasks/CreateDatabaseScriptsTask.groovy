package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.DbDeploy
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile

/**
 * Gradle task for creating the apply and undo scripts.
 * @author Sion Williams
 */
class CreateDatabaseScriptsTask extends AbstractDbDeployTask {

    /*
    * The name of the script that dbdeploy will output. Include a full
    * or relative path.
    */
    @OutputFile
    File outputfile

    /*
    * String representing our DBMS (e.g. mysql, ora)
    */
    @Input
    String dbms

    /*
    * The name of the undo script that dbdeploy will output. Include a full
    * or relative path.
    */
    @OutputFile
    File undoOutputfile

    /*
    * Directory for your template scripts, if not using built-in
    */
    @InputDirectory
    @Optional
    File templateDirectory

    CreateDatabaseScriptsTask(){
        super('Create the apply and undo scripts.')
    }

    @Override
    void executeAction(){
        DbDeploy dbDeploy = getConfiguredDbDeploy()

        try {
            dbDeploy.go()
        } catch (Exception e) {
            logger.error(e)
            throw new GradleException("dbdeploy change script create failed", e)
        }
    }

    @Override
    protected DbDeploy getConfiguredDbDeploy() {
        DbDeploy dbDeploy = super.getConfiguredDbDeploy()
        dbDeploy.setOutputfile(getOutputfile())
        dbDeploy.setUndoOutputfile(getUndoOutputfile())
        dbDeploy.setDbms(getDbms())

        if (getTemplateDirectory()) {
            dbDeploy.setTemplatedir(getTemplateDirectory())
        }

        return dbDeploy
    }
}
