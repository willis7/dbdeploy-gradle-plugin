package org.gradle.api.plugins.dbdeploy.tasks

import com.dbdeploy.scripts.ChangeScriptCreator
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Gradle task for creating a new timestamped dbdeploy change script.
 * @author Sion Williams
 */
class CreateChangeScriptTask extends DefaultTask{

    // Name suffix for the file that will be created (e.g. add_email_to_user_table).
    @Input
    String name

    // Directory where change scripts reside.
    @InputDirectory
    File scriptdirectory

    @TaskAction
    def action() throws GradleException{
        try {
            final ChangeScriptCreator changeScriptCreator = getConfiguredChangeScriptCreator()
            final File newChangeScript = changeScriptCreator.go()

            logger.info("Created new change script:\n\t" + newChangeScript.getAbsolutePath())
        } catch (Exception e) {
            logger.error(e)
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
