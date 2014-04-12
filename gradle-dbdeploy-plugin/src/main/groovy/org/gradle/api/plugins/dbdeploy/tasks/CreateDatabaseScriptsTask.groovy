package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.DbDeploy
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

/**
 * Gradle task for creating the apply and undo scripts.
 */
class CreateDatabaseScriptsTask extends AbstractDbDeployTask {
    /**
     * The name of the script that dbdeploy will output. Include a full
     * or relative path.
     *
     * @parameter
     * @required
     */
    private File outputfile

    /**
     * String representing our DBMS (e.g. mysql, ora)
     *
     * @parameter
     * @required
     */
    private String dbms

    /**
     * The name of the undo script that dbdeploy will output. Include a full
     * or relative path.
     *
     * @parameter
     * @required
     */
    private File undoOutputfile

    /**
     * Directory for your template scripts, if not using built-in
     *
     * @parameter
     */
    private File templateDirectory

    @TaskAction
    def createDatabaseScriptsAction() throws GradleException {
        DbDeploy dbDeploy = getConfiguredDbDeploy()

        try {
            dbDeploy.go()
        } catch (Exception e) {
            getLogger().error(e)
            throw new GradleException("dbdeploy change script create failed", e)
        }
    }

    @Override
    protected DbDeploy getConfiguredDbDeploy() {
        DbDeploy dbDeploy = super.getConfiguredDbDeploy()
        dbDeploy.setOutputfile(outputfile)
        dbDeploy.setUndoOutputfile(undoOutputfile)
        dbDeploy.setDbms(dbms)

        if (templateDirectory != null) {
            dbDeploy.setTemplatedir(templateDirectory)
        }

        return dbDeploy
    }
}
