package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.scripts.ChangeScriptCreator
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

/**
 * Gradle task for creating a new timestamped dbdeploy change script.
 */
class CreateChangeScriptTask extends DefaultTask{

    /**
     * Name suffix for the file that will be created (e.g. add_email_to_user_table).
     *
     * @parameter expression="${dbdeploy.script.name}" default-value="new_change_script"
     * @required
     */
    private String name

    /**
     * Directory where change scripts reside.
     *
     * @parameter expression="${dbdeploy.scriptdirectory}" default-value="${project.src.directory}/main/sql"
     * @required
     */
    private File scriptdirectory

    @TaskAction
    def createChangeScriptAction() throws GradleException{
        try {
            final ChangeScriptCreator changeScriptCreator = getConfiguredChangeScriptCreator()
            final File newChangeScript = changeScriptCreator.go()

            getLogger().info("Created new change script:\n\t" + newChangeScript.getAbsolutePath())
        } catch (Exception e) {
            getLogger().error(e)
            throw new GradleException("create change script failed", e)
        }
    }

    private ChangeScriptCreator getConfiguredChangeScriptCreator() {
        final ChangeScriptCreator changeScriptCreator = new ChangeScriptCreator()
        changeScriptCreator.setScriptDescription(name)
        changeScriptCreator.setScriptDirectory(scriptdirectory)

        return changeScriptCreator
    }
}
